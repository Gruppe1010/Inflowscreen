package gruppe10.inflowscreen.backend.models.entities;

import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

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
    // vi laver et set for at fremtidsikre at vi kan tilføje en funktion med at man kan dele samme slide på tværs af
    // virksomheder
    @NotNull
    @ManyToMany(mappedBy = "slides")//, cascade = CascadeType.ALL)
    private Set<Organisation> organisations;
    
    @NotNull
    private String title;
    
    private int frequency;
    
    private boolean isActive;
    
    @OneToMany(mappedBy = "slide")
    private Set<TextBox> textBoxes;
    
    
    @OneToMany(mappedBy = "slide")
    private Set<SlideImage> slideImages;
    
    @OneToMany(mappedBy = "slide")
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
