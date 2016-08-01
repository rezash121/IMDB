package imdb;

public class DatabaseType {
	
	private String DataBase;
	
	public DatabaseType(){
		DataBase="json";
	}
	
	public String getDataBaseType(){
		return DataBase;
	}
	
	public void SetDataBaseType(String ChangedDatabase){
		DataBase=ChangedDatabase;
	}
}
