package me.electronicsboy.noisehmbackend.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import me.electronicsboy.noisehmbackend.models.NoiseMeasurement;

public interface NoiseMeasurementRepository extends JpaRepository<NoiseMeasurement, Long> {

    @Query(
      value = """
        SELECT AVG(n.noise_level)
         FROM noise_measurement n
         WHERE 1=1
          AND n.latitude - 0.0018 >= :lat
          AND n.latitude + 0.0018 <= :lat
      	  AND n.longitude - 0.0018 >= :lon
      	  AND n.longitude + 0.0018 <= :lon
      	  AND n.recorded_at >= NOW() - INTERVAL 1 HOUR;
      """,
      nativeQuery = true
    )
    Integer findAverageNoiseWithin(
      @Param("lat") double latitude,
      @Param("lon") double longitude
    );
    @Query(value = """
	    SELECT
    		ROUND(latitude / 0.0018) * 0.0018 AS clusterLat,
		    ROUND(longitude / 0.0018) * 0.0018 AS clusterLng,
		    AVG(noise_level) AS averageNoise
		FROM
		    noise_measurement
		WHERE 1=1
    		AND noise_level > -10
    		AND recorded_at >= NOW() - INTERVAL 1 HOUR;
		GROUP BY
		    ROUND(latitude / 0.0018) * 0.0018,
		    ROUND(longitude / 0.0018) * 0.0018
	    """, nativeQuery = true)
	List<Object[]> findClusteredNoiseMeasurements();
}
