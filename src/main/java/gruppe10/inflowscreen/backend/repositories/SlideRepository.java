package gruppe10.inflowscreen.backend.repositories;

import gruppe10.inflowscreen.backend.models.entities.Organisation;
import gruppe10.inflowscreen.backend.models.entities.Slide;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface SlideRepository extends JpaRepository<Slide, Integer> {
    
    @Query("SELECT slide FROM Slide slide WHERE slide.title = ?1")
    Optional<Slide> findByTitle(String title);
    
    // TODO tjek at det virker
    @Query("SELECT slide FROM Slide slide JOIN slide.organisations org WHERE org = ?1 and slide.title = ?2")
    Optional<Slide> findByOrganisationAndTitle(Organisation org, String title);
    
    
}