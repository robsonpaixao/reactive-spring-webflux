package com.learnreactiveprogramming.service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.List;
import java.util.Random;
import java.util.function.Function;

public class FluxAndMonoGeneratorService {

    public Flux<String> fluxNames() {
        return Flux.fromIterable(List.of("rob", "tay", "vini"));
    }

    public Mono<String> fluxName() {
        return Mono.just("rob");
    }

    public Mono<List<String>> monoNamesFlatMap(int stringLength) {
        return Mono.just("alex")
                .map(String::toUpperCase)
                .filter(s -> s.length() > stringLength)
                .flatMap(this::splitStringMono);
    }

    public Flux<String> monoNamesFlatMapMany(int stringLength) {
        return Mono.just("alex")
                .map(String::toUpperCase)
                .filter(s -> s.length() > stringLength)
                .flatMapMany(this::splitString);
    }

    private Mono<List<String>> splitStringMono(String s) {
        var charArray = s.split("");
        return Mono.just(List.of(charArray));
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

    public Flux<String> fluxNamesTransform(int stringLength) {

        Function<Flux<String>, Flux<String>> filterMap = name -> name.map(String::toUpperCase)
                .filter(s -> s.length() > stringLength);

        return Flux.fromIterable(List.of("rob", "tay", "vini"))
                .transform(filterMap)
                .flatMap(s -> splitString(s))
                .defaultIfEmpty("default");
    }

    public Flux<String> fluxNamesTransformSwitchIfEmpty(int stringLength) {

        Function<Flux<String>, Flux<String>> filterMap = name -> name.map(String::toUpperCase)
                .filter(s -> s.length() > stringLength);

        var defaultFlux = Flux.just("default")
                .transform(filterMap)
                .flatMap(s -> splitString(s));

        return Flux.fromIterable(List.of("rob", "tay", "vini"))
                .transform(filterMap)
                .switchIfEmpty(defaultFlux);
    }

    public Flux<String> exploreConcat() {
        var abcFlux = Flux.just("A", "B", "C");
        var defFlux = Flux.just("D", "E", "F");

        return Flux.concat(abcFlux, defFlux);
    }

    public Flux<String> exploreConcatWith() {
        var abcFlux = Flux.just("A", "B", "C");
        var defFlux = Flux.just("D", "E", "F");

        return abcFlux.concatWith(defFlux);
    }

    public Flux<String> exploreMonoConcatWith() {
        var aMono = Mono.just("A");
        var bMono = Mono.just("B");

        return aMono.concatWith(bMono);
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
