/**
 * Copyright (c) 2015 CineApps
 * This source code is the property of CineApps.
 */
package com.cineapps.mediablock.core.connection;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketException;
import java.util.Arrays;

import org.apache.log4j.Logger;

import com.cineapps.mediablock.core.logging.ErrorCommunicationLogger;
import com.cineapps.mediablock.core.utils.Pair;
import com.cineapps.mediablock.core.utils.Utils;

/**
 * This classes sets an intelligent socket connection with the following
 * specificities:
 * <ul>
 * <li>in case of communication error, it tries to reconnect.</li>
 * <li>only one command at a time can be sent.</li>
 * <li>a command that is sent is waiting for a response of the remote device</li>
 * </ul>
 */
public class IntelligentSocketConnection {
	private static final int EOF = -1;
	/** Each Character of this arrays express the end of the response */
	private byte[] stopBytes;
	private String address;
	private int port;
	private Socket socket;
	private InputStream socketInputStream;
	private OutputStream socketOutputStream;
	private BufferedInputStream socketBufferedInputStream;
	private BufferedOutputStream socketBufferedOutputStream;
	private boolean ongoingCommand = false;
	private static final int DEFAULT_TIMEOUT = 20000;
	private final int finalTimeout;
	private int responseLength = 8192;
	private static final Logger logger = ErrorCommunicationLogger
	        .getLogger(IntelligentSocketConnection.class);

	/**
	 * Constructs a new connection to the given IP address using the given port.
	 * The default value of the timeout will be 3000 milliseconds. The default
	 * length of the responses will be 8192 bytes (8k0). The connection will be
	 * established at the end. If no connection can be established, a
	 * {@link ConnectionException} will be thrown
	 * 
	 * @param ipAddress
	 *            the IP address to connect to.
	 * @param port
	 *            the port to use for the connection
	 * @throws ConnectionException
	 *             if any connection error occurs
	 */
	public IntelligentSocketConnection(String ipAddress, int port) throws ConnectionException {
		this(ipAddress, port, true);
	}

	/**
	 * Constructs a new connection to the given IP address using the given
	 * port.The default length of the responses will be 8192 bytes (8k0). The
	 * connection will be established at the end. If no connection can be
	 * established, a {@link ConnectionException} will be thrown
	 * 
	 * @param ipAddress
	 *            the IP address to connect to.
	 * @param port
	 *            the port to use for the connection
	 * @param socketTimeOut
	 *            override the default value for the timeout.
	 * @throws ConnectionException
	 *             if any connection error occurs
	 */
	public IntelligentSocketConnection(String ipAddress, int port, int socketTimeOut)
	        throws ConnectionException {
		this(ipAddress, port, true, socketTimeOut);
	}

	/**
	 * Constructs a new connection to the given IP address using the given port.
	 * The default value of the timeout will be 3000 milliseconds. The default
	 * length of the responses will be 8192 bytes (8k0). If indicated, the
	 * connection will be established at the end. If no connection can be
	 * established, a {@link ConnectionException} will be thrown. This
	 * constructor enables to construct a new connection object even if the
	 * remote address is not alive at the time of the construction of the
	 * {@link IntelligentSocketConnection}.
	 * 
	 * @param ipAddress
	 *            the IP address to connect to.
	 * @param port
	 *            the port to use for the connection
	 * @param connect
	 *            if true, the connection will be established at the end.
	 * @throws ConnectionException
	 *             if any connection error occurs
	 */
	public IntelligentSocketConnection(String ipAddress, int port, boolean connect)
	        throws ConnectionException {
		this(ipAddress, port, connect, DEFAULT_TIMEOUT);
	}

	/**
	 * Constructs a new connection to the given IP address using the given
	 * port.The default length of the responses will be 8192 bytes (8k0). If
	 * indicated, the connection will be established at the end. If no
	 * connection can be established, a {@link ConnectionException} will be
	 * thrown. This constructor enables to construct a new connection object
	 * even if the remote address is not alive at the time of the construction
	 * of the {@link IntelligentSocketConnection}.
	 * 
	 * @param ipAddress
	 *            the IP address to connect to.
	 * @param port
	 *            the port to use for the connection
	 * @param connect
	 *            if true, the connection will be established at the end.
	 * @param socketTimeOut
	 *            set the value in millisecond of the socket timeout.
	 * @throws ConnectionException
	 *             if any connection error occurs
	 */
	public IntelligentSocketConnection(String ipAddress, int port, boolean connect,
	        int socketTimeOut) throws ConnectionException {
		address = ipAddress;
		finalTimeout = socketTimeOut;
		this.port = port;
		if (connect) {
			connect();
		}
	}

	/**
	 * set a new valid timeout value for a instanced socket. Valid is : non-null
	 * and >= 0.
	 * 
	 * @param newTimeout
	 *            new Value of the timeout. have to be >0
	 * @return true if the value of the timeout is equals to newTimeout at the
	 *         end of the call.
	 * @throws ConnectionException
	 */
	private boolean setTimeout(int newTimeout) throws ConnectionException {
		if (socket == null) {
			logger.error("Unable to set the new timeout : null socket");
			return false;
		}
		try {
			if (newTimeout >= 0) {
				if (socket.getSoTimeout() != newTimeout) {
					this.socket.setSoTimeout(newTimeout);
					return true;
				}
				logger.debug("Timeout unchanged : the socket has already the same value");
				return false;
			}
			logger.error("Unable to set the new timeout : invalid timeout. Asked timeout was : "
			        + newTimeout);
			return false;
		} catch (SocketException e) {
			throw new ConnectionException("[Connection error during change of timeout. Address :  "
			        + address + "]", e);
		}

	}

	/**
	 * @return The actual timeout of the Socket. Nb : Timeout cannot be set,
	 *         rather create a new socket from scratch. But for a punctual need
	 *         of longer timeout, the sendCommand methods have optional
	 *         parameter. It that latter case, the
	 *         {@link IntelligentSocketConnection} manage the timeout. ( set a
	 *         new one, then re-set the old one after the call )
	 */
	public int getTimeout() {
		return finalTimeout;
	}

	/**
	 * Gets the length of the responses returned by this connection.
	 * 
	 * @return the responseLength as a number of bytes
	 */
	public int getResponseLength() {
		return responseLength;
	}

	/**
	 * Sets the the length of the responses returned by this connection.
	 * 
	 * @param responseLength
	 *            the responseLength to set as a number of bytes.
	 */
	public void setResponseLength(int responseLength) {
		this.responseLength = responseLength;
	}

	/**
	 * set the stopBytes arrays as the list of bytes this connection will
	 * consider to stop reading response. The response will be cut after the
	 * first stop byte found in this arrays. Stop byte are handled before the
	 * EOF char.
	 * 
	 * @param blockingChar
	 *            , will be cast in a byte arrays.
	 */
	public void setStopBytes(char[] stopBytes) {
		if (stopBytes == null) {
			stopBytes = null;
			return;
		}
		byte[] toSet = new byte[stopBytes.length];
		for (char i = 0; i < stopBytes.length; i++) {
			toSet[i] = (byte) stopBytes[i];
		}
		this.stopBytes = toSet;
	}

	/**
	 * set the stopBytes arrays as the list of bytes this connection will
	 * consider to stop reading response. The response will be cut after the
	 * first stop byte found in this arrays. Stop byte are handled before the
	 * EOF char.
	 * 
	 * @param blockingChar
	 */
	public void setStopByte(char stopByte) {
		this.stopBytes = new byte[] { (byte) stopByte };
	}

	/**
	 * set the stopBytes arrays as the list of bytes this connection will
	 * consider to stop reading response. The response will be cut after the
	 * first stop byte found in this arrays. Stop byte are handled before the
	 * EOF char.
	 * 
	 * @param stopBytes
	 */
	public void setStopBytes(byte[] stopBytes) {
		this.stopBytes = stopBytes;
	}

	/**
	 * set stopByte as the unique stop byte for this connection. Stop byte are
	 * handled before the EOF char.
	 * 
	 * @param stopByte
	 */
	public void setStopBytes(byte stopByte) {
		this.stopBytes = new byte[] { stopByte };
	}

	public void setStopBytes(String stopByte) {
		if (stopByte == null || stopByte.isEmpty()) {
			this.stopBytes = null;
			return;
		}
		this.stopBytes = stopByte.getBytes();
	}

	/**
	 * Establishes a connection with the remote address. If a connection was
	 * already established, deconnection - reconnection is performed.
	 * 
	 * @throws ConnectionException
	 *             if any connection error occurs
	 */
	public void connect() throws ConnectionException {
		if (socket != null || socketInputStream != null || socketOutputStream != null
		        || socketBufferedOutputStream != null || socketBufferedInputStream != null) {
			closeConnection();
		}
		try {
			socket = new Socket();
			socket.setSoTimeout(finalTimeout);
			socket.connect(new InetSocketAddress(address, port), finalTimeout);
			socketOutputStream = socket.getOutputStream();
			socketInputStream = socket.getInputStream();
			socketBufferedOutputStream = new BufferedOutputStream(socketOutputStream);
			socketBufferedInputStream = new BufferedInputStream(socketInputStream);
		} catch (Exception exception) {
			throw new ConnectionException("[Connection error to  " + address + "]", exception);
		}
	}

	/**
	 * Closes the connection that has been previously established
	 */
	public void closeConnection() {
		try {
			socketBufferedOutputStream.close();
		} catch (IOException e) {
			if (logger.isDebugEnabled()) {
				logger.debug("[closeConnection  " + address
				        + "] Unable to close the buffered socket output stream.");
			}
		}
		try {
			socketBufferedInputStream.close();
		} catch (IOException e) {
			if (logger.isDebugEnabled()) {
				logger.debug("[closeConnection  " + address
				        + "] Unable to close the buffered socket input stream.");
			}
		}
		try {
			socketOutputStream.close();
		} catch (IOException e) {
			if (logger.isDebugEnabled()) {
				logger.debug("[closeConnection  " + address
				        + "] Unable to close the socket output stream.");
			}
		}
		try {
			socketInputStream.close();
		} catch (IOException e) {
			if (logger.isDebugEnabled()) {
				logger.debug("[closeConnection  " + address
				        + "] Unable to close the socket input stream.");
			}
		}
		try {
			socket.close();
		} catch (IOException e) {
			if (logger.isDebugEnabled()) {
				logger.debug("[closeConnection  " + address + "] Unable to close the socket.");
			}
		}
	}

	@Override
	protected void finalize() throws Throwable {
		super.finalize();
		// close the connection before destroying this object
		closeConnection();
	}

	/**
	 * Sends the given command to the socket without waiting for any response
	 * from the remote address.
	 * 
	 * @param bytesToSend
	 *            the command to send
	 * @throws ConnectionException
	 *             if any I/O error happens
	 * @throws ProtocolException
	 *             if any error happens in the protocol (bad request for example
	 *             or connection too busy)
	 */
	public void sendCommand(byte[] bytesToSend) throws ConnectionException, ProtocolException {
		// get the bytes from the string to send
		if (logger.isDebugEnabled()) {
			logger.debug("[sendCommand " + address + "] command to send: "
			        + new String(bytesToSend));
		}
		// send the command
		sendCommand(bytesToSend, false, finalTimeout);
	}

	/**
	 * Sends the given command to the socket without waiting for any response
	 * from the remote address.
	 * 
	 * @param bytesToSend
	 *            the command to send
	 * @param one
	 *            shot timeout.
	 * @throws ConnectionException
	 *             if any I/O error happens
	 * @throws ProtocolException
	 *             if any error happens in the protocol (bad request for example
	 *             or connection too busy)
	 */
	public void sendCommand(byte[] bytesToSend, int timeout) throws ConnectionException,
	        ProtocolException {
		// get the bytes from the string to send
		if (logger.isDebugEnabled()) {
			logger.debug("[sendCommand " + address + "] command to send: "
			        + new String(bytesToSend));
		}
		// send the command
		sendCommand(bytesToSend, false, timeout);
	}

	/**
	 * Sends the given command to the socket and gets the response from the
	 * remote address.
	 * 
	 * @param bytesToSend
	 *            the command to send
	 * @param timeout
	 *            to set for this call only
	 * @return the response from the remote address as an array of bytes which
	 *         length is the result of {@link #getResponseLength()}. If the
	 *         response is shorter than this, only the first bytes will be set.
	 *         If the response is longer than this, only the first bytes of the
	 *         response will be retrieved.
	 * @throws ConnectionException
	 *             if any I/O error happens
	 * @throws ProtocolException
	 *             if any error happens in the protocol (bad request for example
	 *             or connection too busy)
	 */
	public byte[] sendCommandAndGetResult(byte[] bytesToSend, int timeout)
	        throws ConnectionException, ProtocolException {
		// get the bytes from the string to send
		if (logger.isDebugEnabled()) {
			logger.debug("[sendCommandAndGetResult " + address + "] command to send: "
			        + new String(bytesToSend));
		}
		// send the command and get the result
		byte[] result = sendCommand(bytesToSend, true, timeout);
		return result;
	}

	/**
	 * Sends the given command to the socket and gets the response from the
	 * remote address.
	 * 
	 * @param bytesToSend
	 *            the command to send
	 * @return the response from the remote address as an array of bytes which
	 *         length is the result of {@link #getResponseLength()}. If the
	 *         response is shorter than this, only the first bytes will be set.
	 *         If the response is longer than this, only the first bytes of the
	 *         response will be retrieved.
	 * @throws ConnectionException
	 *             if any I/O error happens
	 * @throws ProtocolException
	 *             if any error happens in the protocol (bad request for example
	 *             or connection too busy)
	 */
	public byte[] sendCommandAndGetResult(byte[] bytesToSend) throws ConnectionException,
	        ProtocolException {
		return sendCommandAndGetResult(bytesToSend, finalTimeout);
	}

	/**
	 * Low level method to send a command through the connection opened and get
	 * a result. Only one command at a time can be sent. If a command is already
	 * in process (sending or waiting for the answer), the new command to send
	 * will be waiting for the first one to be finished (thread-safe). You
	 * should call {@link #sendCommandAndGetResult(byte[], boolean)} instead of
	 * directly call this method.
	 * 
	 * @param bytesToSend
	 *            the array of bytes forming the command to send
	 * @param waitForResponse
	 *            if the method should wait for a response from the other side
	 * @param timeout
	 *            eventual custum timeout for this call only. (
	 * @return the array of bytes forming the response or null if
	 *         waitForResponse parameter was set to false
	 * @throws ConnectionException
	 *             if any I/O error happens
	 * @throws ProtocolException
	 *             if any error happens in the protocol (bad request for example
	 *             or connection too busy)
	 */
	private byte[] sendCommand(byte[] bytesToSend, boolean waitForResponse, int timeout)
	        throws ConnectionException, ProtocolException {
		byte[] result = null;
		try {
			// get a lock
			if (logger.isDebugEnabled()) {
				logger.debug("[sendCommandAndGetResult " + address
				        + "] waiting for the lock to be released");
			}
			if (!getLockOnCommand()) {
				throw new ProtocolException(ProtocolError.ERROR_BUSY);
			}
			if (logger.isDebugEnabled()) {
				logger.debug("[sendCommandAndGetResult " + address + "] got a lock on command!");
			}

			try {
				setTimeout(timeout);

				// write the command to send in the socket and read the response
				// (if we have to)
				writeData(bytesToSend);
				if (waitForResponse) {
					result = readResponse();
				}
			} catch (IOException ioexception) {
				if (logger.isDebugEnabled()) {
					logger.debug("first i/o error: " + ioexception.getMessage(), ioexception);
				}
				// there was an error when sending the command or receiving the
				// response, let's try
				// to reconnect
				connect();

				// try now to resend the command
				try {
					setTimeout(timeout);
					writeData(bytesToSend);
					if (waitForResponse) {
						result = readResponse();
					}
					setTimeout(finalTimeout);
				} catch (IOException e) {
					if (logger.isDebugEnabled()) {
						logger.debug("second i/o error: " + e.getMessage(), e);
					}
					throw e;
				}
			}
			return result;
		} catch (IOException e) {
			// we close the connection as an error occurred
			closeConnection();
			throw new ConnectionException("[error  " + address + "]", e);
		} catch (ConnectionException e) {
			// we close the connection as an error occurred
			closeConnection();
			throw e;
		} catch (ProtocolException e) {
			// we close the connection as an error occurred
			closeConnection();
			throw e;
		} catch (Throwable t) {
			logger.error("Error while sending a command", t);
			ProtocolException protocolException = new ProtocolException(t);
			protocolException.setProtocolError(ProtocolError.UNKNOWN_ERROR);
			throw protocolException;
		} finally {
			// the lock must absolutely be released, otherwise the plugin will
			// be blocked
			releaseLockOnCommand();
			if (logger.isDebugEnabled()) {
				logger.debug("[sendCommandAndGetResult " + address + "] lock released");
			}
		}
	}

	/**
	 * Writes the given data in the output of the socket. This method can be
	 * overridden by an inherited class to do some operations before sending the
	 * bytes.
	 * 
	 * @param bytesToSend
	 *            the array of bytes to send
	 * @throws IOException
	 *             if any I/O error occurs
	 */
	protected void writeData(byte[] bytesToSend) throws IOException {
		// send the bytes to the socket
		if (logger.isDebugEnabled()) {
			logger.debug("[writeData " + address + "] writing the data in the socket");
		}
		socketBufferedOutputStream.write(bytesToSend);
		socketBufferedOutputStream.flush();
		if (logger.isDebugEnabled()) {
			logger.debug("[writeData " + address + "] data written in the socket");
		}
	}

	/**
	 * Inherited classes can call this method to get the output stream in order
	 * to fully rewrite the {@link #writeData(byte[])} method. If the connection
	 * is closed, writing in the stream is expected to fail.
	 * 
	 * @return the input stream of the socket
	 */
	protected OutputStream getSocketOutputStream() {
		return socketBufferedOutputStream;
	}

	/**
	 * Reads the response in the input of the socket. This method can be
	 * overridden by an inherited class to do some operations before returning
	 * the response.
	 * 
	 * @return the response from the remote address as an array of bytes which
	 *         length is the result of {@link #getResponseLength()}. If the
	 *         response is shorter than this, only the first bytes will be set.
	 *         If the response is longer than this, only the first bytes of the
	 *         response will be retrieved. The response will be read only until
	 *         the first stop byte found (see {@link #setStopBytes(char[])}).
	 * @throws IOException
	 *             if any I/O error occurs
	 */
	protected byte[] readResponse() throws IOException {
		// receive the response
		if (logger.isDebugEnabled()) {
			logger.debug("[readResponse " + address
			        + "] reading the response. The response should have a size of "
			        + responseLength + " bytes");
		}
		int bytesAlreadyRead = 0;
		boolean eofReached = false;
		byte[] response = null;
		// This while is here to handle a slow response.
		while (bytesAlreadyRead < responseLength && !eofReached) {
			byte[] receivedBytes = new byte[responseLength - bytesAlreadyRead];
			int bytesRead = socketBufferedInputStream.read(receivedBytes);
			// see if the end of the input is reached
			Pair<Boolean, byte[]> res = searchForStopByte(receivedBytes);
			if (res != null && res.getFirst()) {
				eofReached = true;
				receivedBytes = res.getSecond();
			} else if (bytesRead == EOF) {
				eofReached = true;
				// truncate the received bytes to keep only the bytes before the
				// EOF
				// get the index of -1
				int indexOfEOF = 0;
				while (indexOfEOF < receivedBytes.length && receivedBytes[indexOfEOF] != EOF) {
					indexOfEOF++;
				}
				// do the truncation
				if (indexOfEOF < receivedBytes.length) {
					receivedBytes = Arrays.copyOfRange(receivedBytes, 0, indexOfEOF);
				}
			} else {
				logger.debug("number of bytes read = " + bytesRead);
				// truncate the received bytes to keep only the bytes actually
				// read
				receivedBytes = Arrays.copyOfRange(receivedBytes, 0, bytesRead);
				bytesAlreadyRead += bytesRead;
			}
			// merge the new bytes in the response ( it's ok if one of the
			// arrays is null )
			response = Utils.merge(response, receivedBytes);
		}
		if (logger.isDebugEnabled()) {
			logger.debug("[readResponse " + address + "] response from the remote device: "
			        + new String(response));
		}
		return response;
	}

	/**
	 * Returns all bytes until the first stop byte found (see
	 * {@link #setStopBytes(char[])}).
	 * 
	 * @param bytesToScan
	 *            the bytes to scan
	 * @return a {@link Pair} object indicating if a stop byte has been found
	 *         (first object is then true) and the extract of the bytes until
	 *         the first stop byte (the whole array if no stop byte has been
	 *         found).
	 */
	private Pair<Boolean, byte[]> searchForStopByte(byte[] bytesToScan) {
		if (bytesToScan == null || stopBytes == null || bytesToScan.length == 0
		        || stopBytes.length == 0) {
			return Pair.of(false, bytesToScan);
		} else {
			int minimumIndex = Integer.MAX_VALUE;
			for (int i = 0; i < stopBytes.length; i++) {
				// search for the index of this stop byte in the bytes to scan
				// we do not search further than the first stop byte that we
				// have found
				for (int j = 0; j < bytesToScan.length && j < minimumIndex; j++) {
					if (bytesToScan[j] == stopBytes[i]) {
						minimumIndex = j;
					}
				}
			}
			if (minimumIndex < bytesToScan.length) {
				// if we have found at least one stop byte
				return Pair.of(true, Arrays.copyOfRange(bytesToScan, 0, minimumIndex + 1));
			} else {
				return Pair.of(false, bytesToScan);
			}
		}
	}

	/**
	 * Inherited classes can call this method to get the input stream in order
	 * to fully rewrite the {@link #readResponse()} method. If the connection is
	 * closed, reading in the stream is expected to fail.
	 * 
	 * @return the input stream of the socket
	 */
	protected InputStream getSocketInputStream() {
		return socketBufferedInputStream;
	}

	/**
	 * Gives a lock for sending a command to the Barco. Every command sending a
	 * command to the barco projector should call this method before. If a
	 * command is already sent to the barco, the new one to send will wait for
	 * the first one to be finished before sending the new one. {
	 * {@link #releaseLockOnCommand()} must absolutely be called once finished.
	 * 
	 * @return true if the lock could be obtained, false otherwise.
	 */
	private synchronized boolean getLockOnCommand() {
		while (ongoingCommand) {
			try {
				wait();
			} catch (InterruptedException e) {
				return false;
			}
		}
		ongoingCommand = true;
		return true;
	}

	/**
	 * Releases a lock previously obtained to let other commands to be sent.
	 * This method must absolutely be called once finished when a lock was
	 * obtained.
	 */
	private synchronized void releaseLockOnCommand() {
		ongoingCommand = false;
		notifyAll();
	}

	/* #################################################################### All
	 * the following getters and setters have been added for testing purpose
	 * only /*
	 * ################################################################### */
	protected byte[] getStopBytes() {
		return stopBytes;
	}

	protected void setSocket(Socket socket) {
		this.socket = socket;
	}

	protected Socket getSocket() {
		return this.socket;
	}

	protected void setSocketInputStream(InputStream socketInputStream) {
		this.socketInputStream = socketInputStream;
	}

	protected void setSocketOutputStream(OutputStream socketOutputStream) {
		this.socketOutputStream = socketOutputStream;
	}

	protected void setSocketBufferedInputStream(BufferedInputStream socketBufferedInputStream) {
		this.socketBufferedInputStream = socketBufferedInputStream;
	}

	protected BufferedInputStream getSocketBufferedInputStream() {
		return this.socketBufferedInputStream;
	}

	protected void setSocketBufferedOutputStream(BufferedOutputStream socketBufferedOutputStream) {
		this.socketBufferedOutputStream = socketBufferedOutputStream;
	}

	protected BufferedOutputStream getSocketBufferedOutputStream() {
		return this.socketBufferedOutputStream;
	}

	protected boolean getOnGoingCommand() {
		return this.ongoingCommand;
	}
	/* ############ End of getters and setters added for test
	 * ########################## */
}