package be.vdab.cultuurhuis.services;

import be.vdab.cultuurhuis.domain.Klant;
import be.vdab.cultuurhuis.repositories.KlantRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED)
public class DefaultKlantService implements KlantService{
    private final KlantRepository klantRepository;

    public DefaultKlantService(KlantRepository klantRepository) {
        this.klantRepository = klantRepository;
    }

    @Override
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED)
    public void create(Klant klant) {
        klantRepository.save(klant);
    }

    @Override
    public Optional<Klant> findById(long id) {
        return klantRepository.findById(id);
    }

    @Override
    public Optional<Klant> findByGebruikersnaam(String gebruikersnaam) {
        return klantRepository.findByGebruikersnaam(gebruikersnaam);
    }

}
