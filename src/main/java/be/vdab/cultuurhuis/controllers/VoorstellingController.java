package be.vdab.cultuurhuis.controllers;

import be.vdab.cultuurhuis.domain.Voorstelling;
import be.vdab.cultuurhuis.forms.AantalPlaatsenForm;
import be.vdab.cultuurhuis.services.GenreService;
import be.vdab.cultuurhuis.services.VoorstellingService;
import be.vdab.cultuurhuis.sessions.Mandje;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Map;

@Controller
@RequestMapping("voorstellingen")
public class VoorstellingController {

    private final VoorstellingService voorstellingService;
    private final Mandje mandje;

    public VoorstellingController(VoorstellingService voorstellingService, Mandje mandje) {

        this.voorstellingService = voorstellingService;
        this.mandje = mandje;
    }

    //Paging via redirect (foutief)

    /*@GetMapping("/programmatie/{id}")
    public ModelAndView programmatie(@PathVariable long id){
        var page = voorstellingService.findAll(PageRequest.of((int) id,10));
        var list =page.getContent();
        return new ModelAndView("programmatie").addObject("voorstellingen", list);
    }*/

    @GetMapping("{id}")
    public ModelAndView voorstelling(@PathVariable long id) {
        ModelAndView modelAndView = new ModelAndView("voorstelling");
        voorstellingService.findById(id).ifPresent(voorstelling -> {
            modelAndView.addObject("voorstelling", voorstelling);
        });
        AantalPlaatsenForm form = new AantalPlaatsenForm((int) mandje.getAantalReservatiesVoorVoorstelling(id));
        modelAndView.addObject(form);
        return modelAndView;
    }

    @PostMapping("/reserveren/{id}")
    public ModelAndView reserveren(@PathVariable long id, @Valid AantalPlaatsenForm form, Errors errors) {
        if (errors.hasErrors()) {
            ModelAndView modelAndView = new ModelAndView("voorstelling");
            voorstellingService.findById(id).ifPresent(voorstelling -> modelAndView.addObject(voorstelling));
            modelAndView.addObject(errors);
            return modelAndView;
        } else {
            ModelAndView error = new ModelAndView("voorstelling");
            voorstellingService.findById(id).ifPresent(voorstelling -> error.addObject(voorstelling));
            Voorstelling voorstelling = voorstellingService.findById(id).get();
            if (voorstelling.getVrijeplaatsen() == 0) {
                return error.addObject("error", "Uitverkocht");
            } else if (voorstelling.getVrijeplaatsen() < form.getAantal()) {
                return error.addObject("error", "Niet voldoende tickets beschikbaar");
            }
            mandje.voegToe(id, form.getAantal());
            return new ModelAndView("redirect:/mandje");

        }


    }
}