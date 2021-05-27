package gruppe10.inflowscreen.backend.models.entities;

import gruppe10.inflowscreen.backend.models.dto.SlideImageDTO;
import lombok.*;

import javax.persistence.*;
import javax.xml.bind.DatatypeConverter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;


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
    
    
    public SlideImageDTO convertToSlideImageDTO(){
        
        return new SlideImageDTO(convertPathToFileName(), convertFileToBase64(), topPx, leftPx, width, height, zIndex);
    }
    
    public String convertPathToFileName(){
        // src/main/resources/static/images/slides/ == 40 char lang
        return imagePath.substring(41);
    }
    
    public String convertFileToBase64() {
        
        String base64 = "";
        
        try{
            base64 = DatatypeConverter.printBase64Binary(Files.readAllBytes(
                    Paths.get(imagePath)));
        }catch(Exception e){
            System.err.println("Fejl i at converte fil til base64: " + e.getMessage());
        }
        return base64;
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