package com.reactivespring.controllers;

import com.reactivespring.domains.MovieInfo;
import com.reactivespring.services.MovieInfoService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/v1")
public class MovieInfoController {
    private MovieInfoService movieInfoService;

    public MovieInfoController(MovieInfoService movieInfoService) {
        this.movieInfoService = movieInfoService;
    }

    @GetMapping("/moviesinfo")
    public Flux<MovieInfo> getAllMovieInfo() {
        return movieInfoService.getAllMovieInfo();
    }

    @GetMapping("/moviesinfo/{id}")
    public Mono<MovieInfo> getMovieInfoById(@PathVariable String id) {
        return movieInfoService.getMovieInfoById(id);
    }

    @PostMapping("/moviesinfo")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<MovieInfo> addMovieInfo(@RequestBody MovieInfo movieInfo) {
        return movieInfoService.addMovieInfo(movieInfo);
    }
}
