/**
 * Copyright (c) 2015 CineApps
 * This source code is the property of CineApps.
 */
package com.cineapps.mediablock.core.connection;

/**
 * Types of protocol error that can happens while communicating with a device
 */
public enum ProtocolError {

	ERROR_NO(0, "ok"), ERROR_FAILED(1, "Responder unable to process request"), ERROR_INVALID(2,
	        "Invalid parameter or command structure"), ERROR_BUSY(3,
	        "Responder too busy to process request"), BAD_RESPONSE(4, "Bad response"), BAD_REQUEST_ID(
	        5, "Bad RequestId"), BAD_REQUEST(6, "Bad Request"), INVALID_PARAMETER(7,
	        "Invalid parameter"), BAD_CHECKSUM(8, "Bad checksum in the response"), UNKNOWN_ERROR(
	        -1, "Unknown error");

	private int code;
	private String label;

	private ProtocolError(int code, String label) {
		this.code = code;
		this.label = label;
	}

	public int getCode() {
		return code;
	}

	public String getLabel() {
		return label;
	}

	public static ProtocolError getProtocolError(int code) {
		for (ProtocolError protocolError : ProtocolError.values()) {
			if (protocolError.getCode() == code) {
				return protocolError;
			}
		}
		return ProtocolError.UNKNOWN_ERROR;
	}
}
