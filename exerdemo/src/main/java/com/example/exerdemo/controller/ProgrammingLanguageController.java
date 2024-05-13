package com.example.exerdemo.controller;


import com.example.exerdemo.entities.ProgrammingLanguage;
import com.example.exerdemo.repositories.ProgrammingLanguageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/programmingLanguage")
public class ProgrammingLanguageController {

    @Autowired
    private ProgrammingLanguageRepository programmingLanguageRepository;

    @PostMapping
    public ResponseEntity<ProgrammingLanguage> insertProgrammingLanguage(@RequestBody ProgrammingLanguage programmingLanguage) {
        ProgrammingLanguage savedProg = programmingLanguageRepository.save(programmingLanguage);
        return ResponseEntity.ok().body(savedProg);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProgrammingLanguage> getProgrammingLanguageById(@PathVariable(name = "id") Long id) {

        return programmingLanguageRepository.findById(id).map(progLang -> ResponseEntity.ok().body(progLang))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<ProgrammingLanguage>> getAllProgrammingLanguages() {
        return programmingLanguageRepository.count() == 0 ? ResponseEntity.noContent().build() : ResponseEntity.ok().body(programmingLanguageRepository.findAll());
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ProgrammingLanguage> updateProgrammingLanguage(@PathVariable(name = "id") Long id, @RequestParam String invetor) {
        return programmingLanguageRepository.findById(id)
                .map(progLang -> {
                    progLang.setInventor(invetor);
                    ProgrammingLanguage updatedCar = programmingLanguageRepository.save(progLang);
                    return ResponseEntity.ok().body(updatedCar);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProgrammingLanguage(@PathVariable Long id) {
        if (programmingLanguageRepository.existsById(id)) {
            programmingLanguageRepository.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteAllProgrammingLanguages() {
        programmingLanguageRepository.deleteAll();
        return ResponseEntity.noContent().build();
    }
}