package Scs.Exceptions;

import org.springframework.http.HttpStatus;

public class BadRequestException extends RuntimeException{
	
	private HttpStatus status;
	private String message;
	
	public BadRequestException(String message) {
		super(message);
	}
	
	public BadRequestException(String _message ,HttpStatus _status) {
		this.status = _status;
		this.message = _message;
	}
	
	public HttpStatus getStatus() {
		return status;
	}
	
	@Override
	public String getMessage() {
		return message;
	}
}
