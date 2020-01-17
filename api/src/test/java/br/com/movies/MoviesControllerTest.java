package br.com.movies;

import br.com.movies.entities.MovieData;
import br.com.movies.services.MovieDataService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest
public class MoviesControllerTest {

    private static final String ALL_MOVIES_JSON = "{\"movieData\":[{\"id\":0,\"year\":1980,\"title\":\"Can't Stop the Music\",\"studios\":\"Associated Film Distribution\",\"producers\":\"Allan Carr\",\"winner\":\"yes\"},{\"id\":1,\"year\":1980,\"title\":\"Friday the 13th\",\"studios\":\"Paramount Pictures\",\"producers\":\"Sean S. Cunningham\"},{\"id\":2,\"year\":1984,\"title\":\"Where the Boys Are '84\",\"studios\":\"TriStar Pictures\",\"producers\":\"Allan Carr\",\"winner\":\"yes\"},{\"id\":3,\"year\":1984,\"title\":\"Bolero\",\"studios\":\"Cannon Films\",\"producers\":\"Bo Derek\", \"winner\":\"yes\"},{\"id\":4,\"year\":1990,\"title\":\"Ghosts Can't Do It\",\"studios\":\"Triumph Releasing\",\"producers\":\"Bo Derek\",\"winner\":\"yes\"}]}";
    private static final String WINNER_JSON = "{\"min\":{\"producer\":\"Allan Carr\",\"interval\":4,\"previousWin\":1980,\"followingWin\":1984},\"max\":{\"producer\":\"Bo Derek\",\"interval\":6,\"previousWin\":1984,\"followingWin\":1990}}";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MovieDataService movieDataService;

    @Before
    public void init() {
        defineGivenWillReturn();
    }

    @Test
    public void findAllMovies() throws Exception {

        this.mockMvc.perform(get("/consulta"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().isOk())
                .andExpect(content().json(ALL_MOVIES_JSON));
    }


    @Test
    public void findAwardWinners() throws Exception {

        this.mockMvc.perform(get("/consulta/intervalo-premios"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().isOk())
                .andExpect(content().json(WINNER_JSON));
    }

    private void defineGivenWillReturn() {
        MovieData movieData = new MovieData();
        movieData.setProducers("Allan Carr");
        movieData.setStudios("Associated Film Distribution");
        movieData.setTitle("Can't Stop the Music");
        movieData.setWinner("yes");
        movieData.setYear(1980);
        movieData.setId(0);

        MovieData movieData1 = new MovieData();
        movieData1.setProducers("Sean S. Cunningham");
        movieData1.setStudios("Paramount Pictures");
        movieData1.setTitle("Friday the 13th");
        movieData1.setYear(1980);
        movieData1.setId(1);

        MovieData movieData2 = new MovieData();
        movieData2.setProducers("Allan Carr");
        movieData2.setStudios("TriStar Pictures");
        movieData2.setTitle("Where the Boys Are '84");
        movieData2.setWinner("yes");
        movieData2.setYear(1984);
        movieData2.setId(2);

        MovieData movieData3 = new MovieData();
        movieData3.setProducers("Bo Derek");
        movieData3.setStudios("Cannon Films");
        movieData3.setTitle("Bolero");
        movieData3.setWinner("yes");
        movieData3.setYear(1984);
        movieData3.setId(3);

        MovieData movieData4 = new MovieData();
        movieData4.setProducers("Bo Derek");
        movieData4.setStudios("Triumph Releasing");
        movieData4.setTitle("Ghosts Can't Do It");
        movieData4.setWinner("yes");
        movieData4.setYear(1990);
        movieData4.setId(4);

        given(movieDataService.findAll()).willReturn(Arrays.asList(movieData, movieData1, movieData2, movieData3, movieData4));
        given(movieDataService.findByWinner()).willReturn(Arrays.asList(movieData, movieData2, movieData3, movieData4));
    }


}
