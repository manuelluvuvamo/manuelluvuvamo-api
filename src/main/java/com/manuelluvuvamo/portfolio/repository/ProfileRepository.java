package com.manuelluvuvamo.portfolio.repository;

import com.manuelluvuvamo.portfolio.domain.Profile;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProfileRepository extends MongoRepository<Profile, String> {

    Optional<Profile> findByKey(String key);
}
