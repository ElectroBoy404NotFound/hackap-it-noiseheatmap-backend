package me.electronicsboy.noisehmbackend.dtos;

public class NoiseLevelSubmissionDto {
	private double latitude;
	private double longitude;
	private double noiselevel;
	private int noiseType;
	
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	public double getNoiselevel() {
		return noiselevel;
	}
	public void setNoiselevel(double noiselevel) {
		this.noiselevel = noiselevel;
	}
	public int getNoiseType() {
		return noiseType;
	}
	public void setNoiseType(int noiseType) {
		this.noiseType = noiseType;
	}
}