package me.electronicsboy.noisehmbackend.exceptions;

public class UnprivilagedExpection extends RuntimeException {
	public UnprivilagedExpection(String message) {
		super(message);
	}

	public UnprivilagedExpection(Throwable cause) {
		super(cause);
	}

	public UnprivilagedExpection(String message, Throwable cause) {
		super(message, cause);
	}
}
