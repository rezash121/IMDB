package Database;

import java.io.IOException;
import imdb.Rate;

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
	public void setTypeOfDatabase(String databasetype){
		this.TypeOfDatabase=databasetype;
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
		System.out.println(userEntry);
		User Findeduser=json.SignIn(userEntry);
		return Findeduser;
	}
	public String Rate(String FieldsOfRate){
		String result=json.Ratejson(FieldsOfRate);
		return result;
	}
	public void ShowRates(String FieldsOfSearch,ArrayList<Rate> list) {
		System.out.println("here is query");
		json.GiveRate(FieldsOfSearch,list);

	}
	public void RateConfirm(String ResultOfConfirm) {
		json.RateConfirm(ResultOfConfirm);

	}
	public void ShowReviews(String FieldsOfSearch,ArrayList<Rate> list) {
		json.ShowReviews(FieldsOfSearch,list);

	}
	public void editmovie(String FieldsOfSearch) {
		json.editmovie(FieldsOfSearch);

	}
	public String addmovie(String FieldsOfMovie) {
		String result=json.addmovie(FieldsOfMovie);
		return result;

	}
	public String AddRefree(String FieldsOfUser){
		String result=json.AddRefree(FieldsOfUser);
		return result;
	}
	
}
