package test.java;

import static org.junit.Assert.assertTrue;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import logParser.LogParser;
import logParser.config.LogParserConfig;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

public class TestLogParserLargeInput {
	
	LogParserConfig config;
	LogParser parser;
	
	@Before
	public void init(){
		config = new LogParserConfig(true);
		parser = new LogParser();
	}
	
	@Test
	@Ignore
	//Test the speed on 2079 log files. 
	//To prepare this test please run makeSomeFiles.bash in directory stressTest
	//This test is ignored as it requires set up.	
	public void stressTestOutput() throws IOException{
		LogParser stressedParser = new LogParser(System.getProperty("user.dir") +"/src/" + "logParser/test/stressTest/");
		stressedParser.parseLogs();
		String outputDir = config.getDefaultOutputDirectory();
		
		//Test the last line
		String testFile = "logtest.2020-09-30.log";
		File input = new File(outputDir + testFile);
    	InputStream stream = new FileInputStream(input);
    	BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
    	String line = reader.readLine();
    	assertTrue(line.equals("2079. stressTest"));
    	reader.close();
    	
    	//Test a line in the middle
    	testFile = "logtest.2017-03-19.log";
    	input = new File(outputDir + testFile);
    	stream = new FileInputStream(input);
    	reader = new BufferedReader(new InputStreamReader(stream));
    	line = reader.readLine();
    	assertTrue(line.equals("1375. stressTest"));
    	reader.close();
    	 
	}
	
}
