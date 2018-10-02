/**
 * Copyright (c) 2015 CineApps
 * This source code is the property of CineApps.
 */
package com.cineapps.server.gdc.server;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.StringReader;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.bind.DatatypeConverter;
import javax.xml.bind.JAXBException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringEscapeUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import util.GdcCommand;

import com.cineapps.server.common.MediablockServer;
import com.cineapps.server.gdc.status.Response;
import com.cineapps.server.gdc.status.Response.Status;
import com.cineapps.server.gdc.status.Response.Status.CplPosition;
import com.cineapps.server.gdc.status.Response.Status.ShowPosition;
import com.cineapps.service.VideoStreamStatus;
import com.cineapps.service.VideoStreamWebService;

public class GdcServer implements MediablockServer {

	// User variables
	private final String title;
	private final String DCP = "_FTR_S_FR-XX_FR_51_2K_SPE_20090912_DUK_i3D-ngb";
	private UUID splUuid = UUID.fromString("c23ab440-0294-11e4-9191-0800200c9a66");
	private UUID cplUuid = UUID.fromString("1bf53165-3138-4848-9f35-8e7435772286");
	private Logger logger = Logger.getLogger(GdcServer.class.getName());
	private final VideoStreamWebService videoStreamWebService = new VideoStreamWebService();
	private final int MOVIE_DURATION_S = 2 * 60 * 60;

	// Server variables
	private Selector selector;
	private SocketChannel channel;
	private Map<SocketChannel, List<byte[]>> dataMap = new HashMap<SocketChannel, List<byte[]>>();

	// Logic variables
	public static final int HEADER_LEN = 16;
	public static final int LENGTH_LEN = 4;
	private static final byte[] request_header = new byte[HEADER_LEN];
	private static final byte[] response_header = new byte[HEADER_LEN];
	static {
		request_header[0] = 6;
		request_header[1] = 0xE;
		request_header[2] = 0x2B;
		request_header[3] = 0x34;
		request_header[4] = 2;
		request_header[5] = 5;
		request_header[6] = 1;
		request_header[7] = 1;
		request_header[8] = 0xF;
		request_header[9] = 0x15;
		request_header[10] = 1;
		request_header[11] = 0x10;
		request_header[12] = 0;
		request_header[13] = 0;
		request_header[14] = 0;
		request_header[15] = 0;

		response_header[0] = 6;
		response_header[1] = 0xE;
		response_header[2] = 0x2B;
		response_header[3] = 0x34;
		response_header[4] = 2;
		response_header[5] = 5;
		response_header[6] = 1;
		response_header[7] = 1;
		response_header[8] = 0xF;
		response_header[9] = 0x15;
		response_header[10] = 1;
		response_header[11] = 0x11;
		response_header[12] = 0;
		response_header[13] = 0;
		response_header[14] = 0;
		response_header[15] = 0;
	}

	public GdcServer(String title) {
		this.title = title;
	}

	// ##### SERVER SIDE #####

	@Override
	public void run(int port) {
		try {
			// create selector and channel
			this.selector = Selector.open();
			ServerSocketChannel serverChannel = ServerSocketChannel.open();
			serverChannel.configureBlocking(false);

			// bind to port
			InetSocketAddress listenAddr = new InetSocketAddress(port);
			serverChannel.socket().bind(listenAddr);
			logger.log(Level.INFO, "The server is running on port " + port);
			serverChannel.register(this.selector, SelectionKey.OP_ACCEPT);

			// processing
			while (true) {
				// wait for events
				this.selector.select();

				// wakeup to work on selected keys
				Iterator<SelectionKey> keys = this.selector.selectedKeys().iterator();
				while (keys.hasNext()) {
					SelectionKey key = (SelectionKey) keys.next();

					// this is necessary to prevent the same key from coming up
					// again the next time around.
					keys.remove();

					if (!key.isValid()) {
						continue;
					}

					if (key.isAcceptable()) {
						this.accept(key);
					} else if (key.isReadable()) {
						this.read(key);
					} else if (key.isWritable()) {
						this.write(key);
					}
				}
			}
		} catch (IOException e) {
			logger.log(Level.SEVERE, "The server is not running", e);
		}
	}

	private void accept(SelectionKey key) throws IOException {
		ServerSocketChannel serverChannel = (ServerSocketChannel) key.channel();
		channel = serverChannel.accept();
		channel.configureBlocking(false);

		// register channel with selector for further IO
		dataMap.put(channel, new ArrayList<byte[]>());
		channel.register(this.selector, SelectionKey.OP_READ);
	}

	private void read(SelectionKey key) throws IOException {
		SocketChannel channel = (SocketChannel) key.channel();

		ByteBuffer buffer = ByteBuffer.allocate(8192);
		int numRead = -1;
		try {
			numRead = channel.read(buffer);
		} catch (IOException e) {
			e.printStackTrace();
		}

		if (numRead == -1) {
			this.dataMap.remove(channel);
			channel.close();
			key.cancel();
			return;
		}

		byte[] data = new byte[numRead];
		System.arraycopy(buffer.array(), 0, data, 0, numRead);
		byte[] response = doRequest(data);

		// write back to client
		doAnswer(key, response);
	}

	private void write(SelectionKey key) throws IOException {
		SocketChannel channel = (SocketChannel) key.channel();
		List<byte[]> pendingData = this.dataMap.get(channel);
		Iterator<byte[]> items = pendingData.iterator();
		while (items.hasNext()) {
			byte[] item = items.next();
			items.remove();
			channel.write(ByteBuffer.wrap(item));
		}
		key.interestOps(SelectionKey.OP_READ);
	}

	private void doAnswer(SelectionKey key, byte[] data) {
		SocketChannel channel = (SocketChannel) key.channel();
		List<byte[]> pendingData = this.dataMap.get(channel);
		pendingData.add(data);
		key.interestOps(SelectionKey.OP_WRITE);
	}

	// ##### SERVER RESPONSE IMPLEMENTATION #####

	public byte[] doRequest(byte[] request) throws IOException {
		byte[] cmdRaw = Arrays.copyOfRange(request, HEADER_LEN + LENGTH_LEN, request.length);
		String cmd = StringEscapeUtils.unescapeXml(new String(cmdRaw));
		try {
			GdcCommand gdcCommand = parseCommand(cmd);
			switch (gdcCommand) {
				case GET_PLAYBACK_STATUS:
					return onGetPlaybackStatus();
				case GET_SCHEDULER_STATUS:
					return onGetSchedulerStatus();
				case GET_SPL:
					return onGetSpl();
				case GET_CPL:
					return onGetCpl();
				default:
					break;
			}
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		return null;
	}

	private GdcCommand parseCommand(String xmlRequest) throws ParserConfigurationException,
	        SAXException, IOException {
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		InputSource is = new InputSource(new StringReader(xmlRequest));
		Document doc = dBuilder.parse(is);
		doc.getDocumentElement().normalize();

		Element commandNode = (Element) doc.getElementsByTagName("command").item(0);
		String cmdAttribute = commandNode.getAttribute("cmd");
		for (GdcCommand cmd : GdcCommand.values()) {
			if (cmd.getLabel().equals(cmdAttribute)) {
				return cmd;
			}
		}
		throw new IllegalArgumentException("Unknown command : " + cmdAttribute);
	}

	private byte[] onGetPlaybackStatus() throws JAXBException, IOException {
		Response response = Response.defaultInstance();
		Status status = response.getStatus();

		status.setCplName(title + DCP);
		status.setShowName(title + DCP);
		status.setCplUuid("urn:uuid:" + cplUuid.toString());
		status.setShowUuid("urn:uuid:" + splUuid.toString());

		int currentPostion = 0;
		String state = "STOPPED";

		VideoStreamStatus videoStreamStatus = videoStreamWebService.getStatus();
		if (videoStreamStatus != null) {
			currentPostion = videoStreamStatus.getCurrentTime_s();
			if (videoStreamStatus.isPlaying()) {
				state = "PLAYING";
			}
		}

		status.setState(state);
		CplPosition cplPosition = new CplPosition();
		cplPosition.setPlayedDuration(currentPostion);
		cplPosition.setTotalDuration(MOVIE_DURATION_S);
		status.setCplPosition(cplPosition);
		ShowPosition showPosition = new ShowPosition();
		showPosition.setPlayedDuration(currentPostion);
		showPosition.setTotalDuration(MOVIE_DURATION_S);
		status.setShowPosition(showPosition);

		String xml = response.marshall();
		byte[] header = DatatypeConverter.parseHexBinary("060E2B34020501010F15011100000000");
		byte[] length = util.GdcUtils.getgdcBerLength(xml);
		byte[] body = xml.getBytes();

		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		outputStream.write(header);
		outputStream.write(length);
		outputStream.write(body);

		return outputStream.toByteArray();
	}

	private byte[] onGetSchedulerStatus() throws IOException {
		String xml = IOUtils.toString(
		        this.getClass().getResourceAsStream("/gdc/GET_SCHEDULER_STATUS.xml"), "UTF-8");
		byte[] header = DatatypeConverter.parseHexBinary("060E2B34020501010F15011100000000");
		byte[] length = util.GdcUtils.getgdcBerLength(xml);
		byte[] body = xml.getBytes();

		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		outputStream.write(header);
		outputStream.write(length);
		outputStream.write(body);

		return outputStream.toByteArray();
	}

	private byte[] onGetSpl() throws IOException {
		String xml = IOUtils.toString(this.getClass().getResourceAsStream("/gdc/GET_SHOW.xml"),
		        "UTF-8");
		byte[] header = DatatypeConverter.parseHexBinary("060E2B34020501010F15011100000000");
		byte[] length = util.GdcUtils.getgdcBerLength(xml);
		byte[] body = xml.getBytes();

		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		outputStream.write(header);
		outputStream.write(length);
		outputStream.write(body);

		return outputStream.toByteArray();
	}

	private byte[] onGetCpl() throws IOException {
		String cpl = IOUtils.toString(this.getClass().getResourceAsStream("/gdc/GET_CPL.xml"),
		        "UTF-8");
		String xml = String.format(cpl, title + DCP);
		byte[] header = DatatypeConverter.parseHexBinary("060E2B34020501010F15011100000000");
		byte[] length = util.GdcUtils.getgdcBerLength(xml);
		byte[] body = xml.getBytes();

		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		outputStream.write(header);
		outputStream.write(length);
		outputStream.write(body);

		return outputStream.toByteArray();
	}
}
