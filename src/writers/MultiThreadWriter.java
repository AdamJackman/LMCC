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
	private static Semaphore lock = new Semaphore(1, false);		
	
	private String outputDirectory;
		
	public MultiThreadWriter() {
		LogParserConfig conf = new LogParserConfig();
		outputDirectory = conf.getDefaultOutputDirectory();
	}
	
	@Override
	public int write(List<LogFile> logFiles) {
		
		ExecutorService executor = Executors.newFixedThreadPool(10);
		for( LogFile lf : logFiles){
			Runnable worker = new MultiWriterRunnable(threadCounterGiven++, lf, outputDirectory);
            executor.execute(worker);
			if(threadCounterGiven > 10){
				try {
					executor.shutdown();
					executor.awaitTermination(1000, TimeUnit.MILLISECONDS);
				} catch (InterruptedException e) {
					System.out.println("Error: threads could not finish");
					e.printStackTrace();
				}
		        //Reset for another batch
		        threadCounterGiven = 1;
		        threadCounterWritten = 1;
		        executor = Executors.newFixedThreadPool(10);
			}
		}       
		return 0;
	}

	private static class MultiWriterRunnable implements Runnable {

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
				
				while(1==1){
					//System.out.println("Thread: " + priority + " wants in  >:(");
					if(threadCounterWritten == priority){
						lock.acquire();
				        while ((line = reader.readLine()) != null){
				        	//Add in line counter
				        	outputText += counter++ + ". " + line + "\n";
				        }
				        reader.close();
				        //Increment and unlock
				        threadCounterWritten++;
				        lock.release();
				        break;
					}
				}

		        // write the new String into the same fileName, directory is configurable
		        FileOutputStream fileOut = new FileOutputStream(outputDirectory + lf.getFileName());
		        fileOut.write(outputText.getBytes());
		        fileOut.close();
			} catch (Exception e){
				System.out.println("Thread Error: " + e.getStackTrace());
			}
			return;
			
		}
	}
}
