package org.uebung.logger;

public class ErrorMessage {
	
	private ErrorLevel errorLevel;
	private String errorMessage;
	private String errorTime;
	
	public ErrorMessage(ErrorLevel errorLevel, String errorMessage, String errorTime) {
		super();
		this.errorLevel = errorLevel;
		this.errorMessage = errorMessage;
		this.errorTime = errorTime;
	}
	
	public String toLine() {
		return errorTime + " " + errorLevel + " - " + errorMessage;
	}

	public ErrorLevel getErrorLevel() {
		return errorLevel;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public String getErrorTime() {
		return errorTime;
	}

}
