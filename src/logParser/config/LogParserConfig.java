package logParser.config;

import readers.ILogReader;
import writers.ILogWriter;

/**
 * Used to Configure Log Parser. 
 * If time permitted I would have like to attach a DB for configuration
 * 
 * @author Adam
 */
public class LogParserConfig {
	
	private static final String PROJECT_BASE = "C:/DevEnv/LMCC/src/";
	
	public LogParserConfig(){		
	}
	
	public ILogReader getLogReader(){
		return null;
	}
	
	public ILogWriter getLogWriter(){
		return null;
	}
	
	public String getDefaultTargetDirectory(){
		return PROJECT_BASE + "logParser/test";
	}
	
	
}
