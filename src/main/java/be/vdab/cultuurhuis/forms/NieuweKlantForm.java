package be.vdab.cultuurhuis.forms;

public class NieuweKlantForm {
    private String voornaam;
    private String familienaam;
    private String straat;
    private String huisnr;
    private String postcode;
    private String gemeente;
    private String gebruikersnaam;
    private String paswoord;
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
