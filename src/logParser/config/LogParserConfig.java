package logParser.config;

import readers.ILogReader;
import readers.SingleThreadReader;
import writers.ILogWriter;
import writers.MultiThreadWriter;
import writers.SingleThreadWriter;

/**
 * Used to Configure Log Parser. 
 * If time permitted I would have like to attach a DB for configuration
 * 
 * @author Adam
 */
public class LogParserConfig {
	
	//In the program's current state these 2 variables should be set to edit the behaviour to user wants.
	private static final boolean DEV_MODE = true;	
	private static final String PROJECT_BASE = System.getProperty("user.dir") +"/src/";
	
	private int writerThreadCount = 20;
	
	public LogParserConfig(){		
	}
	
	public ILogReader getLogReader(){
		return new SingleThreadReader();
	}
	
	public ILogWriter getLogWriter(){
		//return new SingleThreadWriter
		return new MultiThreadWriter();
	}
	
	public String getDefaultTargetDirectory(){
		return PROJECT_BASE + "logParser/test/";
	}
	
	public String getDefaultOutputDirectory(){
		if(DEV_MODE){
			//An output folder limits having to undo damage to original files
			return PROJECT_BASE + "logParser/test/output/";
		} else {
			return getDefaultTargetDirectory(); 
		}
	}
	
	public int getWriterThreadCount(){
		return writerThreadCount;
	}
	public void setWriterThreadCount(int threads){
		writerThreadCount = threads;
	}
	
}
