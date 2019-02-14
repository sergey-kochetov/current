package com.melt.rest.fun;

import com.melt.rest.domain.Language;
import com.melt.rest.repo.LanguageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;
import static org.springframework.web.reactive.function.server.ServerResponse.noContent;
import static org.springframework.web.reactive.function.server.ServerResponse.notFound;

//@Configuration
public class RouterConfig {

    @Autowired
    private LanguageRepo languageRepo;

    @Bean
    public RouterFunction<?> routerFunction() {
        return route(GET("/lan"), this::getAllLanguages)
                .andRoute(GET("/lan/{name}"), this::getLanguageByName)
                .andRoute(POST("/lan"), this::postLanguage)
                .andRoute(DELETE("/lan/{name}"),
                        req -> languageRepo.findByName(req.pathVariable("name"))
                                .flatMap(l -> noContent().build(languageRepo.delete(l)))
                                .switchIfEmpty(notFound().build()));
    }

    private Mono<ServerResponse> getLanguageByName(ServerRequest req) {
        return ServerResponse.ok()
                .body(languageRepo.findByName(req.pathVariable("name")), Language.class);
    }

    private Mono<ServerResponse> postLanguage(ServerRequest request) {
        Mono<Language> languageMonoPost = request.bodyToMono(Language.class);
        Mono<Language> languageMonoSave = languageRepo.saveAll(languageMonoPost).next();
        return ServerResponse.ok()
                .body(languageMonoSave, Language.class);
    }

    private Mono<ServerResponse> getAllLanguages(ServerRequest request) {
        Flux<Language> all = languageRepo.findAll();
        return ServerResponse.ok()
                .body(all, Language.class);
    }
}
