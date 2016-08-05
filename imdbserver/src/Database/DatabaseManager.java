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
	private MYSQL mysqlserver;
	public DatabaseManager() {
		json=new JsonFile();
		mysqlserver=new MYSQL();
	}
	public void setTypeOfDatabase(String databasetype){
		this.TypeOfDatabase=databasetype;
	}
	public void MoviesFind(String FieldsOfSearch,ArrayList<Film> list) {
		System.out.println("type of database is:"+TypeOfDatabase);
		if(TypeOfDatabase.equals("json"))
		json.searchMovie(FieldsOfSearch,list);
		else if(TypeOfDatabase.equals("mysql"))
			mysqlserver.searchMovie(FieldsOfSearch, list);

	}
	public String SignUp(String FieldsOfUser){
		 String result=json.Signup(FieldsOfUser);
		if(result.equals("completed"))
		mysqlserver.Signup(FieldsOfUser);
		return result;
	}
	public User SignIn(String userEntry){
		User Findeduser = null;
		if(TypeOfDatabase.equals("json"))
		Findeduser=json.SignIn(userEntry);
		else if(TypeOfDatabase.equals("mysql"))
		Findeduser=mysqlserver.SignIn(userEntry);
		return Findeduser;
	}
	public String Rate(String FieldsOfRate){
		Rate resultrate = new Rate();
		String result=json.Ratejson(FieldsOfRate,resultrate);
		System.out.println("here is manager: "+resultrate.toString());
		if(result.equals("Rate Is Registerd"))
			mysqlserver.Ratesql(resultrate);
		return result;
	}
	public void ShowRates(String FieldsOfSearch,ArrayList<Rate> list) {
		if(TypeOfDatabase.equals("json"))
		json.GiveRate(FieldsOfSearch,list);
		else if(TypeOfDatabase.equals("mysql"))
			mysqlserver.GiveRate(FieldsOfSearch,list);

	}
	public void RateConfirm(String ResultOfConfirm) {
		json.RateConfirm(ResultOfConfirm);
			mysqlserver.RateConfirm(ResultOfConfirm);
	}
	public void ShowReviews(String FieldsOfSearch,ArrayList<Rate> list) {
		if(TypeOfDatabase.equals("json"))
		json.ShowReviews(FieldsOfSearch,list);
		else if(TypeOfDatabase.equals("mysql"))
			mysqlserver.ShowReviews(FieldsOfSearch, list);
	}
	public void editmovie(String FieldsOfSearch) {
		json.editmovie(FieldsOfSearch);
		mysqlserver.editmovie(FieldsOfSearch);
	}
	public String addmovie(String FieldsOfMovie) {
		String result=json.addmovie(FieldsOfMovie);
		if(result.equals("Adding Complete"))
			mysqlserver.addmovie(FieldsOfMovie);
		return result;

	}
	public String AddRefree(String FieldsOfUser){
		String result=json.AddRefree(FieldsOfUser);
		if(result.equals("completed"))
			mysqlserver.AddRefree(FieldsOfUser);
		return result;
	}
	
}
