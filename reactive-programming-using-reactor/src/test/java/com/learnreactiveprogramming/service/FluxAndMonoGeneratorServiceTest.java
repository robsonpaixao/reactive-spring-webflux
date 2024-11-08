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
        var fluxNames = fluxAndMonoGeneratorService.fluxNamesMap();

        StepVerifier.create(fluxNames)
                .expectNext("ROB", "TAY", "VINI")
                .verifyComplete();
    }

    @Test
    void fluxNamesImmutability() {
        var fluxNamesImmutability = fluxAndMonoGeneratorService.fluxNamesImmutability();

        StepVerifier.create(fluxNamesImmutability)
                .expectNext("rob", "tay", "vini")
                .verifyComplete();
    }
}