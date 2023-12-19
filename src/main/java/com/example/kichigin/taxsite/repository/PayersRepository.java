package com.example.kichigin.taxsite.repository;

import com.example.kichigin.taxsite.database.Payers;
import org.springframework.data.repository.CrudRepository;

public interface PayersRepository extends CrudRepository<Payers, Long> {
}
