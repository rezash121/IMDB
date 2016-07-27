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

	@Override
	public String toString() {
		return "<html>name: " + name + " |year: " + year + " |country: " + country + "<br>" + "generlist: " + genreList
				+ " |durationMinutes: " + durationMinutes + " |director: " + director + "<br>" + "description: "
				+ description+"<br>_______________________________________________________________________________</html>";

	}
}// end of class Film
