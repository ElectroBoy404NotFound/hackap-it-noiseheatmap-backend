package me.electronicsboy.noisehmbackend.responses;

public class LogoutResponse {
	private int status;

	public int getStatus() {
		return status;
	}

	public LogoutResponse setStatus(int status) {
		this.status = status;
		return this;
	}
}
