package readers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import logParser.LogFile;

public class SingleThreadReader implements ILogReader{

	public SingleThreadReader(){
		
	}
	
	/**
	 * Sets up the buffered reader within a LogFile
	 */
	public void loadFiles(List<LogFile> LogFileList) {		
		for( LogFile logFile : LogFileList){
			String inputFile = logFile.getFile().getAbsolutePath();
			try {
				File input = new File(inputFile);
		    	InputStream stream = new FileInputStream(input);
		    	BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
		    	//we can now read lines
		    	logFile.setReader(reader);
			} catch (IOException e) {
				e.printStackTrace();
			}  	    	    		    	
		}
	}
	
}
