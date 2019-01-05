package edu.hm.dako.chat.server;

import edu.hm.dako.chat.common.PduType;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Timestamp;

/**
 * TCP-AuditLog Server
 *
 */
class MyTCPAuditServer {
	
	public static void main(String[] args) throws Exception {
		System.out.println("TCP-Server wird gestartet und wartet auf Verbindungsanfragen von Clients...");
		
		// Erstellte Textdatei 
		PrintWriter printer = new PrintWriter("TCP-file.txt", "UTF-8");
		
		// Verwendetes TCP-Socket
		ServerSocket serverSocket = new ServerSocket(6789);

		while (true) {
			//Verbindungsaufbauwunsch annehmen 
			Socket connectionSocket = serverSocket.accept();
			//Empfangen �ber Inputstream
			ObjectInputStream in = new ObjectInputStream(connectionSocket.getInputStream());
			AuditLogPDU pdu = (AuditLogPDU) in.readObject();
			System.out.println(pdu);
			printer.println(pdu);
			printer.flush();
//			ObjectOutputStream out = new ObjectOutputStream(connectionSocket.getOutputStream());
			
			//Shutdown-Nachricht, um den AuditLog-Server zu beenden.
			if(pdu.getPduType().equals(PduType.UNDEFINED)) {
				System.out.println("TCP AuditLog Server wird heruntergefahren " + new Timestamp(System.currentTimeMillis()));
				printer.println("TCP AuditLog Server wird heruntergefahren " + new Timestamp(System.currentTimeMillis()));
				printer.flush();
			
			//Socket und Verbindung schlie�en
			connectionSocket.close();
			in.close();
			System.exit(0);
			printer.close();
			}
		}
	}
}