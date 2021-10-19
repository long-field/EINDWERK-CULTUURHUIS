package be.vdab.cultuurhuis.services;

import be.vdab.cultuurhuis.domain.Voorstelling;
import be.vdab.cultuurhuis.dto.VoorstellingDto;
import be.vdab.cultuurhuis.repositories.VoorstellingRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;

@Service
@Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED)
public class DefaultVoorstellingService implements VoorstellingService{
    private final VoorstellingRepository voorstellingRepository;
    DefaultVoorstellingService(VoorstellingRepository voorstellingRepository) {
        this.voorstellingRepository = voorstellingRepository;
    }
    //Voor Mvc wordt niet gebruikt Datum where clause NIET OK
    @Override
    @Transactional(readOnly = true)
    public List<Voorstelling> findAll() {

        return voorstellingRepository.findAll(Sort.by(Sort.Direction.ASC,"datum"));
    }
    //Voor MVC
    /*
    @Override
    public List<Voorstelling> findByGenreId(long id) {
        return voorstellingRepository.findByGenreIdAndDatumGreaterThanEqualOrderByDatumAsc(id, LocalDateTime.now());
    }
    */

    //Voor MVC OK
    @Override
    @Transactional(readOnly = true)
    public Optional<Voorstelling> findById(long id) {
        return voorstellingRepository.findById(id);
    }

    //OK
    @Override
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED)
    public void update(Voorstelling voorstelling) {
        voorstellingRepository.save(voorstelling);
    }

    //Methods voor REST met DTO objecten hieronder
    @Override
    @Transactional(readOnly = true)
    public VoorstellingDto findDtoById(long id) {
        Voorstelling voorstelling = voorstellingRepository.findById(id).get();
        VoorstellingDto dto = new VoorstellingDto(voorstelling);
        return dto;
    }

    //Niet gebruikt
    @Override
    @Transactional(readOnly = true)
    public Set<VoorstellingDto> findAllDto() {
        Set<VoorstellingDto> dtoLijst= new TreeSet<VoorstellingDto>();
        List<Voorstelling> voorstellingen = voorstellingRepository.findAll();
        voorstellingen.forEach(voorstelling -> {dtoLijst.add(new VoorstellingDto(voorstelling));});
        return dtoLijst;
    }
    // Datum,Ordening, paging ok
    @Override
    @Transactional(readOnly = true)
    public Set<VoorstellingDto> findAllDtoPaged(int page,int total) {
        Set<Voorstelling> pagina= voorstellingRepository.findAllByDatumGreaterThanEqualOrderByDatumAsc(LocalDateTime.now(),PageRequest.of(page,total)).toSet();
        Set<VoorstellingDto> dtoLijst= new TreeSet<VoorstellingDto>();
        pagina.forEach(voorstelling -> {dtoLijst.add(new VoorstellingDto(voorstelling));});
        return dtoLijst;
    }
    // Datum ok
    @Override
    @Transactional(readOnly = true)
    public long findMax() {
        var max =voorstellingRepository.countByDatumGreaterThanEqual(LocalDateTime.now());
        return max;
    }

    @Override
    @Transactional(readOnly = true)
    public long findMaxMetGenre(long id) {
        var max =voorstellingRepository.countByGenreIdAndDatumGreaterThanEqualOrderByDatumAsc(id,LocalDateTime.now());
        return max;
    }
    //Genre datum ordening
    @Override
    public Set<VoorstellingDto> findByGenreIdPaged(long id,int page,int total) {
        Set<Voorstelling> pagina=voorstellingRepository.findByGenreIdAndDatumGreaterThanEqualOrderByDatumAsc(id, LocalDateTime.now(),PageRequest.of(page,total)).toSet();
        Set<VoorstellingDto> dtoLijst= new TreeSet<VoorstellingDto>();
        pagina.forEach(voorstelling -> {dtoLijst.add(new VoorstellingDto(voorstelling));});
        return dtoLijst;
    }

}
