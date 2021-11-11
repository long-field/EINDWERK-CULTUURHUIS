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
import static org.springframework.test.jdbc.JdbcTestUtils.countRowsInTableWhere;

@DataJpaTest
@Sql("/insertsGenresAndVoorstellingen.sql")
@Sql("/insertKlanten.sql")
@Sql("/insertReservaties.sql")
class ReservatieRepositoryTest extends AbstractTransactionalJUnit4SpringContextTests {
    @Autowired
    private ReservatieRepository reservatieRepository;
    @Autowired
    private GenreRepository genreRepository;
    @Autowired
    private VoorstellingRepository voorstellingRepository;
    @Autowired
    private KlantRepository klantRepository;
    private static final String RESERVATIES = "reservaties";
    @Test
    void saveReservatie() {
        var genre = new Genre("testGenreSave");
        genreRepository.save(genre);
        var klant = new Klant("testKlantSave", "testKlantSave", new Adres("test", "10", "3000", "test"),
                "test", "{bcrypt}$2a$10$854KE6i6qUDaAUZ9b0z9g.QntkAV.3omAALbl2FinQgRw6zlAJpmi");
        klantRepository.save(klant);
        var voorstelling = new Voorstelling("testVoorstelling", "testVoorstelling", LocalDateTime.now(), genre, BigDecimal.ONE, 20, 1);
        voorstellingRepository.save(voorstelling);
        var reservatie = new Reservatie(klant, voorstelling, 5);
        reservatieRepository.save(reservatie);
        var id = reservatie.getId();

        assertThat(id).isPositive();
        assertThat(countRowsInTableWhere(RESERVATIES,"id = " + id)).isOne(); }
}