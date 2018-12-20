package edu.hm.dako.chat.server;
import java.net.*;
import java.io.*;
public class UDPEchoServer {
	protected DatagramSocket socket;
	PrintWriter printer = new PrintWriter("UDP-file.txt", "UTF-8");;
	public UDPEchoServer (int port) throws IOException
	{
		socket = new DatagramSocket (port);
	}
	public void execute() throws IOException
	{
		while (true)
		{
			DatagramPacket packet = receive();
		}
	}
	protected DatagramPacket receive () throws IOException
	{
		byte buffer[] = new byte[6553500];
		DatagramPacket packet = new DatagramPacket (buffer, buffer.length);
		socket.receive(packet);
		String sentence = new String(packet.getData()).trim();
		System.out.println ("Daten erhalten: " + sentence);
		printer.println(sentence);
		printer.flush();

		return packet;
	}
	public static void main (String args[]) throws IOException
	{
			//throw new RuntimeException ("Syntax: UDPEchoServer <port>");
			UDPEchoServer echo = new UDPEchoServer (9876);
			echo.execute();

	}
}