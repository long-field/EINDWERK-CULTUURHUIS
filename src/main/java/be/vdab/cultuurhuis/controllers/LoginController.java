package be.vdab.cultuurhuis.controllers;

import be.vdab.cultuurhuis.services.GenreService;
import be.vdab.cultuurhuis.sessions.Mandje;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/login")
public class LoginController {


    @GetMapping
    public ModelAndView login() {
        ModelAndView modelAndView = new ModelAndView("login");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication == null || authentication instanceof AnonymousAuthenticationToken)) {
            ModelAndView aangemeld =new ModelAndView("redirect/");
            return aangemeld;
        }
        return modelAndView;
    }

}
