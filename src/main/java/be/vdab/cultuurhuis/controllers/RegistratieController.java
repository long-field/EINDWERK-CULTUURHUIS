package be.vdab.cultuurhuis.controllers;


import be.vdab.cultuurhuis.domain.Adres;
import be.vdab.cultuurhuis.domain.Klant;
import be.vdab.cultuurhuis.forms.NieuweKlantForm;
import be.vdab.cultuurhuis.services.KlantService;
import be.vdab.cultuurhuis.sessions.Mandje;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;


@Controller
@RequestMapping("registratie")
public class RegistratieController {
    private final KlantService klantService;
    private final Mandje mandje;

    public RegistratieController(KlantService klantService, Mandje mandje) {
        this.klantService = klantService;
        this.mandje = mandje;
    }

    @GetMapping()
    ModelAndView registratie(){
        ModelAndView modelAndView = new ModelAndView("registratie")
                .addObject(new NieuweKlantForm(null, null, null, null,
                        null, null, null, null, null));
        return modelAndView;
    }

    @PostMapping("/registratie")
    ModelAndView registratie(@Valid @Validated NieuweKlantForm form, Errors errors, HttpServletRequest request, RedirectAttributes redirect) {
        ModelAndView modelAndView = new ModelAndView("registratie");
        if (errors.hasErrors()) return modelAndView;
        if (klantService.findByGebruikersnaam(form.getGebruikersnaam()).isPresent()) {
            ModelAndView fout = new ModelAndView("registratie");
            return fout.addObject("foutboodschap", "Gebruikersnaam bestaat al");
        }

        Klant klant = new Klant(form.getVoornaam(), form.getFamilienaam(), new Adres(form.getStraat(), form.getHuisnr(), form.getPostcode(), form.getGemeente()),
                form.getGebruikersnaam(), new BCryptPasswordEncoder().encode(form.getPaswoord()));

        if (form.isPaswoordMatch(form.getPaswoord(), form.getHerhaalPaswoord())) {
            klantService.create(klant);
            try {
                request.login(form.getGebruikersnaam(), form.getPaswoord());
            } catch (ServletException e) {
                e.printStackTrace();
            }
            return new ModelAndView("redirect:/");
        }
        ModelAndView fout = new ModelAndView("registratie");
        return fout.addObject("foutboodschap", "Paswoord mismatch");
    }
}
