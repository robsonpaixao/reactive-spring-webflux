package com.learnreactiveprogramming.service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.List;
import java.util.Random;

public class FluxAndMonoGeneratorService {

    public Flux<String> fluxNames() {
        return Flux.fromIterable(List.of("rob", "tay", "vini"));
    }

    public Mono<String> fluxName() {
        return Mono.just("rob");
    }

    public Flux<String> fluxNamesMap(int stringLength) {
        return Flux.fromIterable(List.of("rob", "tay", "vini"))
                .map(String::toUpperCase)
                .filter(s -> s.length() > stringLength)
                .map(s -> s.length() + " - " + s);
    }

    public Flux<String> fluxNamesImmutability() {
        var namesFlux = Flux.fromIterable(List.of("rob", "tay", "vini"));
        namesFlux.map(String::toUpperCase);
        return namesFlux;
    }

    public Flux<String> fluxNamesFlatMap(int stringLength) {
        return Flux.fromIterable(List.of("rob", "tay", "vini"))
                .map(String::toUpperCase)
                .filter(s -> s.length() > stringLength)
                .flatMap(s -> splitString(s));
    }

    public Flux<String> fluxNamesFlatMapAsync(int stringLength) {
        return Flux.fromIterable(List.of("rob", "tay", "vini"))
                .map(String::toUpperCase)
                .filter(s -> s.length() > stringLength)
                .flatMap(s -> splitStringWithDelay(s));
    }

    public Flux<String> fluxNamesConcatMap(int stringLength) {
        return Flux.fromIterable(List.of("rob", "tay", "vini"))
                .map(String::toUpperCase)
                .filter(s -> s.length() > stringLength)
                .concatMap(s -> splitStringWithDelay(s));
    }

    public Flux<String> splitString(String name) {
        var charArray = name.split("");
        return Flux.fromArray(charArray);
    }

    public Flux<String> splitStringWithDelay(String name) {
        var charArray = name.split("");
        var delay = new Random().nextInt(5);
        return Flux.fromArray(charArray)
                .delayElements(Duration.ofMillis(delay));
    }

    public static void main(String[] args) {
        FluxAndMonoGeneratorService fluxAndMonoGeneratorService = new FluxAndMonoGeneratorService();
        fluxAndMonoGeneratorService.fluxNames()
                .subscribe(name -> System.out.println("Name is: " + name));


        fluxAndMonoGeneratorService.fluxName()
                .subscribe(name -> System.out.println("Mono name is: " + name));


        fluxAndMonoGeneratorService.fluxNamesMap(3)
                .subscribe(name -> System.out.println("Name map is: " + name));

        fluxAndMonoGeneratorService.fluxNamesFlatMap(3)
                .subscribe(name -> System.out.println("Name flatMap is: " + name));
    }
}
