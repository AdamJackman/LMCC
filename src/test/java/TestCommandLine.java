package test.java;

import org.junit.Ignore;
import org.junit.Test;

import ui.ProjectCommandLine;

public class TestCommandLine {

	/**
	 * Used to simulate running of the project from the command line
	 */
	@Test
	@Ignore
	public void test() {
		ProjectCommandLine.main(new String[] {"src\\logParser\\test"});
	}

}
