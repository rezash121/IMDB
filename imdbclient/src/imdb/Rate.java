package imdb;

import java.io.Serializable;
public class Rate implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5112440037398011256L;
	private String Filmname;
	private String username;
	private String rateNumber;
	private String title;
	private String Discription;
	private String FirstRefreeUsername;
	private String SecondRefreeUsername;
	private String ThirdRefreeUsername;
	private String FirstRefreeResult;
	private String SecondRefreeResult;
	private String ThirdRefreeResult;
	public Rate(String filmname,String username,String rateNumber,String title,String Discription,String FirstRefreeUsername,
			String SecondRefreeUsername,String ThirdRefreeUsername){
		this.Filmname=filmname;
		this.username=username;
		this.rateNumber=rateNumber;
		this.title=title;
		this.Discription=Discription;
		this.setFirstRefreeUsername(FirstRefreeUsername);
		this.setSecondRefreeUsername(SecondRefreeUsername);
		this.setThirdRefreeUsername(ThirdRefreeUsername);
		this.setFirstRefreeResult("");
		this.setSecondRefreeResult("");
		this.setThirdRefreeResult("");
		
	}
	public Rate(){
		this.Filmname="";
		this.username="";
		this.rateNumber="";
		this.title="";
		this.Discription="";
		
	}
	public String GetFilmName(){
		return this.Filmname;
	}
	public String GetUserName(){
		return this.username;
	}
	public String GetRate(){
		return this.rateNumber;
	}
	public String GetTitle(){
		return this.title;
	}
	public String GetDiscription(){
		return this.Discription;
	}
	public String toString(){
		return "<html><pre>UserName: "+username+"<br>RateNumber:  "+rateNumber+"   Title: "+title+
	"<br>Discription: "+Discription+"</pre></html>";
	}
	public String getFirstRefreeResult() {
		return FirstRefreeResult;
	}
	public void setFirstRefreeResult(String firstRefreeResult) {
		FirstRefreeResult = firstRefreeResult;
	}
	public String getSecondRefreeResult() {
		return SecondRefreeResult;
	}
	public void setSecondRefreeResult(String secondRefreeResult) {
		SecondRefreeResult = secondRefreeResult;
	}
	public String getThirdRefreeResult() {
		return ThirdRefreeResult;
	}
	public void setThirdRefreeResult(String thirdRefreeResult) {
		ThirdRefreeResult = thirdRefreeResult;
	}
	public String getSecondRefreeUsername() {
		return SecondRefreeUsername;
	}
	public void setSecondRefreeUsername(String secondRefreeUsername) {
		SecondRefreeUsername = secondRefreeUsername;
	}
	public String getThirdRefreeUsername() {
		return ThirdRefreeUsername;
	}
	public void setThirdRefreeUsername(String thirdRefreeUsername) {
		ThirdRefreeUsername = thirdRefreeUsername;
	}
	public String getFirstRefreeUsername() {
		return FirstRefreeUsername;
	}
	public void setFirstRefreeUsername(String firstRefreeUsername) {
		FirstRefreeUsername = firstRefreeUsername;
	}

}
