package me.electronicsboy.noisehmbackend.dtos;

public class NoiseMeasurementWithAverageDTO {
    private double latitude;
    private double longitude;
    private double averageNoiseLevel;

    public NoiseMeasurementWithAverageDTO(double latitude, double longitude, double averageNoiseLevel) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.averageNoiseLevel = averageNoiseLevel;
    }

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

	public double getAverageNoiseLevel() {
		return averageNoiseLevel;
	}

	public void setAverageNoiseLevel(double averageNoiseLevel) {
		this.averageNoiseLevel = averageNoiseLevel;
	}
}
