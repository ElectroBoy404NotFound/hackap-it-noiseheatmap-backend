package me.electronicsboy.noisehmbackend.exceptions;

public class InvalidRequestException extends RuntimeException {
	public InvalidRequestException(String message) {
		super(message);
	}

	public InvalidRequestException(Throwable cause) {
		super(cause);
	}

	public InvalidRequestException(String message, Throwable cause) {
		super(message, cause);
	}
}
