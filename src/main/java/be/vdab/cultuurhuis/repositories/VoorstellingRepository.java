package be.vdab.cultuurhuis.repositories;

import be.vdab.cultuurhuis.domain.Genre;
import be.vdab.cultuurhuis.domain.Voorstelling;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VoorstellingRepository extends JpaRepository<Voorstelling, Long> {
    @Override
    Page<Voorstelling> findAll(Pageable pageable);
    List<Voorstelling> findByGenreId(long id);

    List<Voorstelling> findByGenreIdOrderByDatumAsc(long id);

}
