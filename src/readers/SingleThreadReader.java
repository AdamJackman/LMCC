package readers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import logParser.LogFile;

public class SingleThreadReader implements ILogReader{

	public SingleThreadReader(){
		
	}

	public List<BufferedReader> loadFiles(List<LogFile> LogFileList) {
		List<BufferedReader> buffList = new ArrayList<BufferedReader>();		
		for( LogFile logFile : LogFileList){
			String inputFile = logFile.getFile().getAbsolutePath();
			try {
				File input = new File(inputFile);
		    	InputStream stream = new FileInputStream(input);
		    	BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
		    	//we can now read lines
		    	buffList.add(reader);		        
			} catch (IOException e) {
				e.printStackTrace();
			}  	    	    		    	
		}
		return buffList;
	}
	
}
