package be.vdab.cultuurhuis.controllers;

import be.vdab.cultuurhuis.domain.Klant;
import be.vdab.cultuurhuis.domain.Reservatie;
import be.vdab.cultuurhuis.domain.Voorstelling;
import be.vdab.cultuurhuis.services.KlantService;
import be.vdab.cultuurhuis.services.ReservatieService;
import be.vdab.cultuurhuis.services.VoorstellingService;
import be.vdab.cultuurhuis.sessions.Mandje;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.LinkedHashMap;
import java.util.Map;

@Controller
@RequestMapping("reservatie")
public class ReservatieController {
    private final ReservatieService reservatieService;
    private final KlantService klantService;
    private final VoorstellingService voorstellingService;
    private final Mandje mandje;

    public ReservatieController(ReservatieService reservatieService, VoorstellingService voorstellingService,
                                KlantService klantService, Mandje mandje) {
        this.reservatieService = reservatieService;
        this.voorstellingService = voorstellingService;
        this.klantService = klantService;
        this.mandje = mandje;
    }

    @GetMapping("toevoegen")
    ModelAndView toevoegen() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        ModelAndView modelAndView = new ModelAndView("bevestig");
        if (username != null && !username.isEmpty()) {
            klantService.findByGebruikersnaam(username).ifPresent(klant -> {
                modelAndView.addObject(klant);
            });
        }
        return modelAndView;
    }

    @PostMapping("bevestigen")
    ModelAndView create() {
        if (mandje.isLeeg()) return new ModelAndView("redirect:/");
        Map<Voorstelling, Integer> nietGeslaagd= new LinkedHashMap<>();
        Map<Voorstelling, Integer> geslaagd = new LinkedHashMap<>();
        mandje.getReservaties().forEach((id, aantal) -> {
            Voorstelling voorstelling = voorstellingService.findById(id).get();
            if (voorstelling.getVrijeplaatsen()>=aantal) {
                voorstelling.setVrijeplaatsen(voorstelling.getVrijeplaatsen() - aantal);
                voorstellingService.update(voorstelling);
                geslaagd.put(voorstelling, aantal);
                reservatieService.create(new Reservatie(klantService.findByGebruikersnaam(SecurityContextHolder.getContext().getAuthentication().getName()).get(), voorstelling, aantal));
            }
            else {
               nietGeslaagd.put(voorstelling, voorstelling.getVrijeplaatsen());
            }
        });
        mandje.verwijderMandje();
        boolean geenFouten = nietGeslaagd.isEmpty();

        ModelAndView modelAndView = new ModelAndView("reservatie")
                .addObject("geenfouten", geenFouten)
                .addObject("geslaagd", geslaagd)
                .addObject("nietgeslaagd", nietGeslaagd);
        return modelAndView;
    }

}
