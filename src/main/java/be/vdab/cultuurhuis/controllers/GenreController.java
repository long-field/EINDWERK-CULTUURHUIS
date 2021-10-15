package be.vdab.cultuurhuis.controllers;

import be.vdab.cultuurhuis.services.GenreService;
import be.vdab.cultuurhuis.services.VoorstellingService;
import be.vdab.cultuurhuis.sessions.Mandje;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/genre/{id}")
public class GenreController {
    private final GenreService genreService;
    private final VoorstellingService voorstellingService;

    public GenreController(GenreService genreService, VoorstellingService voorstellingService) {
        this.genreService = genreService;
        this.voorstellingService = voorstellingService;
    }

    @GetMapping
    public ModelAndView genre(@PathVariable long id) {
        ModelAndView modelAndView = new ModelAndView("genre");
        modelAndView.addObject("voorstellingen",voorstellingService.findByGenreId(id));
        genreService.findById(id).ifPresent(genre ->{modelAndView.addObject("genrenaam" ,genre.getNaam());});
        return modelAndView;
    }
}
