package edu.hm.dako.chat.server;

import java.io.Serializable;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Vector;

import edu.hm.dako.chat.common.ChatPDU;
import edu.hm.dako.chat.common.ClientConversationStatus;
import edu.hm.dako.chat.common.PduType;

public class AuditLogPDU implements Serializable  {
	
	// Login-Name des Clients
	private String userName;
	
	// Name des Threads, der den Request im Server
	private String serverThreadName;
	
	// Name des Client-Threads, der den Request absendet
	private String clientThreadName;
	
	private ChatPDU receivedPDU;
	
	// Nutzdaten (eigentliche Chat-Nachricht in Textform)
	private String message;

	private Timestamp timestamp;

	private PduType pduType;

	public AuditLogPDU() {
		userName = null;
		clientThreadName = null;
		serverThreadName = null;
		message = null;
	}
	
	public AuditLogPDU(ChatPDU receivedPDU, Timestamp timestamp, String serverThreadName) {
		this.userName = receivedPDU.getUserName();
		this.serverThreadName = serverThreadName;
		this.clientThreadName = receivedPDU.getClientThreadName();
		this.message = receivedPDU.getMessage();
		this.timestamp = timestamp;
		this.pduType = receivedPDU.getPduType();
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("PDU-TYPE: " + pduType.toString() + "| ");
		sb.append("TIMESTAMP: " + timestamp + "| ");
		sb.append("USERNAME: " + userName + "| ");
		sb.append("WORKER-THREAD: " + serverThreadName + "| ");
		sb.append("CLIENT-THREAD: " + clientThreadName + "| ");
		if(pduType.toString().equals("Chat-Message-Request")) {
			sb.append("CHAT-MESSAGE: " + message);
		}
		return sb.toString();
	}
}
