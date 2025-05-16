package me.electronicsboy.noisehmbackend.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import me.electronicsboy.noisehmbackend.models.User;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {
	Optional<User> findByUsername(String username);
	Optional<User> findByEmail(String email);
}
