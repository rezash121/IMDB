package imdb;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

import Database.DatabaseManager;

public class Functionality {
	private String order;
	private String typeofuser;
	private Socket socket;
	private ArrayList<Film> list;
	private DatabaseManager databasemanager;
	private ObjectOutputStream objectOut;
	private PrintWriter output ;
	private Response response;
	private User user;
	public Functionality(Socket socket){
		this.socket=socket;
		user=new User();
		databasemanager = new DatabaseManager();
		list=new ArrayList<>();
		response=new Response();
		try {
			this.objectOut=new ObjectOutputStream(socket.getOutputStream());
			output = new PrintWriter(socket.getOutputStream(), true);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void WhatIsTheOrder(String Order){
		try{
		int EndOfTitle;
		String Title;
		System.out.println("title");
		EndOfTitle=Order.indexOf("#");
		Title=Order.substring(0,EndOfTitle);
		Order=Order.substring(EndOfTitle+1,Order.length());
		if(Title.equals("search")){
			databasemanager.MoviesFind(Order,this.list);
			System.out.println("here is output");
			if(list.size()==0){
				response.SetResult("NotFound");
				objectOut.writeObject(response);
				objectOut.reset();
				list.clear();
			}
			else{
				response.SetResult("Ready");
				objectOut.writeObject(response);
				objectOut.writeObject(list);
				objectOut.reset();
				list.clear();
			}
		}
		else if(Title.equals("signup")){
			String result=databasemanager.SignUp(Order);
			response.SetResult(result);
			objectOut.writeObject(response);
			objectOut.reset();
		}
		else if(Title.equals("signin")){
			User FindedUser=databasemanager.SignIn(Order);
			if(FindedUser.getusername().equals("")){
				response.SetResult("Sign in Not Completed");
				objectOut.writeObject(response);
				objectOut.reset();	
			}else{
				user=FindedUser;
				response.SetResult("Sign in Completed");
				objectOut.writeObject(response);
				objectOut.reset();
			}
				
		}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
}
