package com.reactivespring.services;

import com.reactivespring.domains.MovieInfo;
import com.reactivespring.repositories.MovieInfoRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class MovieInfoService {
    private MovieInfoRepository movieInfoRepository;

    public MovieInfoService(MovieInfoRepository movieInfoRepository) {
        this.movieInfoRepository = movieInfoRepository;
    }

    public Mono<MovieInfo> addMovieInfo(MovieInfo movieInfo) {
        return movieInfoRepository.save(movieInfo);
    }
}
