package readers;

import java.util.List;

import logParser.LogFile;

/**
 * The job of a reader is to take as input a List LogFiles.
 * Make sure we want to load the file and then load the file into the LogFile Bean.
 * 
 * @author Adam
 *
 */
public interface ILogReader {

		/**
		 * Load the given files
		 */
		public void loadFiles(List<LogFile> fileList);
}
