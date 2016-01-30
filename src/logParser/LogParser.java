package logParser;

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

	
	
}
