package com.learnreactiveprogramming.service;

import org.junit.jupiter.api.Test;
import reactor.test.StepVerifier;

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

        var fluxNamesFlatMap = fluxAndMonoGeneratorService.fluxNamesFlatMapAsync(stringLength);

        StepVerifier.create(fluxNamesFlatMap)
                .expectNextCount(10)
                .verifyComplete();
    }
}