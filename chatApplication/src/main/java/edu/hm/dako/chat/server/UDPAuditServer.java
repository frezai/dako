package edu.hm.dako.chat.server;
import java.net.*;
import java.sql.Timestamp;
import java.io.*;

/**
 * UDP-AuditLog Server
 *
 */
public class UDPAuditServer {
	
	public static void main(String[] args) throws Exception {
		System.out.println("UDP-Server wird gestartet und wartet auf Verbindungsanfragen von Clients...");
		
		// Erstellte Textdatei
		PrintWriter printer = new PrintWriter("UDP-file.txt", "UTF-8");
		
		DatagramSocket socket = new DatagramSocket (9876);
		
//	public UDPEchoServer (int port) throws IOException {
//		socket = new DatagramSocket (port);
//	}
//	
//	public void execute() throws IOException {
//		while (true) {
//			DatagramPacket packet = receive();
//		}
//	}
//	
//	protected DatagramPacket receive () throws IOException {
		while (true) {
		byte buffer[] = new byte[6553500];
		DatagramPacket packet = new DatagramPacket (buffer, buffer.length);
		socket.receive(packet);
		String sentence = new String(packet.getData()).trim();
		System.out.println ("Daten erhalten: " + sentence);
		printer.println(sentence);
		printer.flush();
		
		//Shutdown-Nachricht, um den AuditLog-Server ordnungsgem�� zu beenden.
		if (sentence.contains("Undefined")) {
			System.out.println("UDP AuditLog Server wird heruntergefahren " + new Timestamp(System.currentTimeMillis()));
			printer.println("UDP AuditLog Server wird heruntergefahren " + new Timestamp(System.currentTimeMillis()));
			printer.flush();
			printer.close();
			System.exit(0);
		}
//		return packet;

		}
	}
//	public static void main (String args[]) throws IOException {
//			//throw new RuntimeException ("Syntax: UDPEchoServer <port>");
//			UDPEchoServer echo = new UDPEchoServer (9876);
//			echo.execute();
//	}
}