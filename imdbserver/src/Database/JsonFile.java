package Database;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.security.spec.PSSParameterSpec;
import java.util.ArrayList;
import imdb.Film;
import imdb.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class JsonFile {

private Gson gson;
	public JsonFile() {
		gson = new Gson();
	}

	public void searchMovie(String Order, ArrayList<Film> list) {
		try {
			Film film = new Film();
			int EndField;
			int EndContent;
			int Endorder;
			String fieldname;
			String NameContent = "";
			String YearContent = "";
			EndField = Order.indexOf(":");
			EndContent = Order.indexOf("*");
			Endorder = Order.indexOf("#");
			fieldname = Order.substring(0, EndField);
			if (fieldname.equals("name")) {
				NameContent = Order.substring(EndField + 1, EndContent);
				Order = Order.substring(EndContent + 1, Order.length());
				if (Endorder != EndContent + 1) {
					Endorder-=(EndContent+1);
					EndField = Order.indexOf(":");
					EndContent = Order.indexOf("*");
					fieldname = Order.substring(0, EndField);
				}
			}
			if (fieldname.equals("year")) {
				YearContent = Order.substring(EndField + 1, EndContent);
				Order = Order.substring(EndContent + 1, Order.length());
				if (Endorder != EndContent + 1) {
					Endorder-=(EndContent+1);
					EndField = Order.indexOf(":");
					EndContent = Order.indexOf("*");
					fieldname = Order.substring(0, EndField);
				}
			}
			
////////////////////////////////////////////////////////////////////////////////////////////
			BufferedReader br = new BufferedReader(new FileReader("G:\\movies.json"));
			System.out.println("here is json2");
			String line = "";
			while ((line = br.readLine()) != null) {
				film = gson.fromJson(line, Film.class);
				if (film.getname().equals(NameContent) || (NameContent.equals("")))
					if (String.valueOf(film.getyear()).equals(YearContent)|| (YearContent.equals("")))
						list.add(film);

			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public String Signup(String Order){
		int EndField;
		int EndContent;
		int Endorder;
		String username;
		String password;
		String e_mail;
		EndField = Order.indexOf(":");
		EndContent = Order.indexOf("*");
		Endorder = Order.indexOf("#");
		username = Order.substring(EndField + 1, EndContent);
		Order = Order.substring(EndContent + 1, Order.length());
		Endorder-=(EndContent+1);
		EndField = Order.indexOf(":");
		EndContent = Order.indexOf("*");
		password = Order.substring(EndField + 1, EndContent);
		Order = Order.substring(EndContent + 1, Order.length());
		Endorder-=(EndContent+1);
		EndField = Order.indexOf(":");
		EndContent = Order.indexOf("*");
		e_mail = Order.substring(EndField + 1, EndContent);
		Order = Order.substring(EndContent + 1, Order.length());
		System.out.println(username+" "+" "+password+" "+e_mail);
		User newuser=new User(username,password,e_mail,"Ordinary");
		GsonBuilder builder = new GsonBuilder();
		Gson gson = builder.create();
		String result="completed";
		try {
			BufferedReader br = new BufferedReader(new FileReader("G:/Users.json"));
			User jsonuser=new User();
			String line = "";
			while ((line = br.readLine()) != null) {
				jsonuser = gson.fromJson(line, User.class);
				if (jsonuser.getusername().equals(newuser.getusername()) || 
						(jsonuser.gete_mail().equals(newuser.gete_mail())))
					result="not completed";

			}
			/////////////////////////////////////////
			if(result.equals("completed")){
			File file = new File("G:/Users.json");
			BufferedWriter bw = new BufferedWriter(new FileWriter(file,true));
			System.out.println(gson.toJson(newuser));
			bw.write(gson.toJson(newuser));
			bw.newLine();
			bw.close();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
		 
		
	}
	public User SignIn(String Order){
		int EndField;
		int EndContent;
		int Endorder;
		String username;
		String password;
		EndField = Order.indexOf(":");
		EndContent = Order.indexOf("*");
		Endorder = Order.indexOf("#");
		username = Order.substring(EndField + 1, EndContent);
		Order = Order.substring(EndContent + 1, Order.length());
		Endorder-=(EndContent+1);
		EndField = Order.indexOf(":");
		EndContent = Order.indexOf("*");
		password = Order.substring(EndField + 1, EndContent);
		Order = Order.substring(EndContent + 1, Order.length());
		GsonBuilder builder = new GsonBuilder();
		Gson gson = builder.create();
		User FindedUser =new User();
		try {
			BufferedReader br = new BufferedReader(new FileReader("G:/Users.json"));
			User jsonuser=new User();
			String line = "";
			while ((line = br.readLine()) != null) {
				jsonuser = gson.fromJson(line, User.class);
				if (jsonuser.getusername().equals(username) && 
						(jsonuser.getpassword().equals(password)))
					FindedUser=jsonuser;

			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return FindedUser;
	}
}
