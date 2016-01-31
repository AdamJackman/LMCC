package writers;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

import logParser.LogFile;
import logParser.config.LogParserConfig;

/**
 * The class will number each line in the log files.
 * Each thread will be given charge of writing a single file
 * The amount of threads to use in each batch (i.e. how many can be running at any time)
 * is given by WriterThreadCount in LogParserConfig
 * @author Adam
 *
 */
public class MultiThreadWriter implements ILogWriter {
	
	private static final int TIMEOUT = 1; 
	private static int counter = 1;
	private static int threadCounterWritten = 1;
	private static int threadCounterGiven = 1;	
	private int threadCount;	
	private String outputDirectory;
		
	
	public MultiThreadWriter() {
		LogParserConfig conf = new LogParserConfig();
		outputDirectory = conf.getDefaultOutputDirectory();
		threadCount = conf.getWriterThreadCount();		
	}
	
	/**
	 * Well create a new thread for each LogFile.
	 * Threads created in batches of threadCount size.
	 * Each thread will edit the file and place the output in the specified directory
	 */
	public int write(List<LogFile> logFiles) {
		
		ExecutorService executor = Executors.newFixedThreadPool(threadCount);
		try {		
			for( LogFile lf : logFiles){
				Runnable worker = new MultiWriterRunnable(threadCounterGiven++, lf, outputDirectory);
	            executor.execute(worker);
	            //If this batch is complete
				if(threadCounterGiven >= threadCount){
					//Shutdown current batch
					executor.shutdown();
					executor.awaitTermination(TIMEOUT, TimeUnit.MINUTES);					
			        //Reset for another batch
			        threadCounterGiven = 1;
			        threadCounterWritten = 1;
			        executor = Executors.newFixedThreadPool(threadCount);
				}
			}
			//Final Shutdown
			executor.shutdown();
			executor.awaitTermination(TIMEOUT, TimeUnit.MINUTES);	
			return 0;
		} catch (InterruptedException e){
			System.out.println("Error: threads could not finish");
			e.printStackTrace();
			return -1;
		}		
	}

	/**
	 * Runnable class that will do the file writing for MultiThreadWriter
	 * @author Adam
	 */
	private static class MultiWriterRunnable implements Runnable {

		private static final Semaphore lock = new Semaphore(1, false);
		
		private static final Object writingLock = new Object();
		private static final Object counterCheckLock = new Object();
		private int priority;
		private LogFile lf;
		private String outputDirectory;
		
		/**
		 * @param priority - this represents the ordering the file should be written to in
		 * @param lf
		 * @param outputDirectory
		 */
		public MultiWriterRunnable(int priority, LogFile lf, String outputDirectory) {
	      this.priority = priority;
	      this.lf = lf;
	      this.outputDirectory = outputDirectory;
		}		
		
		public void run() {
			try{
				BufferedReader reader = lf.getReader(); 
				String line;
				String outputText = "";

				while(true){
					
					//Only allow one thread to check at a time this significantly speeds up the result
					synchronized (counterCheckLock) {
						//Spin unless you have priority
						if(threadCounterWritten != priority || !lock.tryAcquire()){												
							continue;
						}
					}
					
					while ((line = reader.readLine()) != null){
			        	//Create the output 
			        	outputText += counter++ + ". " + line + "\n";
			        }
					
					synchronized (writingLock) {
						//Release Lock
				        threadCounterWritten++;
				        lock.release();
				        
				        FileOutputStream fileOut = new FileOutputStream(outputDirectory + lf.getFileName());
				        fileOut.write(outputText.getBytes());
				        fileOut.close();				        
				        break;
					}
				
				}
				
									        
			} catch (Exception e){
				e.printStackTrace();
			}
			return;
			
		}
	}
}
