package Database;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;

import imdb.Film;
import imdb.Rate;
import imdb.User;

public class MYSQL {
	private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	private final String DB_URL = "jdbc:mysql://127.0.0.1:3306/imdbdatabase?user=root;databaseName=imdb";
	// Database credentials
	private static final String USER = "root";
	private static final String PASS = "123456789";

	public MYSQL() {

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
			Connection conn = null;
			Statement stmt = null;
			// STEP 2: Register JDBC driver
			Class.forName("com.mysql.jdbc.Driver");

			// STEP 3: Open a connection
			System.out.println("Connecting to database...");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);

			stmt = conn.createStatement();

			boolean FilledBefore = false;
			String sql = "SELECT * FROM Movie WHERE";
			if (!(NameContent.equals(""))) {
				sql += " Name='" +DuplicateQoute( NameContent) + "'";
				FilledBefore = true;
			}
			if (!(YearContent.equals(""))) {
				if (FilledBefore == true)
					sql += " AND";
				sql += " Year='" + YearContent + "'";
				FilledBefore = true;
			}
			if (!(CountryContent.equals(""))) {
				if (FilledBefore == true)
					sql += " AND";
				sql += " Country='" +DuplicateQoute(CountryContent) + "'";
				FilledBefore = true;
			}
			if (!(geners.isEmpty())) {
				if (FilledBefore == true)
					for (int i = 0; i < geners.size(); i++)
						sql += " AND Gener1='" + geners.get(i) + "' OR Gener2='" + geners.get(i) + "' OR Gener3='"
								+ geners.get(i) + "'";
				else {
					sql += " Gener1='" + geners.get(0) + "' OR Gener2='" + geners.get(0) + "' OR Gener3='"
							+ geners.get(0) + "'";
					for (int i = 1; i < geners.size(); i++)
						sql += " AND Gener1='" + geners.get(i) + "' OR Gener2='" + geners.get(i) + "' OR Gener3='"
								+ geners.get(i) + "'";
					FilledBefore = true;
				}
			}
			if (!(MinuteContent.equals(""))) {
				if (FilledBefore == true)
					sql += " AND";
				sql += " DurationMinute=" + MinuteContent;
				FilledBefore = true;
			}
			if (!(DirectorContent.equals(""))) {
				if (FilledBefore == true)
					sql += " AND";
				sql += " Director='" + DuplicateQoute(DirectorContent) + "'";
			}
			System.out.println(sql);
			ResultSet rs = stmt.executeQuery(sql);

			String name, country, gener1, gener2, gener3, director, description;
			int year, minute;
			while (rs.next()) {
				ArrayList<String> filmgeners = new ArrayList<String>(3);
				name = rs.getString("Name");
				year = rs.getInt("Year");
				country = rs.getString("Country");
				gener1 = rs.getString("Gener1");
				gener2 = rs.getString("Gener2");
				gener3 = rs.getString("Gener3");
				minute = rs.getInt("DurationMinute");
				director = rs.getString("Director");
				description = rs.getString("Description");
				filmgeners.add(gener1);
				if (!gener2.equals(""))
					filmgeners.add(gener2);
				if (!gener3.equals(""))
					filmgeners.add(gener3);
				film = new Film(name, year, country, filmgeners, minute, director, description);
				list.add(film);
			}
			rs.close();
			conn.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void Signup(String Order) {
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
		try {
			Connection conn = null;
			Statement stmt = null;
			// STEP 2: Register JDBC driver
			Class.forName("com.mysql.jdbc.Driver");

			// STEP 3: Open a connection
			System.out.println("Connecting to database...");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			
			stmt = conn.createStatement();

			/////////////////////////////////////////
			String sql = "INSERT INTO users " + "VALUES ('" +DuplicateQoute(username) + "'," +DuplicateQoute(password) + ",'" +DuplicateQoute(e_mail)
					+ "','Ordinary')";
			stmt.executeUpdate(sql);
			conn.close();
		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

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
		User FindedUser = new User();
		try {
			Connection conn = null;
			Statement stmt = null;
			// STEP 2: Register JDBC driver
			Class.forName("com.mysql.jdbc.Driver");

			// STEP 3: Open a connection
			conn = DriverManager.getConnection(DB_URL, USER, PASS);


			stmt = conn.createStatement();

			/////////////////////////////////////////
			String sql = "SELECT * FROM users" + " WHERE UserName ='" + DuplicateQoute(username) + "' AND Password='" +DuplicateQoute(password) + "'";
			ResultSet rs = stmt.executeQuery(sql);
			if (rs.next()) {
				String name = rs.getString("UserName");
				String pass = rs.getString("Password");
				String e_mail = rs.getString("E_Mail");
				String usertype = rs.getString("TypeOfUser");
				FindedUser.setusername(name);
				FindedUser.setpassword(pass);
				FindedUser.sete_mail(e_mail);
				FindedUser.setTypeOfUser(usertype);
			}
			rs.close();
			conn.close();
		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return FindedUser;
	}

	public void Ratesql(Rate rate) {
		try {
			Connection conn = null;
			Statement stmt = null;
			// STEP 2: Register JDBC driver
			Class.forName("com.mysql.jdbc.Driver");

			// STEP 3: Open a connection
			System.out.println("Connecting to database...");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);

			stmt = conn.createStatement();

			/////////////////////////////////////////
			String sql = "INSERT INTO rate" + " VALUES ( '" + DuplicateQoute(rate.GetFilmName()) + "','" +DuplicateQoute(rate.GetUserName()) + "','"
					+ DuplicateQoute(rate.GetTitle()) + "','" + DuplicateQoute(rate.GetRate()) + "','" + DuplicateQoute(rate.GetDiscription()) + "','"
					+ DuplicateQoute(rate.getFirstRefreeUsername()) + "','" + DuplicateQoute(rate.getSecondRefreeUsername()) + "','"
					+ DuplicateQoute(rate.getThirdRefreeUsername()) + "','" + rate.getFirstRefreeResult() + "','"
					+ rate.getSecondRefreeResult() + "','" + rate.getThirdRefreeResult() + "')";
			stmt.executeUpdate(sql);

		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
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
			Connection conn = null;
			Statement stmt = null;
			// STEP 2: Register JDBC driver
			Class.forName("com.mysql.jdbc.Driver");

			// STEP 3: Open a connection
			System.out.println("Connecting to database...");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);

			stmt = conn.createStatement();
			String sql="SELECT * FROM rate WHERE FirstRefreeUsername='"+DuplicateQoute(RefreeName)+"' OR SecondRefreeUsername='"+DuplicateQoute(RefreeName)
			+"' OR ThirdRefreeUsername='"+DuplicateQoute(RefreeName)+"'";
			
			Rate resultrate=new Rate();
			ResultSet rs = stmt.executeQuery(sql);
			String filmname,username,title,ratenumber,description,firstrefreename,secondrefreename,thirdrefreename,
			firstrefreeresult,secondrefreeresult,thirdrefreeresult;
			while(rs.next()){
					filmname=rs.getString("FilmName");
					username=rs.getString("UserName");
					title=rs.getString("Title");
					ratenumber=rs.getString("RateNumber");
					description=rs.getString("Description");
					firstrefreename=rs.getString("FirstRefreeUserName");
					secondrefreename=rs.getString("SecondRefreeUserName");
					thirdrefreename=rs.getString("ThirdRefreeUserName");
					firstrefreeresult=rs.getString("FirstRefreeResult");
					secondrefreeresult=rs.getString("SecondRefreeResult");
					thirdrefreeresult=rs.getString("ThirdRefreeResult");
					Rate rate = new Rate(filmname, username, ratenumber, title, description, firstrefreename, secondrefreename, thirdrefreename,
							firstrefreeresult,secondrefreeresult,thirdrefreeresult);
					if ((rate.getFirstRefreeUsername().equals(RefreeName) && rate.getFirstRefreeResult().equals(""))
							|| (rate.getSecondRefreeUsername().equals(RefreeName)
									&& rate.getSecondRefreeResult().equals(""))
							|| (rate.getThirdRefreeUsername().equals(RefreeName)
									&& rate.getThirdRefreeResult().equals("")))
					list.add(rate);
					
			}
			rs.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void RateConfirm(String Order){
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
		ArrayList<Rate> RateList=new ArrayList<Rate>();
		try {
			Connection conn = null;
			Statement stmt = null;
			// STEP 2: Register JDBC driver
			Class.forName("com.mysql.jdbc.Driver");

			// STEP 3: Open a connection
			System.out.println("Connecting to database...");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);

			stmt = conn.createStatement();
			String sql="SELECT * FROM rate WHERE FilmName='"+DuplicateQoute(FilmName)+"' AND UserName='"+DuplicateQoute(UserName)+"'";
			
			ResultSet rs = stmt.executeQuery(sql);
			String filmname,username,title,ratenumber,description,firstrefreename,secondrefreename,thirdrefreename,
			firstrefreeresult,secondrefreeresult,thirdrefreeresult;
			Rate rate ;
			if(rs.next()){
				filmname=rs.getString("FilmName");
				username=rs.getString("UserName");
				title=rs.getString("Title");
				ratenumber=rs.getString("RateNumber");
				description=rs.getString("Description");
				firstrefreename=rs.getString("FirstRefreeUserName");
				secondrefreename=rs.getString("SecondRefreeUserName");
				thirdrefreename=rs.getString("ThirdRefreeUserName");
				firstrefreeresult=rs.getString("FirstRefreeResult");
				secondrefreeresult=rs.getString("SecondRefreeResult");
				thirdrefreeresult=rs.getString("ThirdRefreeResult");
				rate = new Rate(filmname, username, ratenumber, title, description, firstrefreename, secondrefreename, thirdrefreename,
						firstrefreeresult,secondrefreeresult,thirdrefreeresult);

			
			if(rate.getFirstRefreeUsername().equals(Refreename)){
				rate.setFirstRefreeResult(Status);
			}else if(rate.getSecondRefreeUsername().equals(Refreename)){
				rate.setSecondRefreeResult(Status);
			}else if(rate.getThirdRefreeUsername().equals(Refreename)){
				rate.setThirdRefreeResult(Status);
			}
				if((rate.getFirstRefreeResult().equals("Accept")&&rate.getSecondRefreeResult().equals("Accept"))
						||(rate.getFirstRefreeResult().equals("Accept")&&rate.getThirdRefreeResult().equals("Accept"))
						||(rate.getSecondRefreeResult().equals("Accept")&&rate.getThirdRefreeResult().equals("Accept"))){
					 sql="DELETE FROM rate WHERE FilmName='"+DuplicateQoute(FilmName)+"' AND UserName='"+DuplicateQoute(UserName)+"'";
					 stmt.executeUpdate(sql);
					 sql = "INSERT INTO review" + " VALUES ( '" + DuplicateQoute(rate.GetFilmName()) + "','" + DuplicateQoute(rate.GetUserName()) + "','"
								+ DuplicateQoute(rate.GetTitle()) + "','" + rate.GetRate() + "','" + DuplicateQoute(rate.GetDiscription()) + "','"
								+ DuplicateQoute(rate.getFirstRefreeUsername()) + "','" + DuplicateQoute(rate.getSecondRefreeUsername()) + "','"
								+ DuplicateQoute(rate.getThirdRefreeUsername()) + "','" + rate.getFirstRefreeResult() + "','"
								+ rate.getSecondRefreeResult() + "','" + rate.getThirdRefreeResult() + "')";
						stmt.executeUpdate(sql);
					 
				}else if((rate.getFirstRefreeResult().equals("Reject")&&rate.getSecondRefreeResult().equals("Reject"))
						||(rate.getFirstRefreeResult().equals("Reject")&&rate.getThirdRefreeResult().equals("Reject"))
						||(rate.getSecondRefreeResult().equals("Reject")&&rate.getThirdRefreeResult().equals("Reject"))){
					 sql="DELETE FROM rate WHERE FilmName='"+DuplicateQoute(FilmName)+"' AND UserName='"+DuplicateQoute(UserName)+"'";
					 stmt.executeUpdate(sql);
				}else {
					sql="DELETE FROM rate WHERE FilmName='"+FilmName+"' AND UserName='"+UserName+"'";
					 stmt.executeUpdate(sql);
					 sql = "INSERT INTO rate" + " VALUES ( '" + DuplicateQoute(rate.GetFilmName()) + "','" + DuplicateQoute(rate.GetUserName()) + "','"
								+ DuplicateQoute(rate.GetTitle()) + "','" + rate.GetRate() + "','" + DuplicateQoute(rate.GetDiscription()) + "','"
								+ DuplicateQoute(rate.getFirstRefreeUsername()) + "','" + DuplicateQoute(rate.getSecondRefreeUsername()) + "','"
								+ DuplicateQoute(rate.getThirdRefreeUsername()) + "','" + rate.getFirstRefreeResult() + "','"
								+ rate.getSecondRefreeResult() + "','" + rate.getThirdRefreeResult() + "')";
						stmt.executeUpdate(sql);
				}}
			conn.close();
		} catch (JsonSyntaxException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
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
			Connection conn = null;
			Statement stmt = null;
			// STEP 2: Register JDBC driver
			Class.forName("com.mysql.jdbc.Driver");

			// STEP 3: Open a connection
			System.out.println("Connecting to database...");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);

			stmt = conn.createStatement();
			String sql="SELECT * FROM review WHERE FilmName='"+DuplicateQoute(FilmName)+"'";
			
			ResultSet rs = stmt.executeQuery(sql);
			Rate rate;
			String filmname,username,title,ratenumber,description,firstrefreename,secondrefreename,thirdrefreename,
			firstrefreeresult,secondrefreeresult,thirdrefreeresult;
			while(rs.next()){
				filmname=rs.getString("FilmName");
				username=rs.getString("UserName");
				title=rs.getString("Title");
				ratenumber=rs.getString("RateNumber");
				description=rs.getString("Description");
				firstrefreename=rs.getString("FirstRefreeUserName");
				secondrefreename=rs.getString("SecondRefreeUserName");
				thirdrefreename=rs.getString("ThirdRefreeUserName");
				firstrefreeresult=rs.getString("FirstRefreeResult");
				secondrefreeresult=rs.getString("SecondRefreeResult");
				thirdrefreeresult=rs.getString("ThirdRefreeResult");
				rate = new Rate(filmname, username, ratenumber, title, description, firstrefreename, secondrefreename, thirdrefreename,
						firstrefreeresult,secondrefreeresult,thirdrefreeresult);
				list.add(rate);
			}
			conn.close();
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
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
		ArrayList geners = new ArrayList<String>(4);
		String MinuteContent = "";
		String DirectorContent = "";
		String descriptionContent="";
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
		try {
			Connection conn = null;
			Statement stmt = null;
			// STEP 2: Register JDBC driver
			Class.forName("com.mysql.jdbc.Driver");

			// STEP 3: Open a connection
			System.out.println("Connecting to database...");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);

			int position=1;
			stmt = conn.createStatement();
			String sql="UPDATE movie SET Name='"+DuplicateQoute(NameContent)+"',Year='"+YearContent+"',Country='"
			+CountryContent+"',Gener1='"+geners.get(0);
			if(position!=geners.size()){
					sql+="',Gener2='"+geners.get(1);
					position++;
			}else sql+="',Gener2='"+"";
			if(position!=geners.size())
				sql+="',Gener3='"+geners.get(2);
			else
				sql+="',Gener3='"+"";
			sql+="',DurationMinute='"+MinuteContent+"',Director='"+
					DuplicateQoute(DirectorContent)+"',Description='"+DuplicateQoute(descriptionContent)+"' WHERE Name='"+DuplicateQoute(identifier)+"'";
			
			 stmt.executeUpdate(sql);
			 conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
	}
	public void addmovie(String Order) {
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
		String descriptionContent="";
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
		try {
			Connection conn = null;
			Statement stmt = null;
			// STEP 2: Register JDBC driver
			Class.forName("com.mysql.jdbc.Driver");

			// STEP 3: Open a connection
			System.out.println("Connecting to database...");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);

			stmt = conn.createStatement();
			 int position=1;
			String sql = "INSERT INTO movie" + " VALUES ( '" + DuplicateQoute(NameContent) + "','" + YearContent + "','"
						+ DuplicateQoute(CountryContent) + "','" + geners.get(0) + "','" ;
			if(position!=geners.size()){
				sql+= geners.get(1) + "','";
				position++;
			}else sql+= "" + "','";
				if(position!=geners.size())
					sql+= geners.get(2) + "','";
				else
					sql+= "" + "','";
						sql+= MinuteContent + "','" + DuplicateQoute(DirectorContent) + "','"
						+ DuplicateQoute(descriptionContent) + "')";
				stmt.executeUpdate(sql);
			 
			conn.close();
		}  catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void AddRefree(String Order) {
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
		try {
			Connection conn = null;
			Statement stmt = null;
			// STEP 2: Register JDBC driver
			Class.forName("com.mysql.jdbc.Driver");

			// STEP 3: Open a connection
			System.out.println("Connecting to database...");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);

			stmt = conn.createStatement();

			/////////////////////////////////////////
			String sql = "INSERT INTO users " + "VALUES ('" +DuplicateQoute(username) + "'," +DuplicateQoute(password) + ",'" +DuplicateQoute(e_mail)
					+ "','Refree')";
			stmt.executeUpdate(sql);
			conn.close();
		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		

	}
	public String DuplicateQoute(String Stmt) {
		String ch, Result = "";
		int position;
		position = Stmt.indexOf('\'');
		if (position == -1)
			return Stmt;
		else {
			for (int i = 0; i < Stmt.length(); i++) {
				ch = Stmt.substring(i, i + 1);
				if (i == position) {
					Result += "''";
					position = Stmt.indexOf('\'', position + 1);
				} else
					Result += ch;
			}
		}
		return Result;
	}
}
