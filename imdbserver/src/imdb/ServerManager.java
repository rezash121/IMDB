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
	private DatabaseType databasetype;

	public ServerManager(Socket socket, int clientNumber, DatabaseType databasetype) {
		this.databasetype = databasetype;
		this.socket = socket;
		this.clientNumber = clientNumber;
		log("New connection with client# " + clientNumber + " at " + socket);
	}

	public void run() {
		try {
			Functionality function = new Functionality(socket);
			BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			// PrintWriter out = new PrintWriter(socket.getOutputStream(),
			// true);

			while (true) {
				String input = in.readLine();
				if (input == null || input.equals(".")) {
					break;
				}
				System.out.println(databasetype.getDataBaseType());
				int EndOfTitle = input.indexOf("#");
				String Title = input.substring(0, EndOfTitle);
				if (Title.equals("Switch Database"))
					databasetype.SetDataBaseType(input.substring(EndOfTitle + 1, input.length()));
				else {
					function.setdatabasetype(databasetype.getDataBaseType());
					function.WhatIsTheOrder(input);
				}

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
