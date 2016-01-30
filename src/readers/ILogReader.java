package readers;

import java.io.File;
import java.util.List;

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
		public List<File> loadFiles(List<String> fileNameList);
}
