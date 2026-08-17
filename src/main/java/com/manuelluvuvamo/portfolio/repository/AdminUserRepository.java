package com.manuelluvuvamo.portfolio.repository;

import com.manuelluvuvamo.portfolio.domain.AdminUser;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdminUserRepository extends MongoRepository<AdminUser, String> {

    Optional<AdminUser> findByEmailIgnoreCase(String email);
}
