package edu.hm.dako.chat.server;

import java.io.PrintWriter;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.sql.Timestamp;

/**
 * UDP-AuditLog Server
 *
 */
public class UDPAuditServer {
	
	public static void main(String[] args) throws Exception {
		System.out.println("UDP-Server wird gestartet...");
		
		// Erstellte Textdatei
		PrintWriter printer = new PrintWriter("UDP-file.txt", "UTF-8");
		
		DatagramSocket socket = new DatagramSocket (9876);
		
		while (true) {
		byte buffer[] = new byte[6553500];
		DatagramPacket packet = new DatagramPacket (buffer, buffer.length);
		socket.receive(packet);
		String sentence = new String(packet.getData()).trim();
		System.out.println ("Daten erhalten: " + sentence);
		printer.println(sentence);
		printer.flush();
		
		//Shutdown-Nachricht, um den AuditLog-Server zu beenden.
		if (sentence.contains("Undefined")) {
			System.out.println("UDP AuditLog Server wird heruntergefahren " + new Timestamp(System.currentTimeMillis()));
			printer.println("UDP AuditLog Server wird heruntergefahren " + new Timestamp(System.currentTimeMillis()));
			printer.flush();
			printer.close();
			System.exit(0);
		}
		}
	}
}