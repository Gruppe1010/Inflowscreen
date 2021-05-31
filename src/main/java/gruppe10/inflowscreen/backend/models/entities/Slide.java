package gruppe10.inflowscreen.backend.models.entities;

import com.sun.istack.NotNull;
import gruppe10.inflowscreen.backend.models.dto.*;
import lombok.*;

import javax.persistence.*;
import java.util.Set;
import java.util.stream.Collectors;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name="slides")
public class Slide {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    //@Column(columnDefinition = "integer auto_increment")
    private int activeSlideOrder;
    // vi laver et set for at fremtidsikre at vi kan tilføje en funktion med at man kan dele samme slide på tværs af
    // virksomheder
    @NotNull
    @ManyToMany(mappedBy = "slides")
    private Set<Organisation> organisations;
    
    @NotNull
    private String title;
    
    private int frequency;
    
    private boolean isActive;
    
    @OneToMany(mappedBy = "slide", cascade = CascadeType.ALL)
    private Set<TextBox> textBoxes;
    
    
    @OneToMany(mappedBy = "slide", cascade = CascadeType.ALL)
    private Set<SlideImage> slideImages;
    
    @OneToMany(mappedBy = "slide", cascade = CascadeType.ALL)
    private Set<SlideVideo> slideVideos;
    
    private String themePath;
    
    
    
    // constructors
    public Slide(String title, int frequency, boolean isActive, Set<TextBox> textBoxes,
                 Set<SlideImage> slideImages, Set<SlideVideo> slideVideos, String themePath) {
        this.title = title;
        this.frequency = frequency;
        this.isActive = isActive;
        this.textBoxes = textBoxes;
        this.slideImages = slideImages;
        this.slideVideos = slideVideos;
        this.themePath = themePath;
    }
    
    
    public CreateOrUpdateSlideDTO convertToSlideDTO(){
    
        Set<SlideImageDTO> slideImageDTOs = null;
        Set<TextBoxDTO> textBoxDTOs = null;
        Set<SlideVideoDTO> slideVideoDTOs = null;
    
        // converterer hvert SlideImageDTO-obj på listen til en SlideImage og lægger dem i Set
        if(slideImages != null){
            slideImageDTOs =
                    slideImages.stream().map(SlideImage::convertToSlideImageDTO).collect(Collectors.toSet());
        }
        if(textBoxes != null) {
            textBoxDTOs = textBoxes.stream().map(TextBox::convertToTextBoxDTO).collect(Collectors.toSet());
        }
        if(slideVideos != null) {
            slideVideoDTOs =
                    slideVideos.stream().map(SlideVideo::convertToSlideVideoDTO).collect(Collectors.toSet());
        }
    
        return new CreateOrUpdateSlideDTO(title, 0, true, textBoxDTOs, slideImageDTOs, slideVideoDTOs, null);
    }
    
    
    public IndexSlideDTO convertToIndexSlideDTO(){
        return new IndexSlideDTO(id, activeSlideOrder, title, isActive);
    }
    
    
    @Override
    public String toString()
    {
        return "Slide{" +
                       "id=" + id +
                       ", organisations=" + organisations +
                       ", title='" + title + '\'' +
                       ", frequency=" + frequency +
                       ", isActive=" + isActive +
                       ", textBoxes=" + textBoxes +
                       ", slideImages=" + slideImages +
                       ", slideVideos=" + slideVideos +
                       ", themePath='" + themePath + '\'' +
                       '}';
    }
}
