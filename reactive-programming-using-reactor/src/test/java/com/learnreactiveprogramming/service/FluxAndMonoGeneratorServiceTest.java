package com.learnreactiveprogramming.service;

import org.junit.jupiter.api.Test;
import reactor.test.StepVerifier;

import java.util.List;

class FluxAndMonoGeneratorServiceTest {

    FluxAndMonoGeneratorService fluxAndMonoGeneratorService = new FluxAndMonoGeneratorService();

    @Test
    void fluxNames() {
        var fluxNames = fluxAndMonoGeneratorService.fluxNames();

        StepVerifier.create(fluxNames)
                .expectNext("rob", "tay", "vini")
                .verifyComplete();

        StepVerifier.create(fluxNames)
                .expectNextCount(3)
                .verifyComplete();

        StepVerifier.create(fluxNames)
                .expectNext("rob")
                .expectNextCount(2)
                .verifyComplete();
    }

    @Test
    void fluxNamesMap() {
        int stringLength = 3;

        var fluxNames = fluxAndMonoGeneratorService.fluxNamesMap(stringLength);

        StepVerifier.create(fluxNames)
                .expectNext("4 - VINI")
                .verifyComplete();
    }

    @Test
    void fluxNamesImmutability() {
        var fluxNamesImmutability = fluxAndMonoGeneratorService.fluxNamesImmutability();

        StepVerifier.create(fluxNamesImmutability)
                .expectNext("rob", "tay", "vini")
                .verifyComplete();
    }

    @Test
    void fluxNamesFlatMap() {
        int stringLength = 3;

        var fluxNamesFlatMap = fluxAndMonoGeneratorService.fluxNamesFlatMap(stringLength);

        StepVerifier.create(fluxNamesFlatMap)
                .expectNext("V", "I", "N", "I")
                .verifyComplete();
    }

    @Test
    void fluxNamesFlatMapAsync() {
        int stringLength = 2;

        var fluxNamesFlatMapAsync = fluxAndMonoGeneratorService.fluxNamesFlatMapAsync(stringLength);

        StepVerifier.create(fluxNamesFlatMapAsync)
                .expectNextCount(10)
                .verifyComplete();
    }

    @Test
    void fluxNamesConcatMap() {
        int stringLength = 2;

        var fluxNamesConcatMap = fluxAndMonoGeneratorService.fluxNamesConcatMap(stringLength);

        StepVerifier.create(fluxNamesConcatMap)
                .expectNext("R", "O", "B", "T", "A", "Y", "V", "I", "N", "I")
                .verifyComplete();
    }

    @Test
    void monoNamesFlatMap() {
        int stringLength = 3;

        var monoNamesFlatMap = fluxAndMonoGeneratorService.monoNamesFlatMap(stringLength);

        StepVerifier.create(monoNamesFlatMap)
                .expectNext(List.of("A", "L", "E", "X"))
                .verifyComplete();
    }


    @Test
    void monoNamesFlatMapMany() {
        int stringLength = 3;

        var monoNamesFlatMap = fluxAndMonoGeneratorService.monoNamesFlatMapMany(stringLength);

        StepVerifier.create(monoNamesFlatMap)
                .expectNext("A", "L", "E", "X")
                .verifyComplete();
    }

    @Test
    void fluxNamesTransform() {
        int stringLength = 3;

        var fluxNamesTransform = fluxAndMonoGeneratorService.fluxNamesTransform(stringLength);

        StepVerifier.create(fluxNamesTransform)
                .expectNext("V", "I", "N", "I")
                .verifyComplete();
    }
}