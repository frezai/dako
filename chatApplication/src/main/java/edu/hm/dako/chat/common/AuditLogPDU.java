package edu.hm.dako.chat.common;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Nachrichtenaufbau fuer Chat-Protokoll (fuer alle Nachrichtentypen: Request,
 * Response, Event, Confirm)
 */
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
	
	// Shutdown Nachricht 
	private String shutdownMessage;

	//Zeitstempel
	private Timestamp timestamp;

	//PDU Typ 
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

	public AuditLogPDU(PduType pduType, Timestamp timestamp, String serverThreadName, String shutDownMessage) {
		this.userName = "";
		this.serverThreadName = serverThreadName;
		this.clientThreadName = "";
		this.shutdownMessage = shutDownMessage;
		this.timestamp = timestamp;
		this.pduType = pduType;
	}

	public PduType getPduType() {
		return this.pduType;
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
		if(pduType.toString().equals("Undefined")) {
			sb.append("SHUTDOWN-MESSAGE: " + shutdownMessage);
		}
		return sb.toString();
	}
}
