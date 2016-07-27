package imdb;

public class User {
	private String username;
	private String password;
	private String e_mail;
	private String TypeOfUser;
	public User(){
		username="";
		password="";
		e_mail="";
		TypeOfUser="guest";
	}
	public User(String username,String password,String e_mail,String TypeOfUser){
		this.username=username;
		this.password=password;
		this.e_mail=e_mail;
		this.TypeOfUser=TypeOfUser;
	}
	public void setusername(String username){
		this.username=username;
	}
	public void setpassword(String password){
		this.password=password;
	}
	public void sete_mail(String e_mail){
		this.e_mail=e_mail;
	}
	public void setTypeOfUser(String TypeOfUser){
		this.TypeOfUser=TypeOfUser;
	}
	public String getusername(){
		return this.username;
	}
	public String getpassword(){
		return this.password;
	}
	public String gete_mail(){
		return this.e_mail;
	}
	public String getTypeOfUser(){
		return this.TypeOfUser;
	}
	
}
