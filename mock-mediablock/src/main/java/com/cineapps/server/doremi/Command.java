package com.cineapps.server.doremi;

public class Command {

	private final int command1;
	private final int command2;
	private final int command3;

	public Command(int command1, int command2, int command3) {
		this.command1 = command1;
		this.command2 = command2;
		this.command3 = command3;
	}

	public CommandBinder getCommandBinder() {
		String hex1 = Integer.toHexString(command1);
		String hex2 = Integer.toHexString(command2);
		String hex3 = Integer.toHexString(command3);

		// For Getting status
		if ("3".equals(hex1) && "1b".equals(hex2) && "0".equals(hex3)) {
			return new CommandBinder(CommandType.GET_STATUS, new Command(Integer.parseInt("3", 16),
			        Integer.parseInt("1c", 16), Integer.parseInt("0", 16)));
		} else if ("3".equals(hex1) && "3".equals(hex2) && "0".equals(hex3)) {
			return new CommandBinder(CommandType.GET_SPL_INFO,
			        new Command(Integer.parseInt("3", 16), Integer.parseInt("4", 16),
			                Integer.parseInt("0", 16)));
		} else if ("4".equals(hex1) && "f".equals(hex2) && "0".equals(hex3)) {
			return new CommandBinder(CommandType.GET_SCHEDULER_ENABLE, new Command(
			        Integer.parseInt("4", 16), Integer.parseInt("10", 16),
			        Integer.parseInt("0", 16)));
		} else if ("8".equals(hex1) && "3".equals(hex2) && "0".equals(hex3)) {
			return new CommandBinder(CommandType.GET_SNMP, new Command(Integer.parseInt("8", 16),
			        Integer.parseInt("4", 16), Integer.parseInt("0", 16)));
		}
		// For getting spl list
		else if ("3".equals(hex1) && "1".equals(hex2) && "0".equals(hex3)) {
			return new CommandBinder(CommandType.GET_SPLS_LIST, new Command(Integer.parseInt("3",
			        16), Integer.parseInt("2", 16), Integer.parseInt("0", 16)));
		} else if ("3".equals(hex1) && "1d".equals(hex2) && "0".equals(hex3)) {
			return new CommandBinder(CommandType.RETR_SPL, new Command(Integer.parseInt("3", 16),
			        Integer.parseInt("1e", 16), Integer.parseInt("0", 16)));
		}
		// For getting cpl list
		else if ("1".equals(hex1) && "1".equals(hex2) && "0".equals(hex3)) {
			return new CommandBinder(CommandType.GET_CPLS_LIST, new Command(Integer.parseInt("1",
			        16), Integer.parseInt("2", 16), Integer.parseInt("0", 16)));
		} else if ("1".equals(hex1) && "7".equals(hex2) && "0".equals(hex3)) {
			return new CommandBinder(CommandType.RETR_CPL, new Command(Integer.parseInt("1", 16),
			        Integer.parseInt("8", 16), Integer.parseInt("0", 16)));
		}
		throw new IllegalArgumentException("Command with " + hex1 + hex2 + hex3 + " unknown");
	}

	public int getCommand1() {
		return command1;
	}

	public int getCommand2() {
		return command2;
	}

	public int getCommand3() {
		return command3;
	}
}
