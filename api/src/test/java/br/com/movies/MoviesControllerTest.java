package br.com.movies;

import br.com.movies.entities.MovieData;
import br.com.movies.services.MovieDataService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest
public class MoviesControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MovieDataService movieDataService;


    @Test
    public void findAll() throws Exception {

        MovieData movieData = new MovieData();
        movieData.setProducers("Allan Carr");
        movieData.setStudios("Associated Film Distribution");
        movieData.setTitle("Can't Stop the Music");
        movieData.setWinner("yes");
        movieData.setYear(1980);

        List<MovieData> movies = Arrays.asList(movieData);
        given(movieDataService.findAll()).willReturn(movies);

        this.mockMvc.perform(get("/consulta"))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"movieData\":[{\"id\":0,\"year\":1980,\"title\":\"Can't Stop the Music\",\"studios\":\"Associated Film Distribution\",\"producers\":\"Allan Carr\",\"winner\":\"yes\"}]}"));
    }


}
