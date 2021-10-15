package be.vdab.cultuurhuis.repositories;

import be.vdab.cultuurhuis.domain.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.TreeSet;

public interface GenreRepository extends JpaRepository<Genre, Long> {

}
