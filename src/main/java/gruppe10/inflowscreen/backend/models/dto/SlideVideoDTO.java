package gruppe10.inflowscreen.backend.models.dto;

import gruppe10.inflowscreen.backend.models.entities.SlideVideo;

public class SlideVideoDTO {
    
    private String videoBase64;
    
    
    
    //     public SlideVideo(String imagePath, boolean fullscreen, double x, double y)
    public SlideVideo convertToSlideVideo(){
        return new SlideVideo(videoBase64, true, 3.4, 6.7);
    }


}
