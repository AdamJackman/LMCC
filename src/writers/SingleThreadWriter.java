package writers;

import java.io.BufferedReader;
import java.util.List;

public class SingleThreadWriter implements ILogWriter {

	static int counter = 0;
	
	public SingleThreadWriter() {
		
	}
	
	@Override
	public int write(List<BufferedReader> filesToWrite) {
		for(BufferedReader reader : filesToWrite){
			System.out.println(counter++);
		}
		//Successful return
		return 0;
	}

	
}
