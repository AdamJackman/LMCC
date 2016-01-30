package writers;

import java.io.BufferedReader;
import java.util.List;

/**
 * The job of the writer is to take in a List of Files and to write on to each of these.
 * The specifics of what is written is left to the specific implementation
 * @author Adam
 *
 */
public interface ILogWriter {
	
	/**
	 * Takes in a list of files and will write to each
	 * @param filesToWrite
	 * @return -1 for error, 0 otherwise;
	 */
	public int write(List<BufferedReader> filesToWrite);
	
}
