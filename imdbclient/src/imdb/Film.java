package imdb;

import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;
import java.util.Comparator;

public class Film implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;
	private int year;
	private String country;
	private ArrayList<String> genreList;
	private int durationMinutes;
	private String director;
	private String description;
	
	public Film(){
		this.name="";
		this.year=12;
		this.country="";
		this.genreList=null;
		this.durationMinutes=123;
		this.director="dsa";
		this.description="heloo";
	}
	
	public String getname(){
		return name;
	}
	
	public int getyear(){
		return year;
	}
	
	public String getcountry(){
		return country;
	}

	public List<String> getFilmGeners() {
		return this.genreList;
	}

	public int getdurationminutes(){
		return durationMinutes;
	}
	
	public String getdirector(){
		return director;
		
	}
	public String getdescription(){
		return description;
		
	}
/////////////////////////////////////////////////////////////////////////////////////////
	   public static Comparator<Film> FilmNameComparatorASC = new Comparator<Film>() {
		
		@Override
		public int compare(Film f1, Film f2) {
			  String FilmName1 = f1.getname().toUpperCase();
			  String FilmName2 = f2.getname().toUpperCase();
			return FilmName1.compareTo(FilmName2);
		}
	};
	 public static Comparator<Film> FilmYearComparatorASC = new Comparator<Film>() {
			
			@Override
			public int compare(Film f1, Film f2) {
				  int FilmYear1 = f1.getyear();
				  int FilmYear2 = f2.getyear();
				return FilmYear1-FilmYear2;
			}
		};   
		   public static Comparator<Film> FilmCountryComparatorASC = new Comparator<Film>() {
				
				@Override
				public int compare(Film f1, Film f2) {
					  String FilmCountry1 = f1.getcountry().toUpperCase();
					  String FilmCountry2 = f2.getcountry().toUpperCase();
					return FilmCountry1.compareTo(FilmCountry2);
				}
			};
			 public static Comparator<Film> FilmDurationMinuteComparatorASC = new Comparator<Film>() {
					
					@Override
					public int compare(Film f1, Film f2) {
						  int FilmMinute1 = f1.getdurationminutes();
						  int FilmMinute2 = f2.getdurationminutes();
						return FilmMinute1-FilmMinute2;
					}
				};  
				public static Comparator<Film> FilmDirectorComparatorASC = new Comparator<Film>() {
					
					@Override
					public int compare(Film f1, Film f2) {
						  String FilmDirector1 = f1.getdirector().toUpperCase();
						  String FilmDirector2 = f2.getdirector().toUpperCase();
						return FilmDirector1.compareTo(FilmDirector2);
					}
				};
				   public static Comparator<Film> FilmNameComparatorDesc = new Comparator<Film>() {
						
						@Override
						public int compare(Film f1, Film f2) {
							  String FilmName1 = f1.getname().toUpperCase();
							  String FilmName2 = f2.getname().toUpperCase();
							return FilmName2.compareTo(FilmName1);
						}
					};
					 public static Comparator<Film> FilmYearComparatorDesc = new Comparator<Film>() {
							
							@Override
							public int compare(Film f1, Film f2) {
								  int FilmYear1 = f1.getyear();
								  int FilmYear2 = f2.getyear();
								return FilmYear2-FilmYear1;
							}
						};   
						   public static Comparator<Film> FilmCountryComparatorDesc = new Comparator<Film>() {
								
								@Override
								public int compare(Film f1, Film f2) {
									  String FilmCountry1 = f1.getcountry().toUpperCase();
									  String FilmCountry2 = f2.getcountry().toUpperCase();
									return FilmCountry2.compareTo(FilmCountry1);
								}
							};
							 public static Comparator<Film> FilmDurationMinuteComparatorDesc = new Comparator<Film>() {
									
									@Override
									public int compare(Film f1, Film f2) {
										  int FilmMinute1 = f1.getdurationminutes();
										  int FilmMinute2 = f2.getdurationminutes();
										return FilmMinute2-FilmMinute1;
									}
								};  
								public static Comparator<Film> FilmDirectorComparatorDesc = new Comparator<Film>() {
									
									@Override
									public int compare(Film f1, Film f2) {
										  String FilmDirector1 = f1.getdirector().toUpperCase();
										  String FilmDirector2 = f2.getdirector().toUpperCase();
										return FilmDirector2.compareTo(FilmDirector1);
									}
								};
	@Override
	public String toString() {
		return "<html>name: " + name + " |year: " + year + " |country: " + country + "<br>" + "generlist: " + genreList
				+ " |durationMinutes: " + durationMinutes + " |director: " + director + "<br>" + "description: "
				+ description+"<br>_______________________________________________________________________________</html>";

	}
}// end of class Film
