package be.vdab.cultuurhuis.domain;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Objects;

@Entity
@Table(name = "voorstellingen")
public class Voorstelling implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotBlank
    private String titel;
    @NotBlank
    private String uitvoerders;
    @NotNull
    private LocalDateTime datum;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "genreid")
    private Genre genre;
    @NotNull
    private BigDecimal prijs;
    @NotNull
    private int vrijeplaatsen;
    @Version
    private long versie;

    protected Voorstelling() { }

    public Voorstelling(@NotBlank String titel, @NotBlank String uitvoerders, @NotNull LocalDateTime datum, Genre genre, @NotNull BigDecimal prijs, @NotNull int vrijeplaatsen, long versie) {
        this.titel = titel;
        this.uitvoerders = uitvoerders;
        this.datum = datum;
        this.genre = genre;
        this.prijs = prijs;
        this.vrijeplaatsen = vrijeplaatsen;
        this.versie = versie;
    }

    public long getId() {
        return id;
    }

    public String getTitel() {
        return titel;
    }

    public String getUitvoerders() {
        return uitvoerders;
    }

    public LocalDateTime getDatum() {
        return datum;
    }
    public LocalDate getDag(){
        return LocalDate.from(datum);
    }
    public LocalTime getTijdstip(){
        return LocalTime.from(datum);
    }
    public String getStringDatum(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd LLLL yyyy");
        String date = formatter.format(getDatum());
        return date;
    }

    public Genre getGenre() {
        return genre;
    }

    public BigDecimal getPrijs() {
        return prijs;
    }

    public int getVrijeplaatsen() {
        return vrijeplaatsen;
    }

    public boolean isUitverkocht(){
        return (vrijeplaatsen == 0 ? true : false);
    }

    public long getVersie() {
        return versie;
    }
    public void setVrijeplaatsen(int vrijeplaatsen) {
        this.vrijeplaatsen = vrijeplaatsen;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Voorstelling that = (Voorstelling) o;
        return id == that.id && Objects.equals(datum, that.datum);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, datum);
    }
}
