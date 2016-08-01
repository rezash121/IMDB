package imdb;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class ServerMain {

	public static void main(String[] args) throws IOException {
		int clientNumber = 0;
		DatabaseType databasetype=new DatabaseType();
		ArrayList listofsocket = new ArrayList<Socket>(1000);
		ServerSocket listener = new ServerSocket(9942);
		try {
			while (true) {
				new ServerManager(listener.accept(), clientNumber++,databasetype).start();
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		} finally {
			listener.close();
		}

	}

}
