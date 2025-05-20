package me.electronicsboy.noisehmbackend.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import me.electronicsboy.noisehmbackend.dtos.NoiseMeasurementWithAverageDTO;
import me.electronicsboy.noisehmbackend.models.NoiseMeasurement;
import me.electronicsboy.noisehmbackend.repositories.NoiseMeasurementRepository;

@Service
public class NoiseService {
	private final NoiseMeasurementRepository repo;

	public NoiseService(NoiseMeasurementRepository repo) {
		this.repo = repo;
	}

	public NoiseMeasurement saveMeasurement(double lat, double lon, double noiseLevel, int type) {
		NoiseMeasurement m = new NoiseMeasurement();
		m.setLatitude(lat);
		m.setLongitude(lon);
		m.setNoiseLevel(noiseLevel);
		m.setType(type);
		return repo.save(m);
	}

	public int getAverageNoise(double lat, double lon) {
		Integer avg = repo.findAverageNoiseWithin(lat, lon);
		return avg != null ? avg : -10;
	}
	
//	public List<NoiseMeasurementWithAverageDTO> getAllWithAverageNoise() {
//        List<NoiseMeasurement> allMeasurements = repo.findAll();
//
//        return allMeasurements.stream()
//            .map(m -> {
//                Double avg = repo.findAverageNoiseWithin(m.getLatitude(), m.getLongitude());
//                return new NoiseMeasurementWithAverageDTO(m.getLatitude(), m.getLongitude(), avg != null ? avg : 0.0);
//            })
//            .collect(Collectors.toList());
//    }

	
	public List<NoiseMeasurementWithAverageDTO> getClusteredAverageNoise() {
	    List<Object[]> raw = repo.findClusteredNoiseMeasurements();

	    return raw.stream()
	        .map(row -> new NoiseMeasurementWithAverageDTO(
	            ((Number) row[0]).doubleValue(),  // clusterLat
	            ((Number) row[1]).doubleValue(),  // clusterLng
	            ((Number) row[2]).doubleValue()   // avg noise
	        ))
	        .collect(Collectors.toList());
	}

}
