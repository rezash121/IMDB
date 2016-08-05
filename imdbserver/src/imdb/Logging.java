package imdb;

import java.io.IOException;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Logging {
	
	private static Logger LOGGER ;
	private Handler fileHandler ;
	private Handler consoleHandler;
	public Logging(){
		LOGGER = Logger.getLogger("IMDB");
		try {
			//Creating fileHandler
			fileHandler  = new FileHandler("./imdb.XML");
			consoleHandler = new ConsoleHandler();
			
			//Assigning handler to LOGGER object
			LOGGER.addHandler(fileHandler);
			LOGGER.addHandler(consoleHandler);
			
			//Setting levels to handlers and LOGGER
			consoleHandler.setLevel(Level.ALL);
			fileHandler.setLevel(Level.ALL);
			LOGGER.setLevel(Level.ALL);
			
			LOGGER.config("Configuration done.");
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void info(String message){
		LOGGER.log(Level.INFO, message);
	}
	public void warning(String message){
		LOGGER.log(Level.WARNING, message);
	}
	

}
