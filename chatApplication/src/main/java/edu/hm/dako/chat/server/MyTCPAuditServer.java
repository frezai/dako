package edu.hm.dako.chat.server;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class MyTCPAuditServer {
	
	public static void main(String args[]) throws Exception {
	  String clientSentence;
	  String capitalizedSentence;
	  ServerSocket welcomeSocket = new ServerSocket(6789);

	  while (true) {
	   Socket connectionSocket = welcomeSocket.accept();
	   BufferedReader inFromClient =
	    new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
	   DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());
	   clientSentence = inFromClient.readLine();
	   System.out.println("Received: " + clientSentence);
	   
	   PrintWriter writer = new PrintWriter("TCP-file.txt", "UTF-8");
		writer.println(clientSentence);
		writer.close();
	  }
	 }
}
