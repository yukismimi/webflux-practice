package com.example.webfluxpractice.person;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.config.EnableWebFlux;
import org.springframework.web.reactive.config.WebFluxConfigurer;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;

import static org.springframework.web.reactive.function.server.RequestPredicates.accept;

@Configuration
@EnableWebFlux
public class PersonConfig implements WebFluxConfigurer {

    private PersonHandler handler;

    @Autowired
    public PersonConfig(PersonHandler handler) {
        this.handler = handler;
    }

    @Bean
    public RouterFunction<?> personRouter(){
        return RouterFunctions.route()
                .GET("/person", accept(MediaType.APPLICATION_JSON), handler::listPeople)
                .GET("/hello-world", accept(MediaType.APPLICATION_JSON), handler::sayHello)
                .POST("/person", accept(MediaType.APPLICATION_JSON),handler::savePeople)
                .build();
    }

}
