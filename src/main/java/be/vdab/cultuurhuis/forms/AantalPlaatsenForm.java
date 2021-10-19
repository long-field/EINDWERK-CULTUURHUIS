package be.vdab.cultuurhuis.forms;

import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class AantalPlaatsenForm {
    @NotNull()
    @Positive()

    private Integer aantal;

    public AantalPlaatsenForm(Integer aantal) {
        this.aantal = aantal;
    }

    public Integer getAantal() {
        return aantal;
    }
}
