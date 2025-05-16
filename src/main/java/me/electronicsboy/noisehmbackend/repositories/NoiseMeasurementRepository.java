package me.electronicsboy.noisehmbackend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import me.electronicsboy.noisehmbackend.models.NoiseMeasurement;

public interface NoiseMeasurementRepository extends JpaRepository<NoiseMeasurement, Long> {

    @Query(
      value = """
        SELECT AVG(n.noise_level)
         FROM noise_measurement n
         WHERE n.latitude - 0.0045 >= :lat
          AND n.latitude + 0.0045 <= :lat
      	  AND n.longitude - 0.005 >= :lon
      	  AND n.longitude + 0.005 <= :lon
      """,
      nativeQuery = true
    )
    Double findAverageNoiseWithin(
      @Param("lat") double latitude,
      @Param("lon") double longitude
    );
}
