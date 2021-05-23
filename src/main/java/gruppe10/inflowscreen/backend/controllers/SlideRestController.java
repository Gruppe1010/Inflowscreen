package gruppe10.inflowscreen.backend.controllers;

import gruppe10.inflowscreen.backend.models.dto.CreateOrUpdateSlideDTO;
import gruppe10.inflowscreen.backend.models.dto.SlideImageDTO;
import gruppe10.inflowscreen.backend.models.entities.Organisation;
import gruppe10.inflowscreen.backend.models.entities.Slide;
import gruppe10.inflowscreen.backend.repositories.OrganisationRepository;
import gruppe10.inflowscreen.backend.repositories.SlideRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.security.Principal;
import java.util.HashSet;
import java.util.Optional;

@RestController
@CrossOrigin(value = "*")
public class SlideRestController
{
    
    
    @Autowired
    SlideRepository slideRepository;
    @Autowired
    OrganisationRepository organisationRepository; //hfh
    // hejfs

    /*
    * For at Spring kan deserialisere JSON vi får fra front, SKAL vi have getters og setters (OG de SKAL have de rigtige navne)
    * */
    @PostMapping("/saveSlide")
    public ResponseEntity<HttpStatus> saveSlide(@RequestBody CreateOrUpdateSlideDTO slideDTO, Principal principal)
    {
        
        // ! VI SKAL LIGE SØRGE FOR AT DEN GEMMER IMAGES RIGTIGT NEEEED!!!!
        // Måske: Læg images ned hver for sig først, og så via org som vi gør nu

        System.out.println("HEEEEY: " + slideDTO);

        // vi ser om der er et slide med samme titel, som de prøver at oprette
        Optional<Slide> optionalSlide = slideRepository.findByTitle(slideDTO.getTitle());

        // hvis der IKKE er slide med samme titel
        if(optionalSlide.isEmpty())
        {
            // vi henter organisationen som skal forbindes til slidet
            Organisation organisation = organisationRepository.findByEmail(principal.getName());
            
            // if der IKKE allerede er nogle slides på orgen
            if(organisation.getSlides() == null){
                // oprettes nyt set
                organisation.setSlides(new HashSet<>());
            }

            Slide slide = slideDTO.convertToSlide();
    
            // tilføj slide til org
            organisation.getSlides().add(slide);
            
            organisationRepository.save(organisation);
            
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.CONFLICT);
    }
}