package imdb;

import java.io.IOException;
import java.net.Socket;

import UserInterface.guest;

public class ClientMain {

	public static void main(String[] args) {
		String serveradder="192.168.1.4";
		ConnectToServer connection=new ConnectToServer(serveradder);
		Socket socket=connection.getsocket();
		
		guest g1=new guest(socket);
		g1.setVisible(true);
		

	}

}
