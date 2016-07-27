package imdb;

import java.io.Serializable;

public class Response implements java.io.Serializable {

	private static final long serialVersionUID = -797676575735672524L;
	private String result;

	public Response() {
		result = "";
	}

	public String GetResult() {
		return result ;
	}
	public void SetResult(String message){
		result+=message;
	}

}