package com.example.kichigin.taxsite.repository;

import com.example.kichigin.taxsite.database.Person_payer;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface Person_payerRepository extends CrudRepository<Person_payer, Long> {

}
