package logParser.test;

import static org.junit.Assert.*;
import logParser.LogParser;

import org.junit.Test;

public class TestLogParser {

	//Test 1 to init the program
	@Test
	public void testInit() {
		LogParser parser = new LogParser();		
	}
	
	//Test 2 to run the program
	@Test
	public void testParse() {
		LogParser parser = new LogParser();
		parser.parseLogs();
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


}
