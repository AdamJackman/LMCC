package ui;

import logParser.LogParser;
import logParser.config.LogParserConfig;

public class ProjectCommandLine {

	private static final String DELIMITER = "/";
	
	public ProjectCommandLine(){
		
	}
	
	/**
	 * Create the interface to the user
	 * @param args
	 */
	public static void main(String [] args){
		System.out.println("-----Starting Log Parser-----");
		if(args.length > 0){
			String fullPath; 
			
			//Treat as an absolute path if begins with a slash
			if(args[0].charAt(0) == '/'){								
				fullPath = args[0];
			} else {
				fullPath =  System.getProperty("user.dir") + DELIMITER + args[0];
			}
			
			//Check if the thread modifier is used
			if(args.length > 2 && args[1].equalsIgnoreCase("-T")){
				try{
					Integer threads = Integer.parseInt(args[2]);
					LogParserConfig.setWriterThreadCount(threads);
				} catch (Exception e){					
					printUsage();
					return;
				}
			}
			
			//Ready to run the parser
			LogParser parser = new LogParser(fullPath);
			parser.parseLogs();
			
			System.out.println("-----Log Parsing Complete----");
		} else {
			printUsage();
		}		
	}
	
	
	//setWriterThreadCount
	public static void printUsage(){
		System.out.println("This command takes a single arguement. \n"
				+ "That arguement should be the location of the directory with your log files. \n"
				+ "You can also use the modifier -T after the dir name followed by a number of threads to use.");
	}
}
