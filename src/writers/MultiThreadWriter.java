package writers;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.util.List;
import java.util.concurrent.*;

import logParser.LogFile;
import logParser.config.LogParserConfig;

public class MultiThreadWriter implements ILogWriter {
	
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
	 * 
	 */
	@Override
	public int write(List<LogFile> logFiles) {
		
		ExecutorService executor = Executors.newFixedThreadPool(threadCount);
		for( LogFile lf : logFiles){
			Runnable worker = new MultiWriterRunnable(threadCounterGiven++, lf, outputDirectory);
            executor.execute(worker);
			if(threadCounterGiven >= threadCount){
				try {		
					executor.shutdown();
					executor.awaitTermination(100, TimeUnit.MILLISECONDS);					
				} catch (InterruptedException e) {
					System.out.println("Error: threads could not finish");
					e.printStackTrace();
					return -1;
				}
		        //Reset for another batch
		        threadCounterGiven = 1;
		        threadCounterWritten = 1;
		        executor = Executors.newFixedThreadPool(threadCount);
			}
		}       
		return 0;
	}

	private static class MultiWriterRunnable implements Runnable {

		private static final Semaphore lock = new Semaphore(1, false);
		private static final Object waitLock = new Object();
		private int priority;
		private LogFile lf;
		private String outputDirectory;
		
		public MultiWriterRunnable(int priority, LogFile lf, String outputDirectory) {
	      this.priority = priority;
	      this.lf = lf;
	      this.outputDirectory = outputDirectory;
		}		
		
		@Override
		public void run() {
			try{
				BufferedReader reader = lf.getReader(); 
				String line;
				String outputText = "";

				while(true){
					if(threadCounterWritten == priority){
						if(lock.tryAcquire()){
							break;
						}												
					}
					Thread.sleep(1);
				}
								
				synchronized (waitLock) {
					//We now have the lock
					while ((line = reader.readLine()) != null){
			        	//Add in line counter
			        	outputText += counter++ + ". " + line + "\n";
			        }
				}
				//Release Lock
		        threadCounterWritten++;
		        lock.release();
				
		        FileOutputStream fileOut = new FileOutputStream(outputDirectory + lf.getFileName());
		        fileOut.write(outputText.getBytes());
		        fileOut.close();
									        
			} catch (Exception e){
				e.printStackTrace();
			}
			return;
			
		}
		
		
	}
}
