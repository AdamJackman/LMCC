package writers;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import logParser.LogFile;
import logParser.config.LogParserConfig;

public class SingleThreadWriter implements ILogWriter {

	static int counter = 1;
	private String outputDirectory;
	
	public SingleThreadWriter() {
		LogParserConfig conf = new LogParserConfig();
		outputDirectory = conf.getDefaultOutputDirectory();
	}
	
	@Override
	public int write(List<LogFile> logFiles) {
		try{
			for(LogFile lf : logFiles){
				BufferedReader reader = lf.getReader(); 
				String line;
				String outputText = "";
		        while ((line = reader.readLine()) != null){
		        	//Add in line counter
		        	outputText += counter++ + ". " + line + "\n";
		        }
		        reader.close();

		        // write the new String into the same fileName, directory is configurable
		        FileOutputStream fileOut = new FileOutputStream(outputDirectory + lf.getFileName());
		        fileOut.write(outputText.getBytes());
		        fileOut.close();
			}
		} catch (Exception e){
			e.getStackTrace();
			return -1;
		} finally {
			closeReaders(logFiles);
		}
		//Successful return
		return 0;
	}
	
	/**
	 * Close all Readers in the given LogFile list
	 * @param lfList
	 */
	public void closeReaders(List<LogFile> lfList){		
		try {
			for (LogFile lf : lfList){
				BufferedReader reader = lf.getReader();
				if(reader != null){
					reader.close();	
				}				
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	
}
