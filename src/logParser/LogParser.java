package logParser;

import java.io.File;


import java.util.ArrayList;
import java.util.List;

import logParser.config.LogParserConfig;
import readers.ILogReader;
import writers.ILogWriter;


public class LogParser {
	
	private String targetDirectory;
	private ILogReader reader;
	private ILogWriter writer;
	
	public LogParser(){
		//Ask configuration class which reader and writer to use
		LogParserConfig conf = new LogParserConfig();
		reader = conf.getLogReader();
		writer = conf.getLogWriter();
		//Target directory from Default
		targetDirectory = conf.getDefaultTargetDirectory();		
	}
	
	public LogParser(String target){		
		//Ask configuration class which reader and writer to use
		LogParserConfig conf = new LogParserConfig();
		reader = conf.getLogReader();
		writer = conf.getLogWriter();
		//Target directory from String
		targetDirectory = target;
	}
	
	/**
	 * This will be the main driving function for parsing the logs
	 */
	public void parseLogs(){
		
		//Grab all the file names from the target directory (Add to a List)
		File folder = new File(targetDirectory);
		File[] listOfFiles = folder.listFiles();
		List<LogFile> logFiles = new ArrayList<LogFile>();

		//For each file in the directory check if it is a logFile, if so add to the List
	    for (int i = 0; i < listOfFiles.length; i++) {	    	
	    	String currFilename = listOfFiles[i].getName();	    	
			if (listOfFiles[i].isFile() && checkLogFileFormat(currFilename)) {
				//Create the file as a logFile and check date is valid
				LogFile lf = new LogFile(listOfFiles[i], currFilename);
				if (lf.checkValid()){
					logFiles.add(lf);
				}
			}
	    }
		
		//Filter out files that are not of the correct format
		//Sort all file names in the target directory 
		
		//Loop
		
			//Give list to the readers to load into memory
			//Reader will return List of loaded Files
			//If read was successful remove read files from list		
		
			//Give this list to the writer to edit the File
			//Writer will return Nothing
		
		//End Loop	
	}

	public boolean checkLogFileFormat(String fileName){
		String [] split = fileName.split("\\.");
		
		//Check correct length
		if (split.length != 3){
			return false;
		}
		//Check a log file
		if(!split[2].equalsIgnoreCase("log")){
			return false;
		}
						
		return true;
	}
	
	
}
