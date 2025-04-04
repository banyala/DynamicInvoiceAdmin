package com.example.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.entity.GlobalTemplate;

@Repository
public interface GlobalTemplateRepository extends JpaRepository<GlobalTemplate, Long>{

	Optional<GlobalTemplate> findByName(String name);

}
