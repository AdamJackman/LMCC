package logParser;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * The purpose of this class is to remember the date that the logFile is from.
 * This means it does not need to be recalculated at every step.
 * @author Adam
 *
 */
public class LogFile {
	
	private static final String DATE_FORMAT = "yyyy-MM-dd";	

	private String fileName;
	private File file;
	private Calendar cal;
	
	public LogFile(){
		
	}
	
	public LogFile(File file, String fileName){
		this.file = file;
		this.fileName = fileName;				
	}
	
	/**
	 * This function has 2 purposes:
	 * 1. It will check the fileName has a correct Date in it.
	 * 2. It will set the Calendar of the LogFile.
	 * @return valid or not
	 */
	public boolean checkValid(){
		String[] split = fileName.split("\\.");
		String[] dateSplit = split[1].split("-");
		
		//Check length is 3
		if(dateSplit.length != 3){
			return false;
		}
		//Attempt to format		
		try {			
			SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
			Date logDate = sdf.parse(split[1]);
			cal = Calendar.getInstance();
			cal.setTime(logDate);			
		} catch (ParseException e) {
			System.out.println("Log File Date Malformed for file: " + fileName + " ensure format is YYYY-MM-DD");
			return false;
		}
		return true;
	}
	
	
	//Getter /Setters
	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public File getFile() {
		return file;
	}

	public void setFile_(File file) {
		this.file = file;
	}

	public Calendar getCal_() {
		return cal;
	}

	public void setCal(Calendar cal) {
		this.cal = cal;
	}
}
