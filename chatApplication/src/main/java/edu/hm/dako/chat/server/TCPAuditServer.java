package edu.hm.dako.chat.server;

import edu.hm.dako.chat.common.AuditLogPDU;
import edu.hm.dako.chat.common.PduType;

import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Timestamp;

/**
 * TCP-AuditLog Server
 *
 */
class TCPAuditServer {

	public static void main(String[] args) throws Exception {
		System.out.println("TCP-Server wird gestartet...");

		// Erstellte Textdatei
		PrintWriter printer = new PrintWriter("TCP-file.txt", "UTF-8");

		// Verwendetes TCP-Socket
		ServerSocket serverSocket = new ServerSocket(6789);

		while (true) {
			// Verbindungsaufbauwunsch annehmen
			Socket connectionSocket = serverSocket.accept();
			// Empfangen ueber Inputstream; implementiert das Interface
			// ObjectInput und bietet die M�glichkeit, mit der Methode readObject()
			// serialisierte Objekte aus dem darunterliegenden Stream zu lesen.
			ObjectInputStream in = new ObjectInputStream(connectionSocket.getInputStream());
			AuditLogPDU pdu = (AuditLogPDU) in.readObject();
			System.out.println(pdu);
			printer.println(pdu);
			printer.flush();

			// Shutdown-Nachricht, um den AuditLog-Server zu beenden.
			if (pdu.getPduType().equals(PduType.UNDEFINED)) {
				System.out.println(
						"TCP AuditLog Server wird heruntergefahren " + new Timestamp(System.currentTimeMillis()));
				printer.println(
						"TCP AuditLog Server wird heruntergefahren " + new Timestamp(System.currentTimeMillis()));
				printer.flush();

				// Socket, Verbindung, Prozess und Printer schlie�en
				connectionSocket.close();
				in.close();
				System.exit(0);
				printer.close();
			}
		}
	}
}