package gruppe10.inflowscreen.backend.controllers;

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
    // hejfskj
    
    @PostMapping("/saveSlide")
    public ResponseEntity<HttpStatus> saveSlide(@RequestBody Slide slide, Principal principal)
    {
        
        // vi ser om der er et slide med samme titel, som de prøver at oprette
        Optional<Slide> optionalSlide = slideRepository.findByTitle(slide.getTitle());
        
        // hvis der IKKE er slide med samme titel
        if(optionalSlide.isEmpty())
        {
            // vi henter organisationen som skal forbindes til slidet
            Organisation organisation = organisationRepository.findByEmail(principal.getName());
            
            // hvis der endnu ikke er noget org på slidet - lav nyt Set
            if(slide.getOrganisations() == null) {
               slide.setOrganisations(new HashSet<>());
            }
            // tilføj org til slide
            slide.getOrganisations().add(organisationRepository.findByEmail(principal.getName()));
            
            slideRepository.save(slide);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        
        return new ResponseEntity<>(HttpStatus.CONFLICT);
    }
}