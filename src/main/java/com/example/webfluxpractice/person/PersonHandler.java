package com.example.webfluxpractice.person;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
public class PersonHandler {

    private PersonRepository repository;

    @Autowired
    public PersonHandler(PersonRepository repository) {
        this.repository = repository;
    }

    Mono<ServerResponse> listPeople(ServerRequest request){
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(repository.findAll(), Person.class);
    }


    Mono<ServerResponse> savePeople(ServerRequest request){
        Mono<Person> person = request.bodyToMono(Person.class)
                .flatMap(repository::save);
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(person, Person.class);
    }


    Mono<ServerResponse> sayHello(ServerRequest request){
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(Mono.just("Hello World!"), String.class);
    }
}
