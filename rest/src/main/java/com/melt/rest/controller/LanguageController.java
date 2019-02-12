package com.melt.rest.controller;

import com.melt.rest.domain.Language;
import com.melt.rest.repo.LanguageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.swing.text.html.parser.Entity;

@RestController
@RequestMapping(produces = "application/json")
public class LanguageController {

    @Autowired
    private LanguageRepo languageRepo;

    @GetMapping
    public Flux<Language> index() {
        return languageRepo.findAll();
    }

    @GetMapping("/{name}")
    public Mono<Language> show(@PathVariable("name") String name) {
        return languageRepo.findByName(name);
    }

    @PostMapping(consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Language> create(@RequestBody Mono<Language> languageMono) {
        return languageRepo.saveAll(languageMono).next();
    }

    @DeleteMapping("/{name}")
    public Mono<ResponseEntity<Void>> delete(@PathVariable("name") String name) {
        return languageRepo.findByName(name)
                .flatMap(existingLanguage -> languageRepo.delete(existingLanguage)
                        .then(Mono.just(new ResponseEntity<Void>(HttpStatus.OK))))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PatchMapping("/{name}")
    public Mono<ResponseEntity<Language>> update(@PathVariable("name") String name,
                                                 @RequestBody Language language) {
        return languageRepo.findByName(name)
                .flatMap(existingLanguage -> {
                    if (language.getCreator() != null) {
                        existingLanguage.setCreator(language.getCreator());
                    }
                    if (language.getFeature() != null) {
                        existingLanguage.setCreator(language.getCreator());
                    }
                    return languageRepo.save(existingLanguage);
                }).map(upatedLanguage -> new ResponseEntity<>(upatedLanguage, HttpStatus.OK))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
