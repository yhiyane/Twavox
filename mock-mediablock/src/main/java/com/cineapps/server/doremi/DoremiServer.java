package com.cineapps.server.doremi;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.io.IOUtils;

import com.cineapps.server.common.MediablockServer;
import com.cineapps.service.VideoStreamStatus;
import com.cineapps.service.VideoStreamWebService;

public class DoremiServer implements MediablockServer {

	// Server variables
	private Selector selector;
	private SocketChannel channel;
	private Map<SocketChannel, List<byte[]>> dataMap = new HashMap<SocketChannel, List<byte[]>>();
	private Logger logger = Logger.getLogger(DoremiServer.class.getName());

	// User Parameters
	private String title;
	private UUID splUuid = UUID.fromString("c23ab440-0294-11e4-9191-0800200c9a66");
	private UUID cplUuid = UUID.fromString("1bf53165-3138-4848-9f35-8e7435772286");
	private final VideoStreamWebService videoStreamWebService = new VideoStreamWebService();
	private final int MOVIE_DURATION_S = 2 * 60 * 60;

	// Parameters
	private final int POSITION_COMMAND_1 = 13;
	private final int POSITION_COMMAND_2 = 14;
	private final int POSITION_COMMAND_3 = 15;

	public DoremiServer(String title) {
		this.title = title;
	}

	private boolean isPlaying() {
		VideoStreamStatus status = videoStreamWebService.getStatus();
		if (status != null) {
			return status.isPlaying();
		}
		return false;
	}

	private int getPosition() {
		VideoStreamStatus status = videoStreamWebService.getStatus();
		if (status != null) {
			return status.getCurrentTime_s();
		}
		return 0;
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
		if (request.length < POSITION_COMMAND_3) {
			throw new IllegalArgumentException("Request no long enough");
		}

		ByteBuffer bb = ByteBuffer.wrap(new byte[] { request[20], request[21], request[22],
		        request[23] });
		int requestId = bb.getInt();

		CommandBinder commandBinder = new Command(request[POSITION_COMMAND_1],
		        request[POSITION_COMMAND_2], request[POSITION_COMMAND_3]).getCommandBinder();
		switch (commandBinder.getRequest()) {
			case GET_STATUS:
				return onGetStatusRequest(commandBinder.getResponse(), requestId);
			case GET_SPL_INFO:
				return onGetSplInfoRequest(commandBinder.getResponse(), requestId);
			case GET_SCHEDULER_ENABLE:
				return onGetSchedulerEnableRequest(commandBinder.getResponse(), requestId);
			case GET_SNMP:
				return onGetSmnpRequest(commandBinder.getResponse(), requestId);
			case GET_SPLS_LIST:
				return onGetSplsListRequest(commandBinder.getResponse(), requestId);
			case RETR_SPL:
				return onRetrSplRequest(commandBinder.getResponse(), requestId);
			case GET_CPLS_LIST:
				return onGetCplsListRequest(commandBinder.getResponse(), requestId);
			case RETR_CPL:
				return onRetrCplRequest(commandBinder.getResponse(), requestId);
		}
		return null;
	}

	private byte[] onGetStatusRequest(Command responseCommand, int requestId) throws IOException {
		int headerSize = 20;
		int responseSize = 86;
		byte[] response = new byte[headerSize + responseSize];
		int offset = 0;
		ByteBuffer bb;

		// header - response command
		response[13] = (byte) responseCommand.getCommand1();
		response[14] = (byte) responseCommand.getCommand2();
		response[15] = (byte) responseCommand.getCommand3();
		// header - length (4 bytes)
		bb = ByteBuffer.allocate(4);
		bb.putInt(responseSize);
		System.arraycopy(bb.array(), 0, response, 16, 4);
		// response - request id (4 bytes)
		bb = ByteBuffer.allocate(4);
		bb.putInt(requestId);
		System.arraycopy(bb.array(), 0, response, headerSize, 4);
		offset += 4;
		// response - playback status (1 byte)
		if (isPlaying()) {
			response[headerSize + offset] = 2;
		} else {
			response[headerSize + offset] = 1;
		}
		offset += 1;
		// response - playlist uuid (16 bytes)
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		DataOutputStream dos = new DataOutputStream(baos);
		dos.writeLong(splUuid.getMostSignificantBits());
		dos.writeLong(splUuid.getLeastSignificantBits());
		dos.flush(); // May not be necessary
		System.arraycopy(baos.toByteArray(), 0, response, headerSize + offset, 16);
		offset += 16;
		// response - position (4 bytes)
		bb = ByteBuffer.allocate(4);
		bb.putInt(getPosition());
		System.arraycopy(bb.array(), 0, response, headerSize + offset, 4);
		offset += 4;
		// response - duration (4 bytes)
		bb = ByteBuffer.allocate(4);
		bb.putInt(MOVIE_DURATION_S);
		System.arraycopy(bb.array(), 0, response, headerSize + offset, 4);
		offset += 4;
		// response - cpl uuid (16 bytes)
		System.arraycopy(baos.toByteArray(), 0, response, headerSize + offset, 16);
		offset += 16;
		// response - event (16 bytes)
		offset += 16;
		// response - element (16 bytes)
		offset += 16;
		// response - current position (4 bytes)
		bb = ByteBuffer.allocate(4);
		bb.putInt(getPosition());
		System.arraycopy(bb.array(), 0, response, headerSize + offset, 4);
		offset += 4;
		// response - current duration (4 bytes)
		bb = ByteBuffer.allocate(4);
		bb.putInt(MOVIE_DURATION_S);
		System.arraycopy(bb.array(), 0, response, headerSize + offset, 4);
		offset += 4;

		return response;
	}

	private byte[] onGetSplInfoRequest(Command responseCommand, int requestId) throws IOException {
		int headerSize = 20;
		int responseSize = 153;
		byte[] response = new byte[headerSize + responseSize];
		ByteBuffer bb;
		int offset = 0;

		// header - response command
		response[13] = (byte) responseCommand.getCommand1();
		response[14] = (byte) responseCommand.getCommand2();
		response[15] = (byte) responseCommand.getCommand3();
		// header - length (4 bytes)
		bb = ByteBuffer.allocate(4);
		bb.putInt(responseSize);
		System.arraycopy(bb.array(), 0, response, 16, 4);
		// response - request id (4 bytes)
		bb = ByteBuffer.allocate(4);
		bb.putInt(requestId);
		System.arraycopy(bb.array(), 0, response, headerSize, 4);
		offset += 4;
		// response - spl uuid (16 bytes)
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		DataOutputStream dos = new DataOutputStream(baos);
		dos.writeLong(splUuid.getMostSignificantBits());
		dos.writeLong(splUuid.getLeastSignificantBits());
		dos.flush(); // May not be necessary
		System.arraycopy(baos.toByteArray(), 0, response, headerSize + offset, 16);
		offset += 16;
		// response - title (128 bytes)
		byte[] titleArray = title.getBytes();
		System.arraycopy(titleArray, 0, response, headerSize + offset, titleArray.length);
		offset += 128;
		// response - duration in frame (4 bytes)
		int nbFrames = 24 * MOVIE_DURATION_S;
		bb = ByteBuffer.allocate(4);
		bb.putInt(nbFrames);
		System.arraycopy(bb.array(), 0, response, headerSize + offset, 4);
		offset += 4;

		return response;
	}

	private byte[] onGetSchedulerEnableRequest(Command responseCommand, int requestId) {
		int headerSize = 20;
		int responseSize = 6;
		byte[] response = new byte[headerSize + responseSize];
		ByteBuffer bb;

		// header - response command
		response[13] = (byte) responseCommand.getCommand1();
		response[14] = (byte) responseCommand.getCommand2();
		response[15] = (byte) responseCommand.getCommand3();
		// header - length (4 bytes)
		bb = ByteBuffer.allocate(4);
		bb.putInt(responseSize);
		System.arraycopy(bb.array(), 0, response, 16, 4);
		// response - request id (4 bytes)
		bb = ByteBuffer.allocate(4);
		bb.putInt(requestId);
		System.arraycopy(bb.array(), 0, response, headerSize, 4);

		return response;
	}

	private byte[] onGetSmnpRequest(Command responseCommand, int requestId) {
		int headerSize = 20;
		int responseSize = 6;
		byte[] response = new byte[headerSize + responseSize];
		ByteBuffer bb;

		// header - response command
		response[13] = (byte) responseCommand.getCommand1();
		response[14] = (byte) responseCommand.getCommand2();
		response[15] = (byte) responseCommand.getCommand3();
		// header - length (4 bytes)
		bb = ByteBuffer.allocate(4);
		bb.putInt(responseSize);
		System.arraycopy(bb.array(), 0, response, 16, 4);
		// response - request id (4 bytes)
		bb = ByteBuffer.allocate(4);
		bb.putInt(requestId);
		System.arraycopy(bb.array(), 0, response, headerSize, 4);

		return response;
	}

	private byte[] onGetSplsListRequest(Command responseCommand, int requestId) throws IOException {
		int headerSize = 20;
		int responseSize = 29;
		byte[] response = new byte[headerSize + responseSize];
		ByteBuffer bb;
		int offset = 0;

		// header - response command
		response[13] = (byte) responseCommand.getCommand1();
		response[14] = (byte) responseCommand.getCommand2();
		response[15] = (byte) responseCommand.getCommand3();
		// header - length (4 bytes)
		bb = ByteBuffer.allocate(4);
		bb.putInt(responseSize);
		System.arraycopy(bb.array(), 0, response, 16, 4);
		// response - request id (4 bytes)
		bb = ByteBuffer.allocate(4);
		bb.putInt(requestId);
		System.arraycopy(bb.array(), 0, response, headerSize, 4);
		offset += 4;
		// response - nb (4 bytes)
		bb = ByteBuffer.allocate(4);
		bb.putInt(1);
		System.arraycopy(bb.array(), 0, response, headerSize + offset, 4);
		offset += 4;
		// response - size of each element (4 bytes)
		bb = ByteBuffer.allocate(4);
		bb.putInt(16);
		System.arraycopy(bb.array(), 0, response, headerSize + offset, 4);
		offset += 4;
		// response - item
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		DataOutputStream dos = new DataOutputStream(baos);
		dos.writeLong(splUuid.getMostSignificantBits());
		dos.writeLong(splUuid.getLeastSignificantBits());
		dos.flush(); // May not be necessary
		System.arraycopy(baos.toByteArray(), 0, response, headerSize + offset, 16);

		return response;
	}

	private byte[] onRetrSplRequest(Command responseCommand, int requestId) throws IOException {
		// String xml = String.format(
		// new String(IOUtils.toByteArray(DoremiServer.class
		// .getResourceAsStream("/resources/spl.xml"))), title);

		String xml = String.format(
		        new String(IOUtils.toByteArray(DoremiServer.class
		                .getResourceAsStream("/doremi/spl.xml"))), title);
		byte[] spl = xml.getBytes();

		int headerSize = 20;
		int responseSize = 5 + spl.length;
		byte[] response = new byte[headerSize + responseSize];
		ByteBuffer bb;

		// header - response command
		response[13] = (byte) responseCommand.getCommand1();
		response[14] = (byte) responseCommand.getCommand2();
		response[15] = (byte) responseCommand.getCommand3();
		// header - length (4 bytes)
		bb = ByteBuffer.allocate(4);
		bb.putInt(responseSize);
		System.arraycopy(bb.array(), 0, response, 16, 4);
		// response - request id (4 bytes)
		bb = ByteBuffer.allocate(4);
		bb.putInt(requestId);
		System.arraycopy(bb.array(), 0, response, headerSize, 4);
		// response - xml
		System.arraycopy(spl, 0, response, headerSize + 4, spl.length);

		return response;
	}

	private byte[] onGetCplsListRequest(Command responseCommand, int requestId) throws IOException {
		int headerSize = 20;
		int responseSize = 29;
		byte[] response = new byte[headerSize + responseSize];
		ByteBuffer bb;
		int offset = 0;

		// header - response command
		response[13] = (byte) responseCommand.getCommand1();
		response[14] = (byte) responseCommand.getCommand2();
		response[15] = (byte) responseCommand.getCommand3();

		// header - length (4 bytes)
		bb = ByteBuffer.allocate(4);
		bb.putInt(responseSize);
		System.arraycopy(bb.array(), 0, response, 16, 4);
		// response - request id (4 bytes)
		bb = ByteBuffer.allocate(4);
		bb.putInt(requestId);
		System.arraycopy(bb.array(), 0, response, headerSize, 4);
		offset += 4;
		// response - nb (4 bytes)
		bb = ByteBuffer.allocate(4);
		bb.putInt(1);
		System.arraycopy(bb.array(), 0, response, headerSize + offset, 4);
		offset += 4;
		// response - size of each element (4 bytes)
		bb = ByteBuffer.allocate(4);
		bb.putInt(16);
		System.arraycopy(bb.array(), 0, response, headerSize + offset, 4);
		offset += 4;
		// response - item
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		DataOutputStream dos = new DataOutputStream(baos);
		dos.writeLong(cplUuid.getMostSignificantBits());
		dos.writeLong(cplUuid.getLeastSignificantBits());
		dos.flush(); // May not be necessary
		System.arraycopy(baos.toByteArray(), 0, response, headerSize + offset, 16);

		return response;
	}

	private byte[] onRetrCplRequest(Command responseCommand, int requestId) throws IOException {
		// String xml = String.format(
		// new String(IOUtils.toByteArray(DoremiServer.class
		// .getResourceAsStream("/resources/cpl.xml"))), title);

		String xml = String.format(
		        new String(IOUtils.toByteArray(DoremiServer.class
		                .getResourceAsStream("/doremi/cpl.xml"))), title);
		byte[] cpl = xml.getBytes();

		int headerSize = 20;
		int responseSize = 5 + cpl.length;
		byte[] response = new byte[headerSize + responseSize];
		ByteBuffer bb;

		// header - response command
		response[13] = (byte) responseCommand.getCommand1();
		response[14] = (byte) responseCommand.getCommand2();
		response[15] = (byte) responseCommand.getCommand3();
		// header - length (4 bytes)
		bb = ByteBuffer.allocate(4);
		bb.putInt(responseSize);
		System.arraycopy(bb.array(), 0, response, 16, 4);
		// response - request id (4 bytes)
		bb = ByteBuffer.allocate(4);
		bb.putInt(requestId);
		System.arraycopy(bb.array(), 0, response, headerSize, 4);
		// response - xml
		System.arraycopy(cpl, 0, response, headerSize + 4, cpl.length);

		return response;
	}
}
