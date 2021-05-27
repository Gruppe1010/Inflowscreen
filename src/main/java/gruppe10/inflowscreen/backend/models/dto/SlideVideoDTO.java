package gruppe10.inflowscreen.backend.models.dto;

import gruppe10.inflowscreen.backend.models.entities.SlideVideo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SlideVideoDTO {
    
    private String videoBase64;
    
    
    
    //     public SlideVideo(String imagePath, boolean fullscreen, double x, double y)
    public SlideVideo convertToSlideVideo(){
        return new SlideVideo(videoBase64);
    }


}
