package gruppe10.inflowscreen.backend.repositories;

import gruppe10.inflowscreen.backend.models.entities.Organisation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface OrganisationRepository extends JpaRepository<Organisation, Integer> {
    
    
    //     @Query("SELECT slide FROM Slide slide
    //     @Query("SELECT movie FROM Movie movie JOIN movie.uniqueTimeSlots u WHERE u LIKE :likeString")

    //
    //     WHERE slide.title = ?1")
    
    @Query("SELECT organisation FROM Organisation organisation JOIN organisation.accounts a where a.email = ?1")
    Organisation findByEmail(String email);


    
  
}
