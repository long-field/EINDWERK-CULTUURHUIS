package be.vdab.cultuurhuis.repositories;

import be.vdab.cultuurhuis.domain.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
@Sql("/insertsGenresAndVoorstellingen.sql")
class VoorstellingRepositoryTest extends AbstractTransactionalJUnit4SpringContextTests {

    @Autowired
    private GenreRepository genreRepository;
    @Autowired
    private VoorstellingRepository voorstellingRepository;
    private static final String VOORSTELLINGEN = "voorstellingen";
    @Test
    void saveVoorstelling() {
        var genre = new Genre("testGenreSave");
        genreRepository.save(genre);
        var voorstelling = new Voorstelling("testVoorstelling", "testVoorstelling", LocalDateTime.now(), genre, BigDecimal.ONE, 20, 1);
        voorstellingRepository.save(voorstelling);
        var id = voorstelling.getId();
        assertThat(id).isPositive();
        assertThat(countRowsInTableWhere(VOORSTELLINGEN,"id = " + id)).isOne();
    }

}