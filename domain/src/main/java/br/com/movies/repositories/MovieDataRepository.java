package br.com.movies.repositories;

import br.com.movies.entities.MovieData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieDataRepository extends JpaRepository<MovieData, Long> {

    List<MovieData> findByWinner(String win);

}
