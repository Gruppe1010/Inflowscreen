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


    public HttpStatus updateSlide(CreateOrUpdateSlideDTO slideDTO){

        // TODO

        return HttpStatus.OK;
    }
    
    public CreateOrUpdateSlideDTO findById(int slideId){
        Optional<Slide> slide = slideRepository.findById(slideId);
        if(slide.isPresent()){
            return slide.get().convertToSlideDTO();
        }
        return null;
    }
    
    public HttpStatus deleteSlideById(int slideId){

        // vi finder alle slideImage id'er som er tilknyttet slidets id
        Optional<ArrayList<Integer>> imgIds = slideImageRepository.findImgIdsBySlideId(slideId);
        //hvis der ER nogle id'er - slet dem fra rep
        imgIds.ifPresent(integers -> integers.forEach(integer -> slideImageRepository.deleteSlideImageById(integer)));

        // vi finder alle textBoxes id'er som er tilknyttet slidets id
        Optional<ArrayList<Integer>> textBoxIds = textBoxRepository.findTextBoxIdsBySlideId(slideId);
        // hvis der ER nogle id'er - slet dem fra rep
        textBoxIds.ifPresent(integers -> integers.forEach(integer -> textBoxRepository.deleteTextBoxById(integer)));

        // vi sletter slide-obj
        slideRepository.deleteSlideById(slideId);

        // vi henter dem op igen og ser om de er der
        Optional<Slide> optSlide = slideRepository.findById(slideId);
        Optional<Set<SlideImage>> optSlideImages = slideImageRepository.findBySlideId(slideId);
        Optional<Set<TextBox>> optTextBoxes = textBoxRepository.findBySlideId(slideId);

        if(optSlide.isEmpty() && optSlideImages.get().size() == 0 && optTextBoxes.get().size() == 0){
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
                
                // tilf??j activeSlideOrder
                addActiveSlideOrder(newlyPersistedSlide);
                
                // l??g slideImages i db
                saveSlideImagesToDb(slide, newlyPersistedSlide);
                
                // l??g textBoxes i db
                saveTextBoxesToDb(slide, newlyPersistedSlide);
                
                return HttpStatus.CREATED;
            }
            // hvis slidet IKKE er present med ny titel, er den ikke blevet gemt i db
            else{ return HttpStatus.CONFLICT; }
        }
        return HttpStatus.CONFLICT; // hvis titel er optaget p?? orgen
    }
    public void addActiveSlideOrder(Slide slide){
        if(slide.isActive()){
            // hvis det er active, skal den tilf??je en slideorder
            slide.setActiveSlideOrder(slide.getId());
            // og gemme slidet med den nye order
            slideRepository.save(slide);
        }
    }
    
    /**
     * Tjekker om organisationen allerede har et slide med titlen
     * @Param organisation - den organisation som pr??ver at oprette et slide
     * @Param title - titlen organisationen pr??ver p?? at give deres nye slide
     * @return boolean - om titlen er ledig
     * */
    public boolean isTitleAvailable(Organisation organisation, String title){
        // vi ser om der er et slide med samme titel, som de pr??ver at oprette
        Optional<Slide> optionalSlide = slideRepository.findByOrganisationAndTitle(organisation, title);
        
        // hvis den IKKE har fundet et slide, bliver dette evalueret til true == titlen er available
        return optionalSlide.isEmpty();
    }
    
    public void saveSlideToDb(Organisation organisation, Slide slide){
        // if der IKKE allerede er nogle slides p?? orgen
        if(organisation.getSlides() == null)
        {
            // oprettes nyt set
            organisation.setSlides(new HashSet<>());
        }
    
        // tilf??j slide til org
        organisation.getSlides().add(slide);
    
        // opdaterer org i db og l??gger dermed slide ned
        organisationRepository.save(organisation);
    }
    
    public void saveSlideImagesToDb(Slide slide, Slide newlyPersistedSlide){
        // vi opretter et Set af de slideImages vi skal l??gge ned i db ud fra slided vi har f??et i requestbody
        Set<SlideImage> slideImages = slide.getSlideImages();
    
        // hvis der ER slideImages p?? slide
        if(slideImages != null)
        {
            // s??tter vi slide-attribut til ophentet slide p?? hver af SlideImage-obj
            slideImages.forEach(slideImage -> slideImage.setSlide(newlyPersistedSlide));
        
            // gemmer vi alle slideImages ned
            slideImageRepository.saveAll(slideImages);
        }
    }
    
    public void saveTextBoxesToDb(Slide slide, Slide newlyPersistedSlide){
        // vi opretter et Set af de TextBoxes vi skal l??gge ned i db ud fra slided vi har f??et i requestbody
        Set<TextBox> textBoxes = slide.getTextBoxes();
        
        // hvis der ER TextBoxes p?? slide
        if(textBoxes != null)
        {
            // s??tter vi slide-attribut til ophentet slide p?? hver af TextBox-obj
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