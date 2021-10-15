package be.vdab.cultuurhuis.domain;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Entity
@Table(name = "klanten")
public class Klant implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotBlank
    private String voornaam;
    @NotBlank
    private String familienaam;
    @Valid
    @Embedded
    private Adres adres;
    @NotBlank
    private String gebruikersnaam;
    @NotBlank
    private String paswoord;

    protected Klant() { }

    public Klant(@NotBlank String voornaam, @NotBlank String familienaam, @Valid Adres adres, @NotBlank String gebruikersnaam, @NotBlank String paswoord) throws NullPointerException {
        if (adres == null) throw new NullPointerException("Adres kan niet null zijn");
        this.voornaam = voornaam;
        this.familienaam = familienaam;
        this.adres = adres;
        this.gebruikersnaam = gebruikersnaam;
        this.paswoord = "{bcrypt}" + paswoord;
    }

    public long getId() {
        return id;
    }

    public String getVoornaam() {
        return voornaam;
    }

    public String getFamilienaam() {
        return familienaam;
    }

    public Adres getAdres() {
        return adres;
    }

    public String getGebruikersnaam() {
        return gebruikersnaam;
    }

    public String getPaswoord() {
        return paswoord;
    }
}
