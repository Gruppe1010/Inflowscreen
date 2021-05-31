package gruppe10.inflowscreen.backend.repositories;

import gruppe10.inflowscreen.backend.models.dto.IndexSlideDTO;
import gruppe10.inflowscreen.backend.models.entities.Organisation;
import gruppe10.inflowscreen.backend.models.entities.Slide;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface SlideRepository extends JpaRepository<Slide, Integer> {
    
    @Query("SELECT slide FROM Slide slide WHERE slide.title = ?1")
    Optional<Slide> findByTitle(String title);
    
    @Query("SELECT slide FROM Slide slide JOIN slide.organisations org WHERE org = ?1 and slide.title = ?2")
    Optional<Slide> findByOrganisationAndTitle(Organisation org, String title);
    
    @Query("SELECT slide FROM Slide slide JOIN slide.organisations org WHERE org.id = ?1 and slide.isActive = true")
    Optional<Set<Slide>> findByOrganisation(int orgId);
    
    @Query("SELECT slide FROM Slide slide JOIN slide.organisations org WHERE org.id = ?1")
    Optional<List<Slide>> findAllSlides(int orgId);
    
    @Transactional
    @Modifying
    @Query("DELETE FROM Slide slide WHERE slide.id = ?1")
    void deleteSlideById(int slideId);
    
    
}