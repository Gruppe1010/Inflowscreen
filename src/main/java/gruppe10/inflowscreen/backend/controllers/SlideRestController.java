package gruppe10.inflowscreen.backend.controllers;

import gruppe10.inflowscreen.backend.models.dto.CreateOrUpdateSlideDTO;
import gruppe10.inflowscreen.backend.models.dto.IndexSlideDTO;
import gruppe10.inflowscreen.backend.models.entities.Slide;
import gruppe10.inflowscreen.backend.services.SlideService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Set;


@RestController
@CrossOrigin(value = "*")
@RequestMapping("/api")
public class SlideRestController
{
    @Autowired
    SlideService slideService;

    // inflowscreen.dk/api/slides/{orgId}
    @GetMapping("/slides/{orgId}")
    public ResponseEntity<ArrayList<IndexSlideDTO>> index(@PathVariable int orgId){
    
        System.out.println("Helooooo");
    
        try {
            ArrayList<IndexSlideDTO> slides = slideService.findAllSlides(orgId);
    
            System.out.println("HElloo2");
            
            if(slides == null){
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            }
        
            return new ResponseEntity<>(slides, HttpStatus.OK);
            
        }catch (Exception e){
            System.out.println("HElloo3");
    
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        
    }
    
    @DeleteMapping("/slide/{slideId}")
    public ResponseEntity<HttpStatus> deleteSlide(@PathVariable int slideId){
        
        try{
            
            return new ResponseEntity<>(slideService.deleteSlideById(slideId));
            
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        
    }
    
    @GetMapping("/slide/{slideId}")
    public ResponseEntity<CreateOrUpdateSlideDTO> editSlide(@PathVariable int slideId){
    
        CreateOrUpdateSlideDTO slide = slideService.findById(slideId);
        
        if(slide != null){
            return new ResponseEntity<>(slide, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    
    }
    

    /*
    * For at Spring kan deserialisere JSON vi får fra front, SKAL vi have getters og setters (OG de SKAL have de rigtige navne)
    * */
    @PostMapping("/saveSlide")
    public ResponseEntity<HttpStatus> saveSlide(@RequestBody CreateOrUpdateSlideDTO slideDTO, Principal principal) {
        
        HttpStatus httpStatus = slideService.createNewSlide(slideDTO, principal);
        
        return new ResponseEntity<>(httpStatus);
    }
    
    
    
    @GetMapping("/slideshow/{orgId}")
    public ResponseEntity<Set<CreateOrUpdateSlideDTO>> slideShow(@PathVariable int orgId){
        
        try {
            Set<CreateOrUpdateSlideDTO> slides = slideService.findAllActiveSlides(orgId);
            //slides.forEach(System.out::println);
            
            if(slides == null){
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            }
            
            return new ResponseEntity<>(slides, HttpStatus.OK);
            
            
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }
    
    
    
    
    
    
    
}