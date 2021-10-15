package be.vdab.cultuurhuis.repositories;

import be.vdab.cultuurhuis.domain.Genre;
import be.vdab.cultuurhuis.domain.Klant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface KlantRepository extends JpaRepository<Klant, Long> {
    Optional<Klant> findByGebruikersnaam(String gebruikersnaam);
}
