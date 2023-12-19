package com.example.kichigin.taxsite.repository;

import com.example.kichigin.taxsite.database.Tax;
import org.springframework.data.repository.CrudRepository;

public interface TaxRepository extends CrudRepository<Tax, Long> {
}
