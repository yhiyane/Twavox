/**
 * Copyright (c) 2015 CineApps
 * This source code is the property of CineApps.
 */
package com.cineapps.mediablock.core.connection;

/**
 * Error in the communication with a device
 */
public class ProtocolException extends Exception {

	/**
	 * The {@link ProtocolError} related to this exception
	 */
	private ProtocolError protocolError;
	private static final long serialVersionUID = 4866860150342932415L;

	public ProtocolException() {
		super();
	}

	public ProtocolException(String message) {
		super(message);
	}

	public ProtocolException(Throwable cause) {
		super(cause);
	}

	public ProtocolException(String message, Throwable cause) {
		super(message, cause);
	}

	public ProtocolException(ProtocolError protocolError) {
		super(protocolError.getLabel());
		this.protocolError = protocolError;
	}

	public ProtocolException(int protocolError) {
		super(ProtocolError.getProtocolError(protocolError).getLabel());
		this.protocolError = ProtocolError.getProtocolError(protocolError);
	}

	public ProtocolError getProtocolError() {
		return protocolError;
	}

	public void setProtocolError(ProtocolError protocolError) {
		this.protocolError = protocolError;
	}
}
