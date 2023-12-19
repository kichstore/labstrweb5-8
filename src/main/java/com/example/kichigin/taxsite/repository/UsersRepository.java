package com.example.kichigin.taxsite.repository;

import com.example.kichigin.taxsite.database.Users;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UsersRepository extends CrudRepository<Users, Long> {
    Optional<Users> findByLogin(String login);
}
