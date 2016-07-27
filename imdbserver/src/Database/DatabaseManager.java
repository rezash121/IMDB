package Database;

import java.io.IOException;

import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import imdb.Film;
import imdb.User;
public class DatabaseManager {
	private String TypeOfDatabase;
	private JsonFile json;
	public DatabaseManager() {
		json=new JsonFile();
	}
	
	public void MoviesFind(String FieldsOfSearch,ArrayList<Film> list) {
		System.out.println("here is query");
		json.searchMovie(FieldsOfSearch,list);

	}
	public String SignUp(String FieldsOfUser){
		String result=json.Signup(FieldsOfUser);
		return result;
	}
	public User SignIn(String userEntry){
		User Findeduser=json.SignIn(userEntry);
		return Findeduser;
	}
}
