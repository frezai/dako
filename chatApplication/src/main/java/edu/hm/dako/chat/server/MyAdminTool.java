package edu.hm.dako.chat.server;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import edu.hm.dako.chat.common.PduType;

public class MyAdminTool {

    public static void main(String[] args) throws FileNotFoundException, IOException {
        //TODO Logdatei namen in den String fileName
        String fileName = "TCP-file.txt";
        int loginCounter = 0;
        int logoutCounter = 0;
        int chatMessageCounter = 0;


        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String currentLine;
            while ((currentLine = br.readLine()) != null) {

                if(currentLine.contains("Login-Request")) {
                    loginCounter++;
                }
                if(currentLine.contains("Logout-Request")) {
                    logoutCounter++;
                }
                if(currentLine.contains("Chat-Message-Request")) {
                    chatMessageCounter++;
                }



            }
            System.out.println("Angekommene Logins: " + loginCounter);
            System.out.println("Ankommende Logouts: " + logoutCounter);
            System.out.println("Ankommende Chatsmessages: " + chatMessageCounter);
        }
    }



}