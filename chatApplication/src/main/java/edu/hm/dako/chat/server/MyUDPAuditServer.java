package edu.hm.dako.chat.server;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class MyUDPAuditServer {

	public static void main(String args[]) throws Exception {
		//erstellt serverSoket mit Portnummer
		DatagramSocket serverSocket = new DatagramSocket(9876);
		// bekommene Datei byte von 1024
		PrintWriter writer = new PrintWriter("UDP-file.txt", "UTF-8");
		while (true) {
			// bekommt das DatagramPaket mit der Nachricht
			DatagramPacket receivePacket = receive(serverSocket, writer);
			//serverSocket.receive(receivePacket);
			
		}
	}
	
	static DatagramPacket receive(DatagramSocket socket, PrintWriter writer) throws IOException {
		byte[] buffer = new byte[1024];
		DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
		socket.receive(packet);
		
		String sentence = new String(packet.getData());
		System.out.println("RECEIVED: " + sentence);
		
		
		writer.println(sentence);
		
		return packet;
	}
}