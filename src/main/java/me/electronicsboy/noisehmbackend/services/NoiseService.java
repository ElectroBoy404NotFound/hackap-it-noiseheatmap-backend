package me.electronicsboy.noisehmbackend.services;

import org.springframework.stereotype.Service;

import me.electronicsboy.noisehmbackend.models.NoiseMeasurement;
import me.electronicsboy.noisehmbackend.repositories.NoiseMeasurementRepository;

@Service
public class NoiseService {
	private final NoiseMeasurementRepository repo;

	public NoiseService(NoiseMeasurementRepository repo) {
		this.repo = repo;
	}

	public NoiseMeasurement saveMeasurement(double lat, double lon, double noiseLevel) {
		NoiseMeasurement m = new NoiseMeasurement();
		m.setLatitude(lat);
		m.setLongitude(lon);
		m.setNoiseLevel(noiseLevel);
		return repo.save(m);
	}

	public double getAverageNoise(double lat, double lon) {
		Double avg = repo.findAverageNoiseWithin(lat, lon);
		return avg != null ? avg : 0.0;
	}
}
