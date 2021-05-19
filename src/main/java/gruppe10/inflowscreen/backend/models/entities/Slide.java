package gruppe10.inflowscreen.backend.models.entities;

import com.sun.istack.NotNull;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Slide {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    // vi laver et set for at fremtidsikre at vi kan tilføje en funktion med at man kan dele samme slide på tværs af
    // virksomheder
    @NotNull
    private Set<Organisation> organisations;
    
    @NotNull
    @Column(unique = true)
    private String title;
    
    private int frequency;
    
    private boolean isActive;
    
    private Set<TextBox> textBoxes;
    
    @OneToMany()
    private Set<SlideImage> slideImages;
    
    private Set<SlideVideo> slideVideos;
    
    private String themePath;
    
    
    
}
