package be.vdab.cultuurhuis.controllers;

import be.vdab.cultuurhuis.services.GenreService;
import be.vdab.cultuurhuis.services.VoorstellingService;
import be.vdab.cultuurhuis.sessions.Mandje;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
public class IndexController {
    private final VoorstellingService voorstellingService;

    public IndexController(VoorstellingService voorstellingService ) {
        this.voorstellingService = voorstellingService;
    }

    @GetMapping
    public ModelAndView voorstellingen() {
        ModelAndView modelAndView = new ModelAndView("index", "voorstellingen", voorstellingService.findAll());
        return modelAndView;
    }
}
