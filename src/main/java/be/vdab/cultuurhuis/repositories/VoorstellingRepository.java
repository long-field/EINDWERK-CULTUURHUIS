package be.vdab.cultuurhuis.repositories;

import be.vdab.cultuurhuis.domain.Genre;
import be.vdab.cultuurhuis.domain.Voorstelling;
import be.vdab.cultuurhuis.dto.VoorstellingDto;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public interface VoorstellingRepository extends JpaRepository<Voorstelling, Long> {

    //OK
    Page<Voorstelling> findAllByDatumGreaterThanEqualOrderByDatumAsc( LocalDateTime dateTime,Pageable pageable);
    //OK
    long  countByDatumGreaterThanEqual(LocalDateTime dateTime);
    //Voor Mvc
    //List<Voorstelling> findByGenreIdAndDatumGreaterThanEqualOrderByDatumAsc(long id, LocalDateTime datetime);
    //VoorRest
    long countByGenreIdAndDatumGreaterThanEqualOrderByDatumAsc(long id, LocalDateTime datetime);

    Page<Voorstelling> findByGenreIdAndDatumGreaterThanEqualOrderByDatumAsc(long id, LocalDateTime datetime,Pageable pageable);
}
