package be.vdab.cultuurhuis.controllers;

import be.vdab.cultuurhuis.services.GenreService;
import be.vdab.cultuurhuis.sessions.Mandje;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
@ControllerAdvice
public class MyControllerAdvice {
    private final Mandje mandje;
    private final GenreService genreService;
    MyControllerAdvice(Mandje mandje, GenreService genreService) {
        this.mandje = mandje;
        this.genreService = genreService;
    }
    @ModelAttribute("mandje")
    void mandjeToevoegenAanModel(Model model) {
        model.addAttribute(mandje);
        model.addAttribute("genres",genreService.findAll());
    }
}
