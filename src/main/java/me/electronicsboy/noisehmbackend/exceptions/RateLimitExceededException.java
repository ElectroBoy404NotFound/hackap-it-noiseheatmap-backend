package me.electronicsboy.noisehmbackend.exceptions;

public class RateLimitExceededException extends RuntimeException {
	public RateLimitExceededException(String message) {
		super(message);
	}

	public RateLimitExceededException(Throwable cause) {
		super(cause);
	}

	public RateLimitExceededException(String message, Throwable cause) {
		super(message, cause);
	}
}
