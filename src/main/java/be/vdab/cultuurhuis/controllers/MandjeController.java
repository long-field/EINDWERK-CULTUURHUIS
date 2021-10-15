package be.vdab.cultuurhuis.controllers;

import be.vdab.cultuurhuis.domain.Voorstelling;
import be.vdab.cultuurhuis.services.GenreService;
import be.vdab.cultuurhuis.services.VoorstellingService;
import be.vdab.cultuurhuis.sessions.Mandje;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
@RequestMapping("/mandje")
public class MandjeController {
    private final Mandje mandje;
    private final VoorstellingService voorstellingService;
    private Map<Voorstelling, Integer> reservaties = new HashMap<>();

    public MandjeController(Mandje mandje, VoorstellingService voorstellingService) {
        this.mandje = mandje;
        this.voorstellingService = voorstellingService;
    }

    @NumberFormat(pattern = "0.00")
    private BigDecimal getTotaal() {
        BigDecimal prijs = BigDecimal.ZERO;
        for (Map.Entry<Voorstelling, Integer> entry : reservaties.entrySet()) {
            Voorstelling key = entry.getKey();
            Integer value = entry.getValue();
            BigDecimal prijsPerSoort = key.getPrijs().multiply(BigDecimal.valueOf(value));
            prijs = prijs.add(prijsPerSoort);
        }
        return prijs;
    }

    @GetMapping
    public ModelAndView mandje() {
        ModelAndView modelAndView = new ModelAndView("mandje");
        if (mandje.isLeeg()) return modelAndView;
        reservaties.clear();
        mandje.getReservaties().forEach((id, aantal) -> reservaties.put(voorstellingService.findById(id).get(), aantal));
        modelAndView.addObject("reservaties", reservaties);
        modelAndView.addObject("totaal", getTotaal());
        return modelAndView;
    }

    @PostMapping("verwijderen")
    String verwijderen(@RequestParam("verwijder") Optional<List<String>> teVerwijderen, RedirectAttributes redirect) {
        if (teVerwijderen.isPresent()) {
           teVerwijderen.get().stream().forEach(id ->mandje.verwijderReservatie(Long.parseLong(id)));
        }
        if(mandje.isLeeg()){
            return "redirect:/";
        }
        return "redirect:/mandje";
    }
}
