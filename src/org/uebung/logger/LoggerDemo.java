package org.uebung.logger;

public class LoggerDemo {

	public static void main(String[] args) {
		
		Logger l = new Logger("/temp/logger.txt");
		
		l.logDebug("Test pipapo");
		l.logFatal("Hallo ich bin FATAL!");
	}

}
