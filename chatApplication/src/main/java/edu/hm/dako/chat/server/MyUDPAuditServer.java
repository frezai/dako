package edu.hm.dako.chat.server;

import java.io.PrintWriter;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class MyUDPAuditServer {

	public static void main(String args[]) throws Exception {
		//erstellt serverSoket mit Portnummer
		DatagramSocket serverSocket = new DatagramSocket(9876);
		// bekommene Datei byte von 1024
		byte[] receiveData = new byte[1024];
		while (true) {
			// bekommt das DatagramPaket mit der Nachricht
			DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
			serverSocket.receive(receivePacket);
			String sentence = new String(receivePacket.getData());
			System.out.println("RECEIVED: " + sentence);
			
			PrintWriter writer = new PrintWriter("UDP-file.txt", "UTF-8");
			writer.println(sentence);
			writer.close();
		}
	}
}