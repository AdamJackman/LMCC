package logParser.config;

import readers.ILogReader;
import readers.SingleThreadReader;
import writers.ILogWriter;
import writers.MultiThreadWriter;

/**
 * Used to Configure Log Parser. 
 * If time permitted I would have like to attach a DB for configuration
 * 
 * @author Adam
 */
public class LogParserConfig {
	
	private static final String PROJECT_BASE = System.getProperty("user.dir") +"/src/";
	
	private boolean devMode = false;
	private static int writerThreadCount = 100;
	
	public LogParserConfig(){		
	}
	
	public LogParserConfig(boolean devMode){
		this.devMode = devMode;
	}
	
	public ILogReader getLogReader(){
		return new SingleThreadReader();
	}
	
	public ILogWriter getLogWriter(){
		//return new SingleThreadWriter();
		return new MultiThreadWriter();
	}
	
	public String getDefaultTargetDirectory(){
		return PROJECT_BASE + "logParser/test/";
	}
	
	public String getDefaultOutputDirectory(){
		if(devMode){
			//An output folder limits having to undo damage to original files
			return PROJECT_BASE + "logParser/test/output/";
		} else {
			return getDefaultTargetDirectory(); 
		}
	}
	
	public int getWriterThreadCount(){
		return writerThreadCount;
	}
	
	public static void setWriterThreadCount(int threads){
		writerThreadCount = threads;
	}
	
}
