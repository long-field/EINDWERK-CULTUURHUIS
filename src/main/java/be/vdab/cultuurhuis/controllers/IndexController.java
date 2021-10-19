package be.vdab.cultuurhuis.controllers;

import be.vdab.cultuurhuis.services.VoorstellingService;
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

    // mvc:
    @GetMapping
    public ModelAndView voorstellingen() {

       // ModelAndView modelAndView = new ModelAndView("index","voorstellingen",voorstellingService.findAll());   //Zonder Rest
        ModelAndView modelAndView = new ModelAndView("redirect:/scroll");  //Doorverwijzen naar scroll pagina met rest

        return modelAndView;
    }


    //indexpagina met paginering:infinite-scrolling met js op basis van restservice:
    @GetMapping("/scroll")
    public ModelAndView voorstellingenscroll() {

        ModelAndView modelAndView = new ModelAndView("scroll");
        modelAndView.addObject("max",voorstellingService.findMax());
        return modelAndView;
    }
}
