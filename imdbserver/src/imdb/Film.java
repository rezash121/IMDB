package imdb;

import java.util.List;
import java.io.Serializable;

public class Film implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;
	private int year;
	private String country;
	private List<String> genreList;
	private int durationMinutes;
	private String director;
	private String description;
	
	public Film(){
		this.name="ali jan";
		this.year=12312;
		this.country="abhar";
		this.genreList=null;
		this.durationMinutes=123;
		this.director="dsa";
		this.description="xm,c,mxncmnzxm";
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

	@Override
	public String toString() {
		return "name: " + name + " |year: " + year + " |country: " + country + "\n" + "generlist: " + genreList
				+ " |durationMinutes: " + durationMinutes + " |director: " + director + "\n" + "description: "
				+ description;

	}
}// end of class Film
