package Database;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import imdb.Film;
import imdb.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;

import imdb.Rate;

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
			String CountryContent = "";
			ArrayList geners = new ArrayList<String>();
			String MinuteContent = "";
			String DirectorContent = "";
			EndField = Order.indexOf(":");
			EndContent = Order.indexOf("*");
			Endorder = Order.indexOf("#");
			fieldname = Order.substring(0, EndField);
			if (fieldname.equals("name")) {
				NameContent = Order.substring(EndField + 1, EndContent);
				Order = Order.substring(EndContent + 1, Order.length());
				if (Endorder != EndContent + 1) {
					Endorder -= (EndContent + 1);
					EndField = Order.indexOf(":");
					EndContent = Order.indexOf("*");
					fieldname = Order.substring(0, EndField);
				}
			}
			if (fieldname.equals("year")) {
				YearContent = Order.substring(EndField + 1, EndContent);
				Order = Order.substring(EndContent + 1, Order.length());
				if (Endorder != EndContent + 1) {
					Endorder -= (EndContent + 1);
					EndField = Order.indexOf(":");
					EndContent = Order.indexOf("*");
					fieldname = Order.substring(0, EndField);
				}
			}
			if (fieldname.equals("country")) {
				CountryContent = Order.substring(EndField + 1, EndContent);
				Order = Order.substring(EndContent + 1, Order.length());
				if (Endorder != EndContent + 1) {
					Endorder -= (EndContent + 1);
					EndField = Order.indexOf(":");
					EndContent = Order.indexOf("*");
					fieldname = Order.substring(0, EndField);
				}
			}
			if (fieldname.equals("gener")) {
				Order = Order.substring(EndField + 1, Order.length());
				EndContent -= (EndField + 1);
				Endorder -= (EndField + 1);
				EndField = Order.indexOf("|");
				int LengthOfGener = EndContent;
				for (int i = 0; i < LengthOfGener;) {
					geners.add(Order.substring(0, EndField));
					EndContent -= (EndField + 1);
					Endorder -= (EndField + 1);
					i += EndField + 1;
					Order = Order.substring(EndField + 1, Order.length());
					if (i != LengthOfGener)
						EndField = Order.indexOf("|");
					else
						EndField -= EndField;
				}
				Order = Order.substring(1, Order.length());
				if (Endorder != EndContent + 1) {
					Endorder -= (EndContent + 1);
					EndField = Order.indexOf(":");
					EndContent = Order.indexOf("*");
					fieldname = Order.substring(0, EndField);
				}
			}
			if (fieldname.equals("DMinute")) {
				MinuteContent = Order.substring(EndField + 1, EndContent);
				Order = Order.substring(EndContent + 1, Order.length());
				if (Endorder != EndContent + 1) {
					Endorder -= (EndContent + 1);
					EndField = Order.indexOf(":");
					EndContent = Order.indexOf("*");
					fieldname = Order.substring(0, EndField);
				}
			}
			if (fieldname.equals("Director")) {
				DirectorContent = Order.substring(EndField + 1, EndContent);
				Order = Order.substring(EndContent + 1, Order.length());
				if (Endorder != EndContent + 1) {
					Endorder -= (EndContent + 1);
					EndField = Order.indexOf(":");
					EndContent = Order.indexOf("*");
					fieldname = Order.substring(0, EndField);
				}
			}
			////////////////////////////////////////////////////////////////////////////////////////////
			BufferedReader br = new BufferedReader(
					new InputStreamReader(new FileInputStream("./movies.json"), "UTF8"));
			String line = "";
			while ((line = br.readLine()) != null) {
				film = gson.fromJson(line, Film.class);
				if (film.getname().equals(NameContent) || (NameContent.equals("")))
					if (String.valueOf(film.getyear()).equals(YearContent) || (YearContent.equals("")))
						if (film.getcountry().equals(CountryContent) || (CountryContent.equals("")))
							if (GenerIsExist(film.getFilmGeners(), geners) || geners.isEmpty())
								if (String.valueOf(film.getdurationminutes()).equals(MinuteContent)
										|| (MinuteContent.equals("")))
									if (film.getdirector().equals(DirectorContent) || (DirectorContent.equals("")))
										list.add(film);

			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String Signup(String Order) {
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
		Endorder -= (EndContent + 1);
		EndField = Order.indexOf(":");
		EndContent = Order.indexOf("*");
		password = Order.substring(EndField + 1, EndContent);
		Order = Order.substring(EndContent + 1, Order.length());
		Endorder -= (EndContent + 1);
		EndField = Order.indexOf(":");
		EndContent = Order.indexOf("*");
		e_mail = Order.substring(EndField + 1, EndContent);
		Order = Order.substring(EndContent + 1, Order.length());
		User newuser = new User(username, password, e_mail, "Ordinary");
		GsonBuilder builder = new GsonBuilder();
		Gson gson = builder.create();
		String result = "completed";
		try {
			BufferedReader br = new BufferedReader(
					new InputStreamReader(new FileInputStream("./movies.json"), "UTF8"));
			User jsonuser = new User();
			String line = "";
			while ((line = br.readLine()) != null) {
				jsonuser = gson.fromJson(line, User.class);
				if (jsonuser.getusername().equals(newuser.getusername())
						|| (jsonuser.gete_mail().equals(newuser.gete_mail())))
					result = "not completed";

			}
			/////////////////////////////////////////
			if (result.equals("completed")) {
				File file = new File("./Users.json");
				BufferedWriter bw = new BufferedWriter(new FileWriter(file, true));
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

	public User SignIn(String Order) {
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
		Endorder -= (EndContent + 1);
		EndField = Order.indexOf(":");
		EndContent = Order.indexOf("*");
		password = Order.substring(EndField + 1, EndContent);
		Order = Order.substring(EndContent + 1, Order.length());
		GsonBuilder builder = new GsonBuilder();
		Gson gson = builder.create();
		User FindedUser = new User();
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("./Users.json"), "UTF8"));
			User jsonuser = new User();
			String line = "";
			while ((line = br.readLine()) != null) {
				jsonuser = gson.fromJson(line, User.class);
				if (jsonuser.getusername().equals(username) && (jsonuser.getpassword().equals(password)))
					FindedUser = jsonuser;

			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return FindedUser;
	}

	public String Ratejson(String Order, Rate resultrate) {
		int EndField;
		int EndContent;
		int Endorder;
		String FilmName;
		String UserName;
		String rateNumber;
		String title;
		String Discription;

		EndField = Order.indexOf(":");
		EndContent = Order.indexOf("*");
		Endorder = Order.indexOf("#");
		FilmName = Order.substring(EndField + 1, EndContent);
		Order = Order.substring(EndContent + 1, Order.length());
		Endorder -= (EndContent + 1);
		EndField = Order.indexOf(":");
		EndContent = Order.indexOf("*");
		UserName = Order.substring(EndField + 1, EndContent);
		Order = Order.substring(EndContent + 1, Order.length());
		Endorder -= (EndContent + 1);
		EndField = Order.indexOf(":");
		EndContent = Order.indexOf("*");
		rateNumber = Order.substring(EndField + 1, EndContent);
		Order = Order.substring(EndContent + 1, Order.length());
		Endorder -= (EndContent + 1);
		EndField = Order.indexOf(":");
		EndContent = Order.indexOf("*");
		title = Order.substring(EndField + 1, EndContent);
		Order = Order.substring(EndContent + 1, Order.length());
		Endorder -= (EndContent + 1);
		EndField = Order.indexOf(":");
		EndContent = Order.indexOf("*");
		Discription = Order.substring(EndField + 1, EndContent);
		Order = Order.substring(EndContent + 1, Order.length());
		ArrayList<User> listrefree = new ArrayList<User>();
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("./Users.json"), "UTF8"));
			User jsonuser = new User();
			String line = "";
			while ((line = br.readLine()) != null) {
				jsonuser = gson.fromJson(line, User.class);
				if (jsonuser.getTypeOfUser().equals("Referee"))
					listrefree.add(jsonuser);

			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		final int[] RandomIndex = new Random().ints(1, listrefree.size()).distinct().limit(3).toArray();
		String refree1 = listrefree.get(RandomIndex[0]).getusername();
		String refree2 = listrefree.get(RandomIndex[1]).getusername();
		String refree3 = listrefree.get(RandomIndex[2]).getusername();
		Rate userrate = new Rate(FilmName, UserName, rateNumber, title, Discription, refree1, refree2, refree3, "", "",
				"");
		GsonBuilder builder = new GsonBuilder();
		Gson gson = builder.create();
		String result = "Rate Is Registerd";

		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("./Rate.json"), "UTF8"));
			Rate jsonRate = new Rate();
			String line = "";
			while ((line = br.readLine()) != null) {
				jsonRate = gson.fromJson(line, Rate.class);
				if (jsonRate.GetUserName().equals(userrate.GetUserName())
						&& jsonRate.GetFilmName().equals(userrate.GetFilmName()))
					result = "You Rated This Film Before";

			}
			/////////////////////////////////////////
			if (result.equals("Rate Is Registerd")) {
				resultrate.SetFilmName(FilmName);
				resultrate.SetUserName(UserName);
				resultrate.SetTitle(title);
				resultrate.SetRate(rateNumber);
				resultrate.SetDiscription(Discription);
				resultrate.setFirstRefreeUsername(refree1);
				resultrate.setSecondRefreeUsername(refree2);
				resultrate.setThirdRefreeUsername(refree3);
				resultrate.setFirstRefreeResult("");
				resultrate.setSecondRefreeResult("");
				resultrate.setThirdRefreeResult("");
				File file = new File("./Rate.json");
				BufferedWriter bw = new BufferedWriter(new FileWriter(file, true));
				bw.write(gson.toJson(userrate));
				bw.newLine();
				bw.close();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	///////////// 8***************************************************************************************8
	public void GiveRate(String Order, ArrayList<Rate> list) {
		try {
			int EndField;
			int EndContent;
			int Endorder;
			String RefreeName;
			EndField = Order.indexOf(":");
			EndContent = Order.indexOf("*");
			Endorder = Order.indexOf("#");
			RefreeName = Order.substring(EndField + 1, EndContent);
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("./Rate.json"), "UTF8"));
			String line = "";
			while ((line = br.readLine()) != null) {
				Rate jsonrate = gson.fromJson(line, Rate.class);
				if ((jsonrate.getFirstRefreeUsername().equals(RefreeName) && jsonrate.getFirstRefreeResult().equals(""))
						|| (jsonrate.getSecondRefreeUsername().equals(RefreeName)
								&& jsonrate.getSecondRefreeResult().equals(""))
						|| (jsonrate.getThirdRefreeUsername().equals(RefreeName)
								&& jsonrate.getThirdRefreeResult().equals("")))
					list.add(jsonrate);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void RateConfirm(String Order) {
		int EndField;
		int EndContent;
		int Endorder;
		String UserName;
		String FilmName;
		String Refreename;
		String Status;

		EndField = Order.indexOf(":");
		EndContent = Order.indexOf("*");
		Endorder = Order.indexOf("#");
		UserName = Order.substring(EndField + 1, EndContent);
		Order = Order.substring(EndContent + 1, Order.length());
		Endorder -= (EndContent + 1);
		EndField = Order.indexOf(":");
		EndContent = Order.indexOf("*");
		FilmName = Order.substring(EndField + 1, EndContent);
		Order = Order.substring(EndContent + 1, Order.length());
		Endorder -= (EndContent + 1);
		EndField = Order.indexOf(":");
		EndContent = Order.indexOf("*");
		Refreename = Order.substring(EndField + 1, EndContent);
		Order = Order.substring(EndContent + 1, Order.length());
		Endorder -= (EndContent + 1);
		EndField = Order.indexOf(":");
		EndContent = Order.indexOf("*");
		Status = Order.substring(EndField + 1, EndContent);
		Order = Order.substring(EndContent + 1, Order.length());
		ArrayList<Rate> RateList = new ArrayList<Rate>();
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("./Rate.json"), "UTF8"));
			String line = "";
			while ((line = br.readLine()) != null) {
				Rate jsonrate = gson.fromJson(line, Rate.class);
				if (jsonrate.GetFilmName().equals(FilmName) && jsonrate.GetUserName().equals(UserName)) {
					if (jsonrate.getFirstRefreeUsername().equals(Refreename)) {
						jsonrate.setFirstRefreeResult(Status);
					} else if (jsonrate.getSecondRefreeUsername().equals(Refreename)) {
						jsonrate.setSecondRefreeResult(Status);
					} else if (jsonrate.getThirdRefreeUsername().equals(Refreename)) {
						jsonrate.setThirdRefreeResult(Status);
					}
				}
				RateList.add(jsonrate);
			}
			File rate = new File("./Rate.json");
			rate.delete();
			rate.createNewFile();
			BufferedWriter bwrate = new BufferedWriter(new FileWriter(rate, true));
			File reviw = new File("./Review.json");
			BufferedWriter bwreview = new BufferedWriter(new FileWriter(reviw, true));
			PrintWriter cleaner = new PrintWriter(rate);
			cleaner.print("");
			cleaner.close();
			for (int i = 0; i < RateList.size(); i++) {
				if ((RateList.get(i).getFirstRefreeResult().equals("Accept")
						&& RateList.get(i).getSecondRefreeResult().equals("Accept"))
						|| (RateList.get(i).getFirstRefreeResult().equals("Accept")
								&& RateList.get(i).getThirdRefreeResult().equals("Accept"))
						|| (RateList.get(i).getSecondRefreeResult().equals("Accept")
								&& RateList.get(i).getThirdRefreeResult().equals("Accept"))) {
					bwrate.write(gson.toJson(RateList.get(i)));
					bwrate.newLine();
				} else if ((RateList.get(i).getFirstRefreeResult().equals("Reject")
						&& RateList.get(i).getSecondRefreeResult().equals("Reject"))
						|| (RateList.get(i).getFirstRefreeResult().equals("Reject")
								&& RateList.get(i).getThirdRefreeResult().equals("Reject"))
						|| (RateList.get(i).getSecondRefreeResult().equals("Reject")
								&& RateList.get(i).getThirdRefreeResult().equals("Reject"))) {

				} else {
					bwreview.write(gson.toJson(RateList.get(i)));
					bwreview.newLine();
				}
			}
			bwrate.close();
			bwreview.close();
		} catch (JsonSyntaxException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void ShowReviews(String Order, ArrayList<Rate> list) {
		try {
			int EndField;
			int EndContent;
			int Endorder;
			String FilmName;
			EndField = Order.indexOf(":");
			EndContent = Order.indexOf("*");
			Endorder = Order.indexOf("#");
			FilmName = Order.substring(EndField + 1, EndContent);
			BufferedReader br = new BufferedReader(
					new InputStreamReader(new FileInputStream("./Review.json"), "UTF8"));
			String line = "";
			while ((line = br.readLine()) != null) {
				Rate jsonrate = gson.fromJson(line, Rate.class);
				if (jsonrate.GetFilmName().equals(FilmName))
					list.add(jsonrate);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void editmovie(String Order) {
		Film film = new Film();
		int EndField;
		int EndContent;
		int Endorder;
		String identifier = "";
		String fieldname;
		String NameContent = "";
		String YearContent = "";
		String CountryContent = "";
		ArrayList geners = new ArrayList<String>();
		String MinuteContent = "";
		String DirectorContent = "";
		String descriptionContent = "";
		EndField = Order.indexOf(":");
		EndContent = Order.indexOf("*");
		Endorder = Order.indexOf("#");
		fieldname = Order.substring(0, EndField);
		if (fieldname.equals("identifiername")) {
			identifier = Order.substring(EndField + 1, EndContent);
			Order = Order.substring(EndContent + 1, Order.length());
			if (Endorder != EndContent + 1) {
				Endorder -= (EndContent + 1);
				EndField = Order.indexOf(":");
				EndContent = Order.indexOf("*");
				fieldname = Order.substring(0, EndField);
			}
		}
		if (fieldname.equals("name")) {
			NameContent = Order.substring(EndField + 1, EndContent);
			Order = Order.substring(EndContent + 1, Order.length());
			if (Endorder != EndContent + 1) {
				Endorder -= (EndContent + 1);
				EndField = Order.indexOf(":");
				EndContent = Order.indexOf("*");
				fieldname = Order.substring(0, EndField);
			}
		}
		if (fieldname.equals("year")) {
			YearContent = Order.substring(EndField + 1, EndContent);
			Order = Order.substring(EndContent + 1, Order.length());
			if (Endorder != EndContent + 1) {
				Endorder -= (EndContent + 1);
				EndField = Order.indexOf(":");
				EndContent = Order.indexOf("*");
				fieldname = Order.substring(0, EndField);
			}
		}
		if (fieldname.equals("country")) {
			CountryContent = Order.substring(EndField + 1, EndContent);
			Order = Order.substring(EndContent + 1, Order.length());
			if (Endorder != EndContent + 1) {
				Endorder -= (EndContent + 1);
				EndField = Order.indexOf(":");
				EndContent = Order.indexOf("*");
				fieldname = Order.substring(0, EndField);
			}
		}
		if (fieldname.equals("gener")) {
			Order = Order.substring(EndField + 1, Order.length());
			EndContent -= (EndField + 1);
			Endorder -= (EndField + 1);
			EndField = Order.indexOf("|");
			int LengthOfGener = EndContent;
			for (int i = 0; i < LengthOfGener;) {
				geners.add(Order.substring(0, EndField));
				EndContent -= (EndField + 1);
				Endorder -= (EndField + 1);
				i += EndField + 1;
				Order = Order.substring(EndField + 1, Order.length());
				if (i != LengthOfGener)
					EndField = Order.indexOf("|");
				else
					EndField -= EndField;
			}
			Order = Order.substring(1, Order.length());
			if (Endorder != EndContent + 1) {
				Endorder -= (EndContent + 1);
				EndField = Order.indexOf(":");
				EndContent = Order.indexOf("*");
				fieldname = Order.substring(0, EndField);
			}
		}
		if (fieldname.equals("DMinute")) {
			MinuteContent = Order.substring(EndField + 1, EndContent);
			Order = Order.substring(EndContent + 1, Order.length());
			if (Endorder != EndContent + 1) {
				Endorder -= (EndContent + 1);
				EndField = Order.indexOf(":");
				EndContent = Order.indexOf("*");
				fieldname = Order.substring(0, EndField);
			}
		}
		if (fieldname.equals("Director")) {
			DirectorContent = Order.substring(EndField + 1, EndContent);
			Order = Order.substring(EndContent + 1, Order.length());
			if (Endorder != EndContent + 1) {
				Endorder -= (EndContent + 1);
				EndField = Order.indexOf(":");
				EndContent = Order.indexOf("*");
				fieldname = Order.substring(0, EndField);
			}
		}
		if (fieldname.equals("description")) {
			descriptionContent = Order.substring(EndField + 1, EndContent);
			Order = Order.substring(EndContent + 1, Order.length());
			if (Endorder != EndContent + 1) {
				Endorder -= (EndContent + 1);
				EndField = Order.indexOf(":");
				EndContent = Order.indexOf("*");
				fieldname = Order.substring(0, EndField);
			}
		}
		film = new Film(NameContent, Integer.parseInt(YearContent), CountryContent, geners,
				Integer.parseInt(MinuteContent), DirectorContent, descriptionContent);
		ArrayList<Film> FilmList = new ArrayList<Film>();
		try {
			BufferedReader br = new BufferedReader(
					new InputStreamReader(new FileInputStream("./movies.json"), "UTF8"));
			String line = "";
			while ((line = br.readLine()) != null) {
				Film jsonFilm = gson.fromJson(line, Film.class);
				FilmList.add(jsonFilm);
			}
			File movies = new File("./movies.json");
			PrintWriter cleaner = new PrintWriter(movies);
			cleaner.print("");
			cleaner.close();
			BufferedWriter bwmovie = new BufferedWriter(new FileWriter(movies, true));
			for (int i = 0; i < FilmList.size(); i++) {
				if (FilmList.get(i).getname().equals(identifier)) {
					bwmovie.write(gson.toJson(film));
					bwmovie.newLine();
				} else {
					bwmovie.write(gson.toJson(FilmList.get(i)));
					bwmovie.newLine();
				}
			}
			bwmovie.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonSyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public String addmovie(String Order) {
		Film film = new Film();
		int EndField;
		int EndContent;
		int Endorder;
		String fieldname;
		String NameContent = "";
		String YearContent = "";
		String CountryContent = "";
		ArrayList geners = new ArrayList<String>();
		String MinuteContent = "";
		String DirectorContent = "";
		String descriptionContent = "";
		EndField = Order.indexOf(":");
		EndContent = Order.indexOf("*");
		Endorder = Order.indexOf("#");
		fieldname = Order.substring(0, EndField);
		if (fieldname.equals("name")) {
			NameContent = Order.substring(EndField + 1, EndContent);
			Order = Order.substring(EndContent + 1, Order.length());
			if (Endorder != EndContent + 1) {
				Endorder -= (EndContent + 1);
				EndField = Order.indexOf(":");
				EndContent = Order.indexOf("*");
				fieldname = Order.substring(0, EndField);
			}
		}
		if (fieldname.equals("year")) {
			YearContent = Order.substring(EndField + 1, EndContent);
			Order = Order.substring(EndContent + 1, Order.length());
			if (Endorder != EndContent + 1) {
				Endorder -= (EndContent + 1);
				EndField = Order.indexOf(":");
				EndContent = Order.indexOf("*");
				fieldname = Order.substring(0, EndField);
			}
		}
		if (fieldname.equals("country")) {
			CountryContent = Order.substring(EndField + 1, EndContent);
			Order = Order.substring(EndContent + 1, Order.length());
			if (Endorder != EndContent + 1) {
				Endorder -= (EndContent + 1);
				EndField = Order.indexOf(":");
				EndContent = Order.indexOf("*");
				fieldname = Order.substring(0, EndField);
			}
		}
		if (fieldname.equals("gener")) {
			Order = Order.substring(EndField + 1, Order.length());
			EndContent -= (EndField + 1);
			Endorder -= (EndField + 1);
			EndField = Order.indexOf("|");
			int LengthOfGener = EndContent;
			for (int i = 0; i < LengthOfGener;) {
				geners.add(Order.substring(0, EndField));
				EndContent -= (EndField + 1);
				Endorder -= (EndField + 1);
				i += EndField + 1;
				Order = Order.substring(EndField + 1, Order.length());
				if (i != LengthOfGener)
					EndField = Order.indexOf("|");
				else
					EndField -= EndField;
			}
			Order = Order.substring(1, Order.length());
			if (Endorder != EndContent + 1) {
				Endorder -= (EndContent + 1);
				EndField = Order.indexOf(":");
				EndContent = Order.indexOf("*");
				fieldname = Order.substring(0, EndField);
			}
		}
		if (fieldname.equals("DMinute")) {
			MinuteContent = Order.substring(EndField + 1, EndContent);
			Order = Order.substring(EndContent + 1, Order.length());
			if (Endorder != EndContent + 1) {
				Endorder -= (EndContent + 1);
				EndField = Order.indexOf(":");
				EndContent = Order.indexOf("*");
				fieldname = Order.substring(0, EndField);
			}
		}
		if (fieldname.equals("Director")) {
			DirectorContent = Order.substring(EndField + 1, EndContent);
			Order = Order.substring(EndContent + 1, Order.length());
			if (Endorder != EndContent + 1) {
				Endorder -= (EndContent + 1);
				EndField = Order.indexOf(":");
				EndContent = Order.indexOf("*");
				fieldname = Order.substring(0, EndField);
			}
		}
		if (fieldname.equals("description")) {
			descriptionContent = Order.substring(EndField + 1, EndContent);
			Order = Order.substring(EndContent + 1, Order.length());
			if (Endorder != EndContent + 1) {
				Endorder -= (EndContent + 1);
				EndField = Order.indexOf(":");
				EndContent = Order.indexOf("*");
				fieldname = Order.substring(0, EndField);
			}
		}
		film = new Film(NameContent, Integer.parseInt(YearContent), CountryContent, geners,
				Integer.parseInt(MinuteContent), DirectorContent, descriptionContent);
		String result = "Adding Complete";
		try {
			BufferedReader br = new BufferedReader(
					new InputStreamReader(new FileInputStream("./movies.json"), "UTF8"));
			String line = "";
			while ((line = br.readLine()) != null) {
				Film jsonFilm = gson.fromJson(line, Film.class);
				if (jsonFilm.getname().equals(film.getname()))
					result = "This Film was in the Database";

			}
			if (result.equals("Adding Complete")) {
				File file = new File("./movies.json");
				BufferedWriter bw = new BufferedWriter(new FileWriter(file, true));
				bw.write(gson.toJson(film));
				bw.newLine();
				bw.close();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonSyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	public String AddRefree(String Order) {
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
		Endorder -= (EndContent + 1);
		EndField = Order.indexOf(":");
		EndContent = Order.indexOf("*");
		password = Order.substring(EndField + 1, EndContent);
		Order = Order.substring(EndContent + 1, Order.length());
		Endorder -= (EndContent + 1);
		EndField = Order.indexOf(":");
		EndContent = Order.indexOf("*");
		e_mail = Order.substring(EndField + 1, EndContent);
		Order = Order.substring(EndContent + 1, Order.length());
		User newuser = new User(username, password, e_mail, "Refree");
		GsonBuilder builder = new GsonBuilder();
		Gson gson = builder.create();
		String result = "completed";
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("./Users.json"), "UTF8"));
			User jsonuser = new User();
			String line = "";
			while ((line = br.readLine()) != null) {
				jsonuser = gson.fromJson(line, User.class);
				if (jsonuser.getusername().equals(newuser.getusername())
						|| (jsonuser.gete_mail().equals(newuser.gete_mail())))
					result = "not completed";

			}
			/////////////////////////////////////////
			if (result.equals("completed")) {
				File file = new File("./Users.json");
				BufferedWriter bw = new BufferedWriter(new FileWriter(file, true));
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

	public boolean GenerIsExist(List<String> list, ArrayList<String> RequestedGeners) {
		for (int i = 0; i < list.size(); i++)
			for (int j = 0; j < RequestedGeners.size(); j++)
				if (list.get(i).equals(RequestedGeners.get(j)))
					return true;
		return false;
	}
}
