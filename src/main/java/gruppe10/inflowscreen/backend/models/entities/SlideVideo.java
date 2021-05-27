package gruppe10.inflowscreen.backend.models.entities;

import gruppe10.inflowscreen.backend.models.dto.SlideVideoDTO;
import lombok.*;

import javax.persistence.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name="slide_videos")
public class SlideVideo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_slide")
    private Slide slide;
    private String videoBase64;
    
    
    
    public SlideVideo(String videoBase64) {
      this.videoBase64 = videoBase64;
    }
    
    public SlideVideoDTO convertToSlideVideoDTO(){
        return new SlideVideoDTO(videoBase64);
        
    }
    
}

