package be.vdab.cultuurhuis.restcontrollers;

import be.vdab.cultuurhuis.domain.Voorstelling;
import be.vdab.cultuurhuis.dto.VoorstellingDto;
import be.vdab.cultuurhuis.services.VoorstellingService;
import io.swagger.v3.oas.annotations.Operation;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.hateoas.server.EntityLinks;
import org.springframework.hateoas.server.ExposesResourceFor;
import org.springframework.hateoas.server.TypedEntityLinks;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;

@RestController
@RequestMapping("voorstellingdata")
@ExposesResourceFor(VoorstellingDto.class)
public class VoorstellingRestController {
    private VoorstellingService service;

    public VoorstellingRestController(VoorstellingService service ) {
        this.service = service;

    }
    //OK
    @Operation(summary = "Een voorstelling zoeken op id")
    @GetMapping("{id}")
    VoorstellingDto get(@PathVariable long id) throws Exception {
        return service.findDtoById(id);
    }
    //Wordt niet gebruikt door client
    @Operation(summary = "Alle voorstellingen zoeken")
    @GetMapping()
    Set<VoorstellingDto> getVoorstellingenDto() throws Exception {
        return service.findAllDto();
    }
    //Ordening en where clausule voor datum ok!
    @Operation(summary = "Voorstellingen gepagineerd zoeken")
    @GetMapping("/paged")
    Set<VoorstellingDto> getVoorstellingenDtoPaged(@RequestParam int page,@RequestParam int total) throws Exception {
        return service.findAllDtoPaged(page,total);
    }
    //Where clausule datum inbegrepen
    @Operation(summary = "max")
    @GetMapping("/max")
    long max() throws Exception {

        return service.findMax();
    }
    //Where clausule datum en id inbegrepen
    @Operation(summary = "max aantal in genre")
    @GetMapping("/maxgenre/{id}")
    long max(@PathVariable long id) throws Exception {

        return service.findMaxMetGenre(id);
    }
    //Where clausule datum en id inbegrepen
    @Operation(summary = "Voorstellingen zoeken op genreid")
    @GetMapping("/paged/genre/{id}")
    Set<VoorstellingDto> getVoorstellingenDtoPerGenrePaged(@PathVariable long id,@RequestParam int page,@RequestParam int total) throws Exception {
            return service.findByGenreIdPaged(id,page,total);
        }
    }
