package be.vdab.cultuurhuis.dto;



import be.vdab.cultuurhuis.domain.Voorstelling;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Comparator;
import java.util.Objects;

@Data
public class VoorstellingDto implements Comparable<VoorstellingDto>{

    private long id;

    private String titel;

    private String uitvoerders;

    private LocalDateTime datum;
    private String dag;
    private LocalTime tijdstip;
    private String genre;

    private BigDecimal prijs;

    private int vrijeplaatsen;

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

    public String getDag() {
        return dag;
    }

    public LocalTime getTijdstip() {
        return tijdstip;
    }

    public String getGenre() {
        return genre;
    }

    public BigDecimal getPrijs() {
        return prijs;
    }

    public int getVrijeplaatsen() {
        return vrijeplaatsen;
    }

    public VoorstellingDto(Voorstelling entity) {
        this.id = entity.getId();
        this.titel = entity.getTitel();
        this.uitvoerders = entity.getUitvoerders();
        this.datum =entity.getDatum();
        this.dag = entity.getStringDatum();
        this.tijdstip = entity.getTijdstip();
        this.genre = entity.getGenre().getNaam();
        this.prijs = entity.getPrijs();
        this.vrijeplaatsen = entity.getVrijeplaatsen();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VoorstellingDto that = (VoorstellingDto) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }


    @Override
    public int compareTo(VoorstellingDto o) {
      /*  if (this.equals(o))
            return 0;
        else return id == o.getId() ? -1 : (int) (id - o.getId());*/
        return Comparator.comparing(VoorstellingDto::getDatum)
                .thenComparing(VoorstellingDto::getId)
                .compare(this, o);
    }
}
