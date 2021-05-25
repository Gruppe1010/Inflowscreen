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
    private Integer id;
    // vi laver et set for at fremtidsikre at vi kan tilføje en funktion med at man kan dele samme slide på tværs af
    // virksomheder
    @NotNull
    @ManyToMany(mappedBy = "slides")//, cascade = CascadeType.ALL)
    private Set<Organisation> organisations;
    
    @NotNull
    @Column(unique = true)
    private String title;
    
    private int frequency;
    
    private boolean isActive;
    
    public Slide(int id, String title, int frequency, boolean isActive, String themePath)
    {
        this.id = id;
        this.organisations = new HashSet<>();
        this.title = title;
        this.frequency = frequency;
        this.isActive = isActive;
        this.themePath = themePath;
    }

    public Slide(String title, int frequency, boolean isActive, Set<TextBox> textBoxes, Set<SlideVideo> slideVideos, String themePath) {
        this.title = title;
        this.frequency = frequency;
        this.isActive = isActive;
        this.textBoxes = textBoxes;
        this.slideVideos = slideVideos;
        this.themePath = themePath;
    }

    @OneToMany(mappedBy = "slide")
    private Set<TextBox> textBoxes;
    
    
    @OneToMany(mappedBy = "slide")
    private Set<SlideImage> slideImages;
    
    @OneToMany(mappedBy = "slide")
    private Set<SlideVideo> slideVideos;
    
    private String themePath;
    
    public int getId()
    {
        return id;
    }
    
    public void setId(int id)
    {
        this.id = id;
    }
    
    public Set<Organisation> getOrganisations()
    {
        return organisations;
    }
    public void setOrganisations(Set<Organisation> organisations)
    {
        this.organisations = organisations;
    }
    public String getTitle()
    {
        return title;
    }
    public void setTitle(String title)
    {
        this.title = title;
    }
    public int getFrequency()
    {
        return frequency;
    }
    public void setFrequency(int frequency)
    {
        this.frequency = frequency;
    }
    public boolean isActive()
    {
        return isActive;
    }
    public void setActive(boolean active)
    {
        isActive = active;
    }
    public Set<TextBox> getTextBoxes()
    {
        return textBoxes;
    }
    public void setTextBoxes(Set<TextBox> textBoxes)
    {
        this.textBoxes = textBoxes;
    }
    public Set<SlideImage> getSlideImages()
    {
        return slideImages;
    }
    public void setSlideImages(Set<SlideImage> slideImages)
    {
        this.slideImages = slideImages;
    }
    public Set<SlideVideo> getSlideVideos()
    {
        return slideVideos;
    }
    public void setSlideVideos(Set<SlideVideo> slideVideos)
    {
        this.slideVideos = slideVideos;
    }
    public String getThemePath()
    {
        return themePath;
    }
    public void setThemePath(String themePath)
    {
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
