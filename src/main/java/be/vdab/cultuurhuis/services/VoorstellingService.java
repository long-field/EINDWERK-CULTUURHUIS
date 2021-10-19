package be.vdab.cultuurhuis.services;

import be.vdab.cultuurhuis.domain.Voorstelling;
import be.vdab.cultuurhuis.dto.VoorstellingDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface VoorstellingService {
    //VoorMvc
    Optional<Voorstelling> findById(long id);
    void update(Voorstelling voorstelling);
    List<Voorstelling> findAll();
     //List<Voorstelling> findByGenreId(long id);


    //Methods voor REST met DTO objecten hieronder
    Set<VoorstellingDto> findByGenreIdPaged(long id,int page,int total);
    VoorstellingDto findDtoById(long id);
    Set<VoorstellingDto> findAllDto();
    Set<VoorstellingDto> findAllDtoPaged(int page,int total);
    long findMax();
    long findMaxMetGenre(long id);
}
