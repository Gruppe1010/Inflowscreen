package gruppe10.inflowscreen.backend.controllers;

import gruppe10.inflowscreen.backend.models.dto.CreateOrUpdateSlideDTO;
import gruppe10.inflowscreen.backend.models.entities.Slide;
import gruppe10.inflowscreen.backend.services.SlideService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Set;


@RestController
@CrossOrigin(value = "*")
public class SlideRestController
{
    @Autowired
    SlideService slideService;


    @GetMapping("/slideshow/{orgId}")
    public ResponseEntity<Set<Slide>> slideShow(@PathVariable int orgId ){

        Set<Slide> slides = SlideService.findAllSlides(orgId);

        return new ResponseEntity<>(slides, HttpStatus.OK);
    }
    

    /*
    * For at Spring kan deserialisere JSON vi får fra front, SKAL vi have getters og setters (OG de SKAL have de rigtige navne)
    * */
    @PostMapping("/saveSlide")
    public ResponseEntity<HttpStatus> saveSlide(@RequestBody CreateOrUpdateSlideDTO slideDTO, Principal principal) {
    
        System.out.println(slideDTO);
        
        HttpStatus httpStatus = slideService.createNewSlide(slideDTO, principal);
        
        return new ResponseEntity<>(httpStatus);
    }
    
    
    
    
    
    
}