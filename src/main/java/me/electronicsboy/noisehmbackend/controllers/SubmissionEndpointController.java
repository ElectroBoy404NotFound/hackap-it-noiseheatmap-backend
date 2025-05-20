package me.electronicsboy.noisehmbackend.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import me.electronicsboy.noisehmbackend.annotations.RateLimited;
import me.electronicsboy.noisehmbackend.dtos.NoiseLevelSubmissionDto;
import me.electronicsboy.noisehmbackend.dtos.NoiseMeasurementWithAverageDTO;
import me.electronicsboy.noisehmbackend.models.NoiseMeasurement;
import me.electronicsboy.noisehmbackend.services.NoiseService;

@RequestMapping("/submit")
@RestController
public class SubmissionEndpointController {
	private final NoiseService noiseservice;
	
    public SubmissionEndpointController(NoiseService noiseservice) {
    	this.noiseservice = noiseservice;
	}
    
    @PostMapping("/submitlatlongnoisedb")
    @RateLimited(limit=1000)
    public ResponseEntity<NoiseMeasurement> handleSubmission(@RequestBody NoiseLevelSubmissionDto noiseLevelSubmissionDto) {
    	NoiseMeasurement nm = noiseservice.saveMeasurement(noiseLevelSubmissionDto.getLatitude(), noiseLevelSubmissionDto.getLongitude(), noiseLevelSubmissionDto.getNoiselevel(), noiseLevelSubmissionDto.getNoiseType());
        return ResponseEntity.ok(nm);
    }
    
    @GetMapping("/submitlatlongnoisedb")
    public ResponseEntity<List<NoiseMeasurementWithAverageDTO>> handleGet() {
        return ResponseEntity.ok(noiseservice.getClusteredAverageNoise());
    }
}
