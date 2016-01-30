package logParser.config;

import readers.ILogReader;
import readers.SingleThreadReader;
import writers.ILogWriter;
import writers.SingleThreadWriter;

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
		return new SingleThreadReader();
	}
	
	public ILogWriter getLogWriter(){
		return new SingleThreadWriter();
	}
	
	public String getDefaultTargetDirectory(){
		return PROJECT_BASE + "logParser/test/";
	}
	
	
}
