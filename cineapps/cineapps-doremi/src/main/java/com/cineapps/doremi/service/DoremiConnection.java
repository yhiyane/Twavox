/**
 * Copyright (c) 2011 Dvidea
 * This source code is the property of Dvidea.
 */
package com.cineapps.doremi.service;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import org.apache.log4j.Logger;

import com.cineapps.doremi.utils.KLVDoremi;
import com.cineapps.mediablock.core.connection.ConnectionException;
import com.cineapps.mediablock.core.connection.IntelligentSocketConnection;
import com.cineapps.mediablock.core.connection.ProtocolError;
import com.cineapps.mediablock.core.connection.ProtocolException;
import com.cineapps.mediablock.core.logging.ErrorCommunicationLogger;
import com.cineapps.mediablock.core.utils.Utils;

/**
 * Manages a connection to a Doremi device.
 */
public class DoremiConnection extends IntelligentSocketConnection {
	private static final int HEADER_KEY_LENGTH = 16;
	private static final int LENGTH_LENGTH = 4;
	private static final int REQUESTID_LENGTH = 4;
	private static final int STATUS_LENGTH = 1;
	private static final int FIRST_REQUEST_ID = 1000;
	private static final byte[] HEADER_OUT = new byte[] { 6, 0xE, 0x2B, 0x34, 2, 5, 1, 0xA, 0xE,
	        0x10, 1, 1, 1 };
	// Some command take between 30 and 100 second or more to give an answer.
	// waiting for less time cause a ReadTimeOut error.
	// As this timeout is not related to a "connexion" test of the socket, we
	// can set it to 0 to avoid errors.
	public static final int TIMEOUT_DEFAULT_DOREMI_IN_MS = 0;

	private static final Logger logger = ErrorCommunicationLogger.getLogger(DoremiConnection.class);

	private Integer nextRequestIdToUse = FIRST_REQUEST_ID;

	public DoremiConnection(String ipAddress, int port) throws ConnectionException {
		super(ipAddress, port);
	}

	public DoremiConnection(String ipAddress, int port, boolean connect) throws ConnectionException {
		super(ipAddress, port, connect);
	}

	public DoremiConnection(String ipAddress, int port, boolean connect, int timeoutms)
	        throws ConnectionException {
		super(ipAddress, port, connect, timeoutms);
	}

	/**
	 * Sends a command to the Doremi and gets the result. The result can
	 * eventually be an empty byte array.
	 * 
	 * @param commandByte1
	 *            the first byte of the command
	 * @param commandByte2
	 *            the second byte of the command
	 * @param commandByte3
	 *            the third byte of the command
	 * @return the response from the Doremi, truncated from the useless bytes.
	 *         The response only contains the bytes containing the data we are
	 *         expecting to get from the device.
	 * @throws ConnectionException
	 *             if if any I/O error happens
	 * @throws ProtocolException
	 *             if any error within the protocol happens (bad request id,
	 *             wrong response header...)
	 */
	public byte[] doRequest(int commandByte1, int commandByte2, int commandByte3)
	        throws ConnectionException, ProtocolException {
		return doRequest(commandByte1, commandByte2, commandByte3, null);
	}

	/**
	 * Sends a command to the Doremi and gets the result. The result can
	 * eventually be an empty byte array.
	 * 
	 * @param commandByte1
	 *            the first byte of the command
	 * @param commandByte2
	 *            the second byte of the command
	 * @param commandByte3
	 *            the third byte of the command
	 * @param commandData
	 *            the data to send along with the command
	 * @return the response from the Doremi, truncated from the useless bytes.
	 *         The response only contains the bytes containing the data we are
	 *         expecting to get from the device.
	 * @throws ConnectionException
	 *             if if any I/O error happens
	 * @throws ProtocolException
	 *             if any error within the protocol happens (bad request id,
	 *             wrong response header...)
	 */
	public byte[] doRequest(int commandByte1, int commandByte2, int commandByte3, byte[] commandData)
	        throws ConnectionException, ProtocolException {
		return doRequest(commandByte1, commandByte2, commandByte3, commandData,
		        TIMEOUT_DEFAULT_DOREMI_IN_MS);
	}

	public byte[] doRequest(int commandByte1, int commandByte2, int commandByte3,
	        byte[] commandData, int timeOutForStoreMethod) throws ConnectionException,
	        ProtocolException {
		int requestId;
		byte[] response;
		synchronized (nextRequestIdToUse) {
			requestId = nextRequestIdToUse;
			nextRequestIdToUse++;
			byte[] toWrite = setOutputData(commandByte1, commandByte2, commandByte3, commandData,
			        requestId);
			response = sendCommandAndGetResult(toWrite, timeOutForStoreMethod);

		}

		// process the response to check the header, the request id
		// the general contract of the response is : HEADER - LENGTH - REQUESTID
		// - RESPONSE
		// first check the header
		if (response == null) {
			logger.error("Response was null. Command was : " + commandByte1 + " | " + commandByte2
			        + " | " + commandByte3 + " ...");
			throw new ProtocolException(ProtocolError.BAD_RESPONSE);
		}
		if (response.length < HEADER_KEY_LENGTH + LENGTH_LENGTH + REQUESTID_LENGTH + STATUS_LENGTH) {
			throw new ProtocolException(ProtocolError.BAD_RESPONSE);
		}
		if (response[HEADER_KEY_LENGTH - 3] != (byte) commandByte1) {
			throw new ProtocolException(ProtocolError.BAD_RESPONSE);
		}
		if (KLVDoremi.getByteAsInt((byte) commandByte2) != (KLVDoremi
		        .getByteAsInt(response[HEADER_KEY_LENGTH - 2]) - 1)) {
			throw new ProtocolException(ProtocolError.BAD_RESPONSE);
		}
		if (response[HEADER_KEY_LENGTH - 1] != (byte) commandByte3) {
			throw new ProtocolException(ProtocolError.BAD_RESPONSE);
		}

		// then check the request id
		byte[] requestIdBytes = new byte[REQUESTID_LENGTH];
		System.arraycopy(response, HEADER_KEY_LENGTH + LENGTH_LENGTH, requestIdBytes, 0,
		        requestIdBytes.length);
		int receivedRequestId = KLVDoremi.getBytesAsInt(requestIdBytes);
		if (logger.isDebugEnabled()) {
			logger.debug("Request ID = " + receivedRequestId + ", expected :" + requestId);
		}
		if (receivedRequestId != requestId) {
			throw new ProtocolException(ProtocolError.BAD_REQUEST_ID);
		}

		// check the response status (this is the last byte of the response)
		byte[] data = new byte[STATUS_LENGTH];
		System.arraycopy(response, response.length - STATUS_LENGTH, data, 0, data.length);
		if (logger.isDebugEnabled()) {
			logger.debug("reponse status = " + data[0]);
		}
		if (((int) data[0]) != ProtocolError.ERROR_NO.getCode()) {
			throw new ProtocolException((int) data[0]);
		}

		// return the response (truncated of the HEADER, the LENGTH, the REQUEST
		// ID and the RESPONSE STATUS
		byte[] usefulResponseToReturn = new byte[response.length - HEADER_KEY_LENGTH
		        - LENGTH_LENGTH - REQUESTID_LENGTH - STATUS_LENGTH];
		System.arraycopy(response, HEADER_KEY_LENGTH + LENGTH_LENGTH + REQUESTID_LENGTH,
		        usefulResponseToReturn, 0, usefulResponseToReturn.length);
		return usefulResponseToReturn;

	}

	@Override
	protected byte[] readResponse() throws IOException {
		// first read the header
		setResponseLength(HEADER_KEY_LENGTH);
		byte[] headerBytes = super.readResponse();
		if (logger.isDebugEnabled()) {
			logger.debug("[readResponse] key received = " + KLVDoremi.getBytestoPrint(headerBytes));
		}
		// then read the length parameter
		setResponseLength(LENGTH_LENGTH);
		byte[] lengthBytes = super.readResponse();
		int length = KLVDoremi.getLengthAsInt(lengthBytes);
		if (logger.isDebugEnabled()) {
			logger.debug("[readResponse] length received = "
			        + KLVDoremi.getBytestoPrint(lengthBytes) + " -> " + length);
		}
		byte[] response = new byte[0];
		if (length > 0) {
			// then read the rest of the response
			setResponseLength(length);
			response = super.readResponse();
		}

		byte[] completeResponse = Utils.merge(headerBytes, lengthBytes, response);
		return completeResponse;
	}

	/**
	 * Constructs the byte array to send to the device for this command.
	 * 
	 * @param commandByte1
	 *            the first byte of the command
	 * @param commandByte2
	 *            the second byte of the command
	 * @param commandByte3
	 *            the third byte of the command
	 * @param commandData
	 *            the data to send along with the command
	 * @param requestId
	 *            the id of the request
	 * @return the bytes to send to the Doremi
	 */
	private byte[] setOutputData(int commandByte1, int commandByte2, int commandByte3,
	        byte[] commandData, int requestId) {
		KLVDoremi klv = new KLVDoremi();

		// construct the header from the static HEADER_OUT field + the 3 command
		// bytes
		byte[] fullHeaderOut = Utils.merge(HEADER_OUT, new byte[] { (byte) commandByte1 },
		        new byte[] { (byte) commandByte2 }, new byte[] { (byte) commandByte3 });
		klv.setKey(fullHeaderOut);

		ByteArrayOutputStream bos = new ByteArrayOutputStream(LENGTH_LENGTH);
		DataOutputStream dos = new DataOutputStream(bos);
		try {
			dos.writeInt(requestId);
		} catch (IOException e) {
			// it should not occur as we are writing in a byte array
		}
		byte[] tmp = new byte[LENGTH_LENGTH + ((commandData == null) ? 0 : commandData.length)];
		System.arraycopy(bos.toByteArray(), 0, tmp, 0, LENGTH_LENGTH);
		if (commandData != null) {
			System.arraycopy(commandData, 0, tmp, LENGTH_LENGTH, commandData.length);
		}
		klv.setValue(tmp);
		if (logger.isDebugEnabled()) {
			logger.debug("Output data = " + KLVDoremi.getBytestoPrint(klv.toBytes()));
		}
		return klv.toBytes();
	}

}
