package edu.hm.dako.chat.server;

import java.util.Vector;

import edu.hm.dako.chat.common.ChatPDU;
import edu.hm.dako.chat.common.ClientConversationStatus;
import edu.hm.dako.chat.common.PduType;

public class MyAuditLogPDU {
	
	// Login-Name des Clients
	private String userName;
	
	// Name des Threads, der den Request im Server
	private String serverThreadName;
	
	// Name des Client-Threads, der den Request absendet
	private String clientThreadName;
	
	// Nutzdaten (eigentliche Chat-Nachricht in Textform)
	private String message;
	
	public MyAuditLogPDU() {
		userName = null;
		clientThreadName = null;
		serverThreadName = null;
		message = null;
	}

	public String toString() {

		return "\n"
				+ "ChatPdu ****************************************************************************************************"
				+ "\n" + "userName: " + this.userName
				+ ", " + "\n" + "clientThreadName: " + this.clientThreadName + ", " + "\n"
				+ "serverThreadName: " + this.serverThreadName + ", " + "\n" 
				+ "message: " + this.message + "\n"
				+ "**************************************************************************************************** ChatPdu"
				+ "\n";
	}
	
	/**
	 * Erzeugen einer Logout-Event-PDU
	 * 
	 * @param userName
	 *          Client, der Logout-Request-PDU gesendet hat
	 * @param clientList
	 *          Liste der registrierten User
	 * @param receivedPdu
	 *          Empfangene PDU (Logout-Request-PDU)
	 * @return Erzeugte PDU
	 */
	public static ChatPDU createLogoutEventPdu(String userName, Vector<String> clientList,
			ChatPDU receivedPdu) {

		ChatPDU pdu = new ChatPDU();
		pdu.setPduType(PduType.LOGOUT_EVENT);
		pdu.setUserName(userName);
		pdu.setEventUserName(userName);
		pdu.setServerThreadName(Thread.currentThread().getName());
		pdu.setClientThreadName(receivedPdu.getClientThreadName());
		pdu.setClients(clientList);
		pdu.setClientStatus(ClientConversationStatus.UNREGISTERING);
		return pdu;
	}

	/**
	 * Erzeugen einer Login-Event-PDU
	 * 
	 * @param userName
	 *          Client, der Login-Request-PDU gesendet hat
	 * @param clientList
	 *          Liste der registrierten User
	 * @param receivedPdu
	 *          Empfangene PDU (Login-Request-PDU)
	 * @return Erzeugte PDU
	 */
	public static ChatPDU createLoginEventPdu(String userName, Vector<String> clientList,
			ChatPDU receivedPdu) {

		ChatPDU pdu = new ChatPDU();
		pdu.setPduType(PduType.LOGIN_EVENT);
		pdu.setServerThreadName(Thread.currentThread().getName());
		pdu.setClientThreadName(receivedPdu.getClientThreadName());
		pdu.setUserName(userName);
		pdu.setEventUserName(receivedPdu.getUserName());
		pdu.setUserName(receivedPdu.getUserName());
		pdu.setClients(clientList);
		pdu.setClientStatus(ClientConversationStatus.REGISTERING);
		return pdu;
	}
	
	/**
	 * Erzeugen einer Chat-Message-Event-PDU
	 * 
	 * @param userName
	 *          Client, der Chat-Message-Request-PDU gesendet hat
	 * @param receivedPdu
	 *          (Chat-Message-Request-PDU)
	 * @return Erzeugte PDU
	 */
	public static ChatPDU createChatMessageEventPdu(String userName, ChatPDU receivedPdu) {

		ChatPDU pdu = new ChatPDU();
		pdu.setPduType(PduType.CHAT_MESSAGE_EVENT);
		pdu.setServerThreadName(Thread.currentThread().getName());
		pdu.setClientThreadName(receivedPdu.getClientThreadName());
		pdu.setUserName(userName);
		pdu.setEventUserName(receivedPdu.getUserName());
		pdu.setSequenceNumber(receivedPdu.getSequenceNumber());
		pdu.setClientStatus(ClientConversationStatus.REGISTERED);
		pdu.setMessage(receivedPdu.getMessage());
		return pdu;
	}

}
