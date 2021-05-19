package gruppe10.inflowscreen.backend.repositories;

import gruppe10.inflowscreen.backend.models.entities.Organisation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface OrganisationRepository extends JpaRepository<Organisation, Integer> {
    
    
    @Query()
    Organisation findByEmail(String email);


}
