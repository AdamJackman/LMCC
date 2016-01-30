package logParser.test;

import static org.junit.Assert.*;
import logParser.LogParser;

import org.junit.Test;

public class TestLogParserLargeInput {

	@Test
	//test the speed on 2000 log files
	public void stressTestOutput(){
		LogParser stressedParser = new LogParser(System.getProperty("user.dir") +"/src/" + "logParser/test/stressTest/");
		stressedParser.parseLogs();
	}
	
}
