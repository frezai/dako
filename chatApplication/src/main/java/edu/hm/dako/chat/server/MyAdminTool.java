package edu.hm.dako.chat.server;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import edu.hm.dako.chat.common.PduType;

/**
 * Ein Administrationsprogramm, das das AuditLog ausliest 
 * und aufbereitete Informationen zum Inhalt ausgibt.
 *
 */
public class MyAdminTool {

    public static void main(String[] args) throws FileNotFoundException, IOException {
    	//TCP-AuditLog Administrationstool
        String tcpfileName = "TCP-file.txt";
        int tcpLoginCounter = 0;
        int tcpLogoutCounter = 0;
        int tcpChatMessageCounter = 0;


        try (BufferedReader br = new BufferedReader(new FileReader(tcpfileName))) {
            String currentLine;
            while ((currentLine = br.readLine()) != null) {

                if(currentLine.contains("Login-Request")) {
                    tcpLoginCounter++;
                }
                if(currentLine.contains("Logout-Request")) {
                    tcpLogoutCounter++;
                }
                if(currentLine.contains("Chat-Message-Request")) {
                    tcpChatMessageCounter++;
                }

            }
            System.out.println("TCP AuditLog Daten");
            System.out.println("Angekommene Logins: " + tcpLoginCounter);
            System.out.println("Angekommene Logouts: " + tcpLogoutCounter);
            System.out.println("Angekommene Chatmessages: " + tcpChatMessageCounter + "\n");
        }
        
        //UDP-AuditLog Administrationstool
        String udpfileName = "UDP-file.txt";
        int udpLoginCounter = 0;
        int udpLogoutCounter = 0;
        int udpChatMessageCounter = 0;


        try (BufferedReader br = new BufferedReader(new FileReader(udpfileName))) {
            String currentLine;
            while ((currentLine = br.readLine()) != null) {

                if(currentLine.contains("Login-Request")) {
                    udpLoginCounter++;
                }
                if(currentLine.contains("Logout-Request")) {
                    udpLogoutCounter++;
                }
                if(currentLine.contains("Chat-Message-Request")) {
                    udpChatMessageCounter++;
                }

            }
            System.out.println("UDP AuditLog Daten");
            System.out.println("Angekommene Logins: " + udpLoginCounter);
            System.out.println("Angekommene Logouts: " + udpLogoutCounter);
            System.out.println("Angekommene Chatmessages: " + udpChatMessageCounter);
        }
    }
}