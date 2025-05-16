package me.electronicsboy.noisehmbackend.exceptions;

public class InvalidRefreshTokenException extends RuntimeException {
	public InvalidRefreshTokenException(String message) {
		super(message);
	}

	public InvalidRefreshTokenException(Throwable cause) {
		super(cause);
	}

	public InvalidRefreshTokenException(String message, Throwable cause) {
		super(message, cause);
	}
}
