package be.vdab.cultuurhuis.controllers;

import be.vdab.cultuurhuis.domain.Voorstelling;
import be.vdab.cultuurhuis.forms.AantalPlaatsenForm;
import be.vdab.cultuurhuis.services.GenreService;
import be.vdab.cultuurhuis.services.VoorstellingService;
import be.vdab.cultuurhuis.sessions.Mandje;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
@RequestMapping("voorstellingen")
public class VoorstellingController {

    private final VoorstellingService voorstellingService;
    private final Mandje mandje;

    public VoorstellingController( VoorstellingService voorstellingService, Mandje mandje) {

        this.voorstellingService = voorstellingService;
        this.mandje = mandje;
    }

    @GetMapping("{id}")
    public ModelAndView voorstelling(@PathVariable long id) {
        ModelAndView modelAndView = new ModelAndView("voorstelling");
        voorstellingService.findById(id).ifPresent(voorstelling -> {modelAndView.addObject("voorstelling",voorstelling);});
        AantalPlaatsenForm form = new AantalPlaatsenForm((int) mandje.getAantalReservatiesVoorVoorstelling(id));
        modelAndView.addObject(form);
        return modelAndView;
    }
    @PostMapping("{id}/reserveren")
    public ModelAndView reserveren(@PathVariable long id, @Valid AantalPlaatsenForm form, Errors errors){
        if (errors.hasErrors()) {
            ModelAndView modelAndView = new ModelAndView("voorstelling");
            voorstellingService.findById(id).ifPresent(voorstelling -> modelAndView.addObject("voorstelling",voorstelling));
            return modelAndView;
        }
        else {
            ModelAndView modelAndViewError = new ModelAndView("voorstelling");
            voorstellingService.findById(id).ifPresent(voorstellingObj -> modelAndViewError.addObject(voorstellingObj));
            Voorstelling voorstelling = voorstellingService.findById(id).get();
            mandje.voegToe(id, form.getAantal());
            return new ModelAndView("redirect:/mandje");
        }
    }


}
