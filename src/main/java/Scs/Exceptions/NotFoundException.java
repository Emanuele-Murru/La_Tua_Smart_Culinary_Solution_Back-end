package Scs.Exceptions;

import java.util.UUID;

public class NotFoundException extends RuntimeException{
	
	public NotFoundException(String message) {
		super(message);
	}
	
	public NotFoundException(UUID id) {
		super(id + " not found!!");
	}

	public NotFoundException(Long id) {
		super(id + " not found!!");
	}
	
}
