package ui;

import java.util.Scanner;

import logParser.LogParser;

public class ProjectCommandLine {

	public static Scanner keyboard = new Scanner(System.in);
	private static final String DELIMITER = "/";
	
	public ProjectCommandLine(){
		
	}
	
	/**
	 * Create the interface to the user
	 * @param args
	 */
	public static void main(String [] args){
		System.out.println("-----Starting Log Parser-----");
		if(args.length >= 1){			
			String fullPath =  System.getProperty("user.dir") + DELIMITER + args[0];			
			LogParser parser = new LogParser(fullPath);
			parser.parseLogs();
			System.out.println("-----Log Parsing Complete----");
		} else {
			printUsage();
		}		
	}
	
	public static void printUsage(){
		System.out.println("This command takes a single arguement. \n"
				+ "That arguement should be the location of the directory with your log files. \n"
				);
	}
}
