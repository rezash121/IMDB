package imdb;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

public class ServerManager extends Thread {

	private Socket socket;
	private int clientNumber;
	private int index;

	public ServerManager(Socket socket, int clientNumber) {
		this.socket = socket;
		this.clientNumber = clientNumber;
		log("New connection with client# " + clientNumber + " at " + socket);
	}

	public void run() {
		try {
			Functionality function=new Functionality(socket);
			BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		//	PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
			
			
			while (true) {
				String input = in.readLine();
				if (input == null || input.equals(".")) {
					break;
				}
				System.out.println(input);
				function.WhatIsTheOrder(input);
				
				
			}

		} catch (IOException e) {
			log("Error handling client# " + clientNumber + ": " + e);
		} finally {
			try {
				socket.close();
			} catch (IOException e) {
				log("Couldn't close a socket, what's going on?");
			}
			log("Connection with client# " + clientNumber + " closed");
		}
	}

	private void log(String message) {
		System.out.println(message);
	}

}
