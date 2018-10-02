package com.cineapps.server.doremi;

public class CommandBinder {

	private CommandType request;
	private Command response;
	
	public CommandBinder(CommandType request, Command response) {
		this.request = request;
		this.response = response;
	}

	public CommandType getRequest() {
		return request;
	}

	public Command getResponse() {
		return response;
	}
	
}
