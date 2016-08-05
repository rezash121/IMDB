package imdb;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JOptionPane;

public class ConnectToServer {
	private String ServerAddress;
	private Socket socket;
	
	public ConnectToServer(String serveradder)  {
		this.ServerAddress=serveradder;
        try {
			this.socket = new Socket(ServerAddress, 9942);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        }
	public Socket getsocket(){
		return this.socket;
	}
}
