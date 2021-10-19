package be.vdab.cultuurhuis.forms;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

public class NieuweKlantForm {
    @NotBlank
    @Pattern(regexp = "^[a-zA-Z]*$" ,  message="Ongeldig!")
    private String voornaam;
    @NotBlank
    @Pattern(regexp = "^[a-zA-Z]*$" ,  message="Ongeldig!")
    private String familienaam;
    @NotBlank
    private String straat;
    @NotBlank
    @Pattern(regexp = "^[0-9]+$", message="Ongeldig!")
    private String huisnr;
    @NotBlank
    @Pattern(regexp = "^(?:(?:[1-9])(?:\\d{3}))$", message="Ongeldig!")
    private String postcode;
    @NotBlank
    private String gemeente;
    @NotBlank
    private String gebruikersnaam;
    @NotBlank
    @Pattern(regexp = "^(?=.{4,20}$)(?![_.])(?!.*[_.]{2})[a-zA-Z0-9._]+(?<![_.])$" ,  message="Ongeldig!")
    private String paswoord;
    @NotBlank
    private String herhaalPaswoord;


    public NieuweKlantForm(String voornaam, String familienaam, String straat, String huisnr, String postcode, String gemeente, String gebruikersnaam, String paswoord, String herhaalPaswoord) {
        this.voornaam = voornaam;
        this.familienaam = familienaam;
        this.straat = straat;
        this.huisnr = huisnr;
        this.postcode = postcode;
        this.gemeente = gemeente;
        this.gebruikersnaam = gebruikersnaam;
        this.paswoord = paswoord;
        this.herhaalPaswoord = herhaalPaswoord;
    }

    public String getVoornaam() {
        return voornaam;
    }

    public String getFamilienaam() {
        return familienaam;
    }

    public String getStraat() {
        return straat;
    }

    public String getHuisnr() {
        return huisnr;
    }

    public String getPostcode() {
        return postcode;
    }

    public String getGemeente() {
        return gemeente;
    }

    public String getGebruikersnaam() {
        return gebruikersnaam;
    }

    public String getPaswoord() {
        return paswoord;
    }

    public String getHerhaalPaswoord() {
        return herhaalPaswoord;
    }

    public boolean isPaswoordMatch(String paswoord,String herhaalPaswoord){
        return paswoord.equals(herhaalPaswoord);

    }
}
