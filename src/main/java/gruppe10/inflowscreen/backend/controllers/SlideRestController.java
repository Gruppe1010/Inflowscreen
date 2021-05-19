package gruppe10.inflowscreen.backend.controllers;

import gruppe10.inflowscreen.backend.models.entities.Slide;
import gruppe10.inflowscreen.backend.repositories.SlideRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpResponse;
import java.security.Principal;
import java.util.Optional;

@RestController
@CrossOrigin(value = "*")
public class SlideRestController {
    
    
    @Autowired
    SlideRepository slideRepository;
    
    
    
    @PostMapping("saveSlide")
    public ResponseEntity<HttpStatus> saveSlide(@RequestBody Slide slide, Principal principal){
       
        Optional<Slide> optionalSlide = slideRepository.findByTitle(slide.getTitle());
        
        if(optionalSlide.isEmpty()){
            
            principal
            
            slideRepository.save(slide);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        
        return new ResponseEntity<>(HttpStatus.CONFLICT);
    }
    
    
    
    
    
    

}
