package logParser.test;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import logParser.LogParser;
import logParser.config.LogParserConfig;

import org.junit.Before;
import org.junit.Test;

public class TestLogParser {

	LogParserConfig config;
	LogParser parser;
	
	@Before
	public void init(){
		config = new LogParserConfig();
		parser = new LogParser();
		parser.parseLogs();
	}
	
	//Test 1 to init the program - this is mainly just for dev purposes
	@Test
	@SuppressWarnings("unused")
	public void testInit() {
		LogParser parser = new LogParser();		
	}
	
	@Test
	public void testCheckLogFormat() {
		LogParser parser = new LogParser();
		boolean test = parser.checkLogFileFormat("logtest.2016-01-30.log");
		assertTrue(test);
		//The Date is purposely not checked here due to the overhead of formatting, an unchecked segment here is fine
		test = parser.checkLogFileFormat("logging.uncheckedSegment.log");
		assertTrue(test);		
		test = parser.checkLogFileFormat("logtest.2016-01-30.txt");
		assertFalse(test);
		test = parser.checkLogFileFormat("logtest.2016-01-30.log.log");
		assertFalse(test);
	}	
	
	@Test
	public void testOutputPlaced() throws IOException{
		String outputDir = config.getDefaultOutputDirectory();
		String testFile = "logtest.2016-01-30.log";
		File input = new File(outputDir + testFile);
    	InputStream stream = new FileInputStream(input);
    	BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
    	String line = reader.readLine();
    	assertTrue(line.equals("1. aaaaaaaa"));
    	reader.close();		
	}
	
	@Test
	public void testOutputPlaced2() throws IOException{		
		String outputDir = config.getDefaultOutputDirectory();
		String testFile = "logtest.2016-01-31.log";
		File input = new File(outputDir + testFile);
    	InputStream stream = new FileInputStream(input);
    	BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
    	String line = reader.readLine();
    	assertTrue(line.equals("5. e"));
    	reader.close();		
	}
	
	@Test
	public void testOutputPlaced3() throws IOException{
		String outputDir = config.getDefaultOutputDirectory();
		String testFile = "logtest.2016-02-01.log";
		File input = new File(outputDir + testFile);
    	InputStream stream = new FileInputStream(input);
    	BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
    	String line = reader.readLine();
    	assertTrue(line.equals("10. jjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj"));
    	reader.close();		
	}

}
