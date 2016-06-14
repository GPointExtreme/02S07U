package org.uebung.logger;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Logger {
	
	private String path;
	private String timeStamp = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss").format(Calendar.getInstance().getTime());

	public Logger(String path) {
		super();
		this.path = path;
	}
	
	private void logMessage(ErrorMessage message) {
		File file = new File(path);
		try (
			FileWriter fw = new FileWriter(file, true);
			PrintWriter pw = new PrintWriter(fw, true);
			) {
			pw.println(message.toLine());
			pw.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void logFatal(String message) {
		ErrorMessage fatal = new ErrorMessage(ErrorLevel.FATAL, message, timeStamp);
		logMessage(fatal);
	}
	
	public void logError(String message) {
		ErrorMessage error = new ErrorMessage(ErrorLevel.ERROR, message, timeStamp);
		logMessage(error);
	}
	
	public void logInfo(String message) {
		ErrorMessage info = new ErrorMessage(ErrorLevel.INFO, message, timeStamp);
		logMessage(info);
	}
	
	public void logDebug(String message) {
		ErrorMessage debug = new ErrorMessage(ErrorLevel.DEBUG, message, timeStamp);
		logMessage(debug);
	}
}
