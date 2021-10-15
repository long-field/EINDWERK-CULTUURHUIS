package be.vdab.cultuurhuis.services;

import be.vdab.cultuurhuis.domain.Voorstelling;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface VoorstellingService {
    Page<Voorstelling> findAll(Pageable pageable);
    Optional<Voorstelling> findById(long id);
    void update(Voorstelling voorstelling);
    List<Voorstelling> findAll();
    List<Voorstelling> findByGenreId(long id);
}
