package gruppe10.inflowscreen.backend.services;

import gruppe10.inflowscreen.backend.models.dto.CreateOrUpdateSlideDTO;
import gruppe10.inflowscreen.backend.models.entities.Organisation;
import gruppe10.inflowscreen.backend.models.entities.Slide;
import gruppe10.inflowscreen.backend.models.entities.SlideImage;
import gruppe10.inflowscreen.backend.repositories.OrganisationRepository;
import gruppe10.inflowscreen.backend.repositories.SlideImageRepository;
import gruppe10.inflowscreen.backend.repositories.SlideRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Service
public class SlideService {
    
    @Autowired
    SlideRepository slideRepository;
    @Autowired
    OrganisationRepository organisationRepository;
    @Autowired
    SlideImageRepository slideImageRepository;
    
    
    public HttpStatus createNewSlide(CreateOrUpdateSlideDTO slideDTO, Principal principal){
        
        // vi henter organisationen som skal forbindes til slidet
        Organisation organisation = organisationRepository.findByEmail(principal.getName());
        
        boolean titleIsAvailable = isTitleAvailable(organisation, slideDTO.getTitle());
        
        if(titleIsAvailable)
        {
            // if der IKKE allerede er nogle slides på orgen
            if(organisation.getSlides() == null)
            {
                // oprettes nyt set
                organisation.setSlides(new HashSet<>());
            }
            
            Slide slide = slideDTO.convertToSlide();
    
            // tilføj slide til org
            organisation.getSlides().add(slide);
            
            // opdaterer org i db og lægger dermed slide ned
            organisationRepository.save(organisation);
    
            // vi henter det nyoprettede slide OP fra db igen
            Optional<Slide> newlyPersistedSlide = slideRepository.findByOrganisationAndTitle(organisation,
                    slide.getTitle());
    
            // hvis slidet er blevet oprettet korrekt
            if(newlyPersistedSlide.isPresent())
            {
                // læg slideImages på givne slide i db
                // vi opretter et Set af de slideImages vi skal lægge ned i db
                Set<SlideImage> slideImages = slide.getSlideImages();
    
                // hvis der ER slideImages på slide
                if(slideImages != null)
                {
                    // sætter vi slide-attribut til ophentet slide på hver af SlideImage-obj
                    slideImages.forEach(slideImage -> slideImage.setSlide(newlyPersistedSlide.get()));
    
                    // gemmer vi alle slideImages ned
                    slideImageRepository.saveAll(slideImages);
                }
                return HttpStatus.CREATED;
            }
            else
            { // hvis slidet IKKE er present med ny titel, er den ikke blevet gemt i db
                return HttpStatus.CONFLICT;
            }
        }
        return HttpStatus.CONFLICT; // hvis titel er optaget på orgen
    }
    
    // TODO tjek at rep-metode virker
    public boolean isTitleAvailable(Organisation organisation, String title){
        // vi ser om der er et slide med samme titel, som de prøver at oprette
        Optional<Slide> optionalSlide = slideRepository.findByOrganisationAndTitle(organisation, title);
        
        // hvis den IKKE har fundet et slide, bliver dette evalueret til true == titlen er available
        return optionalSlide.isEmpty();
    }
}