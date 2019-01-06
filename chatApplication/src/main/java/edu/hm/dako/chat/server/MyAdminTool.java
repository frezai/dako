package edu.hm.dako.chat.server;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import edu.hm.dako.chat.common.PduType;

public class MyAdminTool {

	public static void main(String[] args) throws FileNotFoundException, IOException {
		// Logdatei namen in den String fileName
		String fileNameTCP = "TCP-file.txt";
		String fileNameUDP = "UDP-file.txt";
		int loginCounter = 0;
		int logoutCounter = 0;
		int chatMessageCounter = 0;

		try (BufferedReader br = new BufferedReader(new FileReader(fileNameTCP));
				BufferedReader br2 = new BufferedReader(new FileReader(fileNameUDP))) {
			String currentLine;
			while ((currentLine = br.readLine()) != null) {

				if (currentLine.contains("Login-Request")) {
					loginCounter++;
				}
				if (currentLine.contains("Logout-Request")) {
					logoutCounter++;
				}
				if (currentLine.contains("Chat-Message-Request")) {
					chatMessageCounter++;
				}
			}
			System.out.println("Angekommene Logins: " + loginCounter);
			System.out.println("Angekommene Logouts: " + logoutCounter);
			System.out.println("Angekommene Chatsmessages: " + chatMessageCounter);
		}
	}
}