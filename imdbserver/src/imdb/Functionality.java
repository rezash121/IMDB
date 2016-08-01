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
	private PrintWriter output;
	private Response response;
	private User user;
	private Rate rate;
	private ArrayList<Rate> Ratelist;
	private String databasetype;
	public Functionality(Socket socket) {
		this.socket = socket;
		user = new User();
		databasemanager = new DatabaseManager();
		list = new ArrayList<Film>();
		response = new Response();
		Ratelist =new ArrayList<Rate>();
		try {
			this.objectOut = new ObjectOutputStream(socket.getOutputStream());
			output = new PrintWriter(socket.getOutputStream(), true);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void WhatIsTheOrder(String Order) {
		try {
			int EndOfTitle;
			String Title;
			System.out.println("title");
			EndOfTitle = Order.indexOf("#");
			Title = Order.substring(0, EndOfTitle);
			Order = Order.substring(EndOfTitle + 1, Order.length());
			if (Title.equals("search")) {
				databasemanager.MoviesFind(Order, this.list);
				System.out.println("here is search");
				if (list.size() == 0) {
					response.SetResult("NotFound");
					objectOut.writeObject(response);
					objectOut.reset();
					list.clear();
				} else {
					response.SetResult("Ready");
					objectOut.writeObject(response);
					objectOut.writeObject(list);
					objectOut.reset();
					list.clear();
				}
			} else if (Title.equals("signup")) {
				String result = databasemanager.SignUp(Order);
				response.SetResult(result);
				objectOut.writeObject(response);
				objectOut.reset();
			} else if (Title.equals("signin")) {
				user = databasemanager.SignIn(Order);
				response.SetResult(user.getTypeOfUser());
				objectOut.writeObject(response);
				objectOut.reset();
			} else if (Title.equals("rate")) {
				String result = databasemanager.Rate(Order);
				response.SetResult(result);
				objectOut.writeObject(response);
				objectOut.reset();
			}
			else if(Title.equals("showrate")){

				databasemanager.ShowRates(Order, Ratelist);
				if (Ratelist.size() == 0) {
					response.SetResult("NotFound");
					objectOut.writeObject(response);
					objectOut.reset();
					Ratelist.clear();
				} else {
					response.SetResult("Ready");
					objectOut.writeObject(response);
					objectOut.writeObject(Ratelist);
					objectOut.reset();
					Ratelist.clear();
				}
			}else if(Title.equals("RateConfirm")){
				databasemanager.RateConfirm(Order);
			}else if(Title.equals("ShowReviews")){
				
				databasemanager.ShowReviews(Order,Ratelist);
				if (Ratelist.size() == 0) {
					response.SetResult("NotFound");
					objectOut.writeObject(response);
					objectOut.reset();
					Ratelist.clear();
				} else {
					response.SetResult("Ready");
					objectOut.writeObject(response);
					objectOut.writeObject(Ratelist);
					objectOut.reset();
					Ratelist.clear();
				}
			}else if(Title.equals("editmovie")){
				databasemanager.editmovie(Order);
			}else if(Title.equals("addmovie")){
				String result =	databasemanager.addmovie(Order);
				response.SetResult(result);
				objectOut.writeObject(response);
				objectOut.reset();
			} else if (Title.equals("addrefree")) {
				String result = databasemanager.AddRefree(Order);
				response.SetResult(result);
				objectOut.writeObject(response);
				objectOut.reset();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	public void setdatabasetype(String databasetype){
		databasemanager.setTypeOfDatabase(databasetype);
	}

}
