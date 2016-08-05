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
	private DatabaseType databasetype;
	private Logging log;

	public ServerManager(Socket socket, int clientNumber, DatabaseType databasetype,Logging Log) {
		this.databasetype = databasetype;
		this.socket = socket;
		this.clientNumber = clientNumber;
		this.log = Log;
	}

	public void run() {
		try {
			Functionality function = new Functionality(socket);
			BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			

			while (true) {
				String input = in.readLine();
				if (input == null || input.equals(".")) {
					break;
				}
				System.out.println(databasetype.getDataBaseType());
				int EndOfTitle = input.indexOf("#");
				String Title = input.substring(0, EndOfTitle);
				if (Title.equals("Switch Database")){
					databasetype.SetDataBaseType(input.substring(EndOfTitle + 1, input.length()));
					log.info("Switch Database");
				}else {
					function.setdatabasetype(databasetype.getDataBaseType());
					synchronized(function) {
				     function.WhatIsTheOrder(input,log);
					} 
				}

			}

		} catch (IOException e) {
			System.out.println("Error handling client# " + clientNumber + ": " + e);
		} finally {
			try {
				socket.close();
			} catch (IOException e) {
				log.warning("Couldn't close a socket "+ clientNumber +" , what's going on?");
			}
			System.out.println("Connection with client# " + clientNumber + " closed");
		}
	}



}
