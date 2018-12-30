package edu.hm.dako.chat.server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

class MyTCPAuditServer {
	public static void main(String[] args) throws Exception {
		PrintWriter printer = new PrintWriter("TCP-file.txt", "UTF-8");
		ServerSocket serverSocket = new ServerSocket(6789);

		while (true) {

			Socket connectionSocket = serverSocket.accept();
			ObjectInputStream in = new ObjectInputStream(connectionSocket.getInputStream());
			AuditLogPDU pdu = (AuditLogPDU) in.readObject();
			System.out.println(pdu);
			printer.println(pdu);
			printer.flush();
		}
	}
}