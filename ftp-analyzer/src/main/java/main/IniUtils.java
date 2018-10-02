package main;

import java.io.File;
import java.io.IOException;

import org.ini4j.InvalidFileFormatException;
import org.ini4j.Wini;

public class IniUtils {

	public static Wini getIni() throws InvalidFileFormatException, IOException {
		String currentDir = System.getProperty("user.dir");
		currentDir += currentDir.endsWith("/") ? "" : "/";
		String iniPath = currentDir + "ftp-config.ini";
		return new Wini(new File(iniPath));
	}
}
