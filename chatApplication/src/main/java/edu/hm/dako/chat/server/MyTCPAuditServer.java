package edu.hm.dako.chat.server;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class MyTCPAuditServer {

	public static void main(String args[]) throws Exception {
		// String clientSentence;
		// String capitalizedSentence;
		ServerSocket server = new ServerSocket(6789);

		System.out.println("TCP Server wird gestartet");
		while (true) {
			Socket incoming = server.accept();
			ObjectInputStream in;
			ObjectOutputStream out;
			try {
				out = new ObjectOutputStream(incoming.getOutputStream());
				in = new ObjectInputStream(incoming.getInputStream());
				// Empfangen �ber Inputstream
				AuditLogPDU pdu = (AuditLogPDU) in.readObject();
				// BufferedReader inFromClient =
				// new BufferedReader(new
				// InputStreamReader(connectionSocket.getInputStream()));
				// DataOutputStream outToClient = new
				// DataOutputStream(connectionSocket.getOutputStream());
				// clientSentence = inFromClient.readLine();
				System.out.println("Received: " + pdu);

				PrintWriter writer = new PrintWriter("TCP-file.txt", "UTF-8");
				writer.println(in);
				writer.close();

				out.writeObject(new AuditLogPDU());
				// Stream und Verbindung schlie�en
				incoming.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
