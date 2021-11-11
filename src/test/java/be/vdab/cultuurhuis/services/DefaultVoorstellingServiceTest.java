package be.vdab.cultuurhuis.services;

import be.vdab.cultuurhuis.domain.Genre;
import be.vdab.cultuurhuis.domain.Klant;
import be.vdab.cultuurhuis.domain.Reservatie;
import be.vdab.cultuurhuis.domain.Voorstelling;
import be.vdab.cultuurhuis.repositories.ReservatieRepository;
import be.vdab.cultuurhuis.repositories.VoorstellingRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

import java.time.LocalDateTime;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
@Sql("/insertsGenresAndVoorstellingen.sql")

class DefaultVoorstellingServiceTest extends AbstractTransactionalJUnit4SpringContextTests {
    @Autowired
    private VoorstellingRepository voorstellingRepository;

    private long idVanGenre1() { return jdbcTemplate.queryForObject( "select id from genres where naam = 'testGenre1'", Long.class);}
    private long idVanGenre2() { return jdbcTemplate.queryForObject( "select id from genres where naam = 'testGenre2'", Long.class);}



    @Test
    void findByGenreIdPagedGeeftEnkelJuisteGenre() {
        Set<Voorstelling> pagina=voorstellingRepository.findByGenreIdAndDatumGreaterThanEqualOrderByDatumAsc(idVanGenre1(), LocalDateTime.now(), PageRequest.of(0,5)).toSet();
        assertThat(pagina.size()).isOne();
    }
    @Test
    void findByGenreIdPagedGeeftEnkelKomendeVoorstellingen() {
        Set<Voorstelling> pagina=voorstellingRepository.findByGenreIdAndDatumGreaterThanEqualOrderByDatumAsc(idVanGenre2(), LocalDateTime.now(), PageRequest.of(0,5)).toSet();
        assertThat(pagina.size()).isOne();
    }
}