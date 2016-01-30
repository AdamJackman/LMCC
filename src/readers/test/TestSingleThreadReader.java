package readers.test;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import logParser.LogFile;
import logParser.config.LogParserConfig;

import org.junit.Before;
import org.junit.Test;

import readers.ILogReader;

public class TestSingleThreadReader {

	public ILogReader reader;
	public File testFile;
	public List<LogFile> lfList;
	public String base;
	
    @Before
    public void init() {
		LogParserConfig conf = new LogParserConfig();
		base = conf.getDefaultTargetDirectory();
		reader = conf.getLogReader();
		testFile = new File(base + "logtest.2016-01-30.log");
		lfList = new ArrayList<LogFile>();
		LogFile lf = new LogFile(testFile, "logtest.2016-01-30.log");
		lfList.add(lf);
    }
	
	@Test
	public void testReader1() {
		reader.loadFiles(lfList);
		try {			
			assertTrue(lfList.get(0).getReader().readLine().equals("aaaaaaaa"));			
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			//closeReaders(bfList);
		}
	}
	
	@Test
	public void testReader2() {
		LogFile lf = new LogFile(new File(base + "logtest.2016-01-31.log"), "logtest.2016-01-31.log");
		LogFile lf2 = new LogFile(new File(base + "logtest.2016-02-01.log"), "logtest.2016-02-01.log");
		lfList.add(lf);
		lfList.add(lf2);
		reader.loadFiles(lfList);		
		try {			
			assertTrue(lf.getReader().readLine().equals("e"));
			assertTrue(lf2.getReader().readLine().equals("jjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj"));
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			//closeReaders(bfList);
		}
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
