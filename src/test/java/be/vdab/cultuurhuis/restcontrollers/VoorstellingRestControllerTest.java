package be.vdab.cultuurhuis.restcontrollers;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
//import org.junit.jupiter.api.Test;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
//import org.junit.Test;

//import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(MockitoExtension.class)
@Sql("/insertsGenresAndVoorstellingen.sql")
public class VoorstellingRestControllerTest extends AbstractTransactionalJUnit4SpringContextTests {
    private  final MockMvc mvc;

    VoorstellingRestControllerTest(MockMvc mvc) {
        this.mvc = mvc;
    }

   private long idVanTestGenre() {
        return jdbcTemplate.queryForObject( "select id from genres where naam = 'testGenre1'", Long.class);
    }


    @Test
    public void getVoorstellingenDtoPagedHeeftStatusOk() throws Exception {

       mvc.perform(get("/voorstellingdata/paged?page=0&total=100"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json;"));


    }
    @Test
    public void getVoorstellingenDtoPagedGenreHeeftStatusOk() throws Exception {

        mvc.perform(get("/voorstellingdata/paged/genre/"+idVanTestGenre()+"?page=0&total=100"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json;"));


    }
}