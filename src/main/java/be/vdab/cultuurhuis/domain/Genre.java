package be.vdab.cultuurhuis.domain;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Locale;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "genres")
public class Genre implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotBlank
    private String naam;
    @OneToMany(mappedBy = "genre")
    @OrderBy("datum")
    private Set<Voorstelling> voorstellingen;

    protected Genre() { }

    public Genre(@NotBlank String naam) {
        this.naam = naam;
        this.voorstellingen = new LinkedHashSet<>();
    }

    public long getId() {
        return id;
    }

    public String getNaam() {
        return naam;
    }

    public Set<Voorstelling> getVoorstellingen() {
        return voorstellingen;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Genre genre = (Genre) o;
        return naam.equals(genre.naam);
    }


    @Override
    public int hashCode() {
        return Objects.hash(naam.toLowerCase());
    }
}
