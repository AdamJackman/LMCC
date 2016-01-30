package readers;

import java.io.BufferedReader;
import java.util.List;

import logParser.LogFile;

/**
 * The job of a reader is to take as input a List of Strings representing file names.
 * From this list the Files will be loaded, placed in a list and returned.
 * 
 * @author Adam
 *
 */
public interface ILogReader {

		/**
		 * Load the given filenames
		 */
		public void loadFiles(List<LogFile> fileNameList);
}
