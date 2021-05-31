package gruppe10.inflowscreen.backend.services;

import gruppe10.inflowscreen.backend.models.dto.CreateOrUpdateSlideDTO;
import gruppe10.inflowscreen.backend.models.dto.IndexSlideDTO;
import gruppe10.inflowscreen.backend.models.entities.Organisation;
import gruppe10.inflowscreen.backend.models.entities.Slide;
import gruppe10.inflowscreen.backend.models.entities.SlideImage;
import gruppe10.inflowscreen.backend.models.entities.TextBox;
import gruppe10.inflowscreen.backend.repositories.OrganisationRepository;
import gruppe10.inflowscreen.backend.repositories.SlideImageRepository;
import gruppe10.inflowscreen.backend.repositories.SlideRepository;
import gruppe10.inflowscreen.backend.repositories.TextBoxRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.*;
import java.util.stream.Collectors;

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
    @Autowired
    TextBoxRepository textBoxRepository;
    
    
    public CreateOrUpdateSlideDTO
    
    public HttpStatus deleteSlideById(int slideId){
        
        slideRepository.deleteById(slideId);
        
        Optional<Slide> slide = slideRepository.findById(slideId);
        if(slide.isEmpty()){
            return  HttpStatus.OK;
        }
        return HttpStatus.BAD_REQUEST;
    }
    
    public HttpStatus createNewSlide(CreateOrUpdateSlideDTO slideDTO, Principal principal){
        
        // vi henter organisationen som skal forbindes til slidet
        Organisation organisation = organisationRepository.findByEmail(principal.getName());
        
        boolean titleIsAvailable = isTitleAvailable(organisation, slideDTO.getTitle());
        
        // hvis titlen IKKE er optaget
        if(titleIsAvailable) {
            Slide slide = slideDTO.convertToSlide();
            
            slide.setOrganisations(new HashSet<>());
            slide.getOrganisations().add(organisation);
            
            saveSlideToDb(organisation, slide);
            
            // vi henter det nyoprettede slide OP fra db igen
            Optional<Slide> optNewlyPersistedSlide = slideRepository.findByOrganisationAndTitle(organisation,
                    slide.getTitle());
    
            // hvis slidet er blevet oprettet korrekt
            if(optNewlyPersistedSlide.isPresent()){
                Slide newlyPersistedSlide = optNewlyPersistedSlide.get();
                
                // tilføj activeSlideOrder
                addActiveSlideOrder(newlyPersistedSlide);
                
                // læg slideImages i db
                saveSlideImagesToDb(slide, newlyPersistedSlide);
                
                // læg textBoxes i db
                saveTextBoxesToDb(slide, newlyPersistedSlide);
                
                return HttpStatus.CREATED;
            }
            // hvis slidet IKKE er present med ny titel, er den ikke blevet gemt i db
            else{ return HttpStatus.CONFLICT; }
        }
        return HttpStatus.CONFLICT; // hvis titel er optaget på orgen
    }
    public void addActiveSlideOrder(Slide slide){
        if(slide.isActive()){
            // hvis det er active, skal den tilføje en slideorder
            slide.setActiveSlideOrder(slide.getId());
            // og gemme slidet med den nye order
            slideRepository.save(slide);
        }
    }
    
    /**
     * Tjekker om organisationen allerede har et slide med titlen
     * @Param organisation - den organisation som prøver at oprette et slide
     * @Param title - titlen organisationen prøver på at give deres nye slide
     * @return boolean - om titlen er ledig
     * */
    public boolean isTitleAvailable(Organisation organisation, String title){
        // vi ser om der er et slide med samme titel, som de prøver at oprette
        Optional<Slide> optionalSlide = slideRepository.findByOrganisationAndTitle(organisation, title);
        
        // hvis den IKKE har fundet et slide, bliver dette evalueret til true == titlen er available
        return optionalSlide.isEmpty();
    }
    
    public void saveSlideToDb(Organisation organisation, Slide slide){
        // if der IKKE allerede er nogle slides på orgen
        if(organisation.getSlides() == null)
        {
            // oprettes nyt set
            organisation.setSlides(new HashSet<>());
        }
    
        // tilføj slide til org
        organisation.getSlides().add(slide);
    
        // opdaterer org i db og lægger dermed slide ned
        organisationRepository.save(organisation);
    }
    
    public void saveSlideImagesToDb(Slide slide, Slide newlyPersistedSlide){
        // vi opretter et Set af de slideImages vi skal lægge ned i db ud fra slided vi har fået i requestbody
        Set<SlideImage> slideImages = slide.getSlideImages();
    
        // hvis der ER slideImages på slide
        if(slideImages != null)
        {
            // sætter vi slide-attribut til ophentet slide på hver af SlideImage-obj
            slideImages.forEach(slideImage -> slideImage.setSlide(newlyPersistedSlide));
        
            // gemmer vi alle slideImages ned
            slideImageRepository.saveAll(slideImages);
        }
    }
    
    public void saveTextBoxesToDb(Slide slide, Slide newlyPersistedSlide){
        // vi opretter et Set af de TextBoxes vi skal lægge ned i db ud fra slided vi har fået i requestbody
        Set<TextBox> textBoxes = slide.getTextBoxes();
        
        // hvis der ER TextBoxes på slide
        if(textBoxes != null)
        {
            // sætter vi slide-attribut til ophentet slide på hver af TextBox-obj
            textBoxes.forEach(textBox -> textBox.setSlide(newlyPersistedSlide));
            
            // gemmer vi alle textBoxes ned
            textBoxRepository.saveAll(textBoxes);
        }
    }
    
    public Set<CreateOrUpdateSlideDTO> findAllActiveSlides(int orgId){
        
        Optional<Set<Slide>> optionalSlides = slideRepository.findByOrganisation(orgId);
        
        if(optionalSlides.isPresent()){
            Set<Slide> slides = optionalSlides.get();
    
            Set<CreateOrUpdateSlideDTO> slideDTOs =
                    slides.stream().map(Slide::convertToSlideDTO).collect(Collectors.toSet());
    
            return slideDTOs;
        }

        return null;
    }
    
    public ArrayList<IndexSlideDTO> findAllSlides(int orgId){
    
        Optional<List<Slide>> optionalSlides = slideRepository.findAllSlides(orgId);
    
        if(optionalSlides.isPresent()){
            ArrayList<IndexSlideDTO> slides =
                    (ArrayList<IndexSlideDTO>) optionalSlides.get().stream().map(Slide::convertToIndexSlideDTO).collect(Collectors.toList());
            
            Collections.sort(slides);
            return slides;
        }
        
        return null;
        
    }
    
  
    
    public void deleteSlideAttributeSlideSets(Slide slide){
        Set<SlideImage> slideImages = slide.getSlideImages();
        
        if(slideImages != null){
            slideImages.forEach(slideImage -> slideImage.setSlide(null));
        }
    
        Set<TextBox> textBoxes = slide.getTextBoxes();
    
        if(textBoxes != null){
            textBoxes.forEach(textBox -> textBox.setSlide(null));
        }
        
    }
    
    
    
    
}