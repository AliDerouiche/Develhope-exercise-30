package com.example.exerdemo.repositories;

import com.example.exerdemo.entities.ProgrammingLanguage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.Description;

import org.springframework.data.rest.core.annotation.RepositoryRestResource;


@RepositoryRestResource(path = "repo-prog-languages", collectionResourceDescription = @Description("programming language repository"))
public interface ProgrammingLanguageRepository extends JpaRepository<ProgrammingLanguage, Long> {
}