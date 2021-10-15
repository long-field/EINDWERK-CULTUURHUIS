package be.vdab.cultuurhuis.services;

import be.vdab.cultuurhuis.domain.Genre;

import java.util.List;
import java.util.Optional;

public interface GenreService {
    List<Genre> findAll();
    Optional<Genre> findById(long id);
}
