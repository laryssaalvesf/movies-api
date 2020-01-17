package br.com.movies.services;

import br.com.movies.entities.MovieData;
import br.com.movies.repositories.MovieDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieDataService {

    private final MovieDataRepository movieDataRepository;

    @Autowired
    public MovieDataService(MovieDataRepository movieDataRepository) {
        this.movieDataRepository = movieDataRepository;
    }

    public void save(MovieData movieData) {
        movieDataRepository.save(movieData);
    }

    public List<MovieData> findAll(){
        return movieDataRepository.findAll();
    }

    public List<MovieData> findByWinner(){
        return movieDataRepository.findByWinner("yes");
    }

}
