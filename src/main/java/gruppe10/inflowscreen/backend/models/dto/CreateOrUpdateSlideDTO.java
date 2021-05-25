package gruppe10.inflowscreen.backend.models.dto;

import gruppe10.inflowscreen.backend.models.entities.Slide;
import gruppe10.inflowscreen.backend.models.entities.SlideImage;
import gruppe10.inflowscreen.backend.models.entities.SlideVideo;
import gruppe10.inflowscreen.backend.models.entities.TextBox;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.util.Set;
import java.util.stream.Collectors;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateOrUpdateSlideDTO {
    
    private String title;
    private int frequency;
    private boolean isActive;
    private Set<TextBoxDTO> textBoxDTOs;
    private Set<SlideImageDTO> slideImageDTOs;
    // private Set<SlideVideoDTO> slideVideoDTOs;
    private String themePath;


    @Override
    public String toString() {
        return "CreateOrUpdateSlideDTO{" +
                "title='" + title + '\'' +
                ", frequency=" + frequency +
                ", isActive=" + isActive +
                ", textBoxeDTOs=" + textBoxDTOs +
                ", slideImageDTOs=" + slideImageDTOs +
                //", slideVideosDTOs=" + slideVideoDTOs +
                ", themePath='" + themePath + '\'' +
                '}';
    }

    // ANDRE METODER
    public Slide convertToSlide(){

        Set<SlideImage> slideImages = null;
        Set<TextBox> textBoxes = null;

        // converterer hvert SlideImageDTO-obj på listen til en SlideImage og lægger dem i Set
        if(slideImageDTOs != null) {
            slideImages = slideImageDTOs.stream().map(SlideImageDTO::convertToSlideImage).collect(Collectors.toSet());
        }
        if(textBoxDTOs != null) {
            textBoxes = textBoxDTOs.stream().map(TextBoxDTO::convertToTextBox).collect(Collectors.toSet());
        }
        // Set<SlideVideo> slideVideos = slideVideoDTOs.stream().map(SlideVideoDTO::convertToSlideVideo).collect(Collectors.toSet());


        //return new Slide(title, 0, true, null, slideImages, null, null);
        return new Slide(title, 0, true, null, null, null);
    }
}
