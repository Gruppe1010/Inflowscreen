package gruppe10.inflowscreen.backend.models.entities;

import lombok.*;

import javax.persistence.*;


@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name="slide_images")
public class SlideImage {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    private String imagePath;
    private String topPx;
    private String leftPx;
    private String width;
    private String height;
    private int zIndex;
    
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_slide")
    // @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "filmId")
    private Slide slide;
    
    
    //constructors
    public SlideImage(String imagePath, String topPx, String leftPx, String width, String height, int zIndex) {
        this.imagePath = imagePath;
        this.topPx = topPx;
        this.leftPx = leftPx;
        this.width = width;
        this.height = height;
        this.zIndex = zIndex;
    }
    
    
    @Override
    public String toString() {
        int slideID = -1;
        if(slide != null){
            slideID = slide.getId();
        }
        
        return "SlideImage{" +
                       "id=" + id +
                       ", imagePath='" + imagePath + '\'' +
                       ", topPx='" + topPx + '\'' +
                       ", leftPx='" + leftPx + '\'' +
                       ", width='" + width + '\'' +
                       ", height='" + height + '\'' +
                       ", zIndex=" + zIndex +
                       ", slideId=" + slideID +
                       '}';
    }
}