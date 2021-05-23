package gruppe10.inflowscreen.backend.models.dto;

import gruppe10.inflowscreen.backend.models.entities.Slide;
import gruppe10.inflowscreen.backend.models.entities.SlideImage;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class CreateOrUpdateSlideDTO {
    
    private String title;
    //private int frequency;
    //private boolean isActive;
    //private Set<TextBoxDTO> textBoxes;
    private Set<SlideImageDTO> images;
    //private Set<SlideVideoDTO> slideVideosDTOs;
    //private String themePath;


    public CreateOrUpdateSlideDTO() {}

    /*
    public CreateOrUpdateSlideDTO(String title, int frequency, boolean isActive,
                                  Set<TextBoxDTO> textBoxes, List<SlideImageDTO> slideImages,
                                  Set<SlideVideoDTO> slideVideos, String themePath) {
        this.title = title;
        this.frequency = frequency;
        this.isActive = isActive;
        this.textBoxes = textBoxes;
        this.images = slideImages;
        this.slideVideosDTOs = slideVideos;
        this.themePath = themePath;
    }

     */

    public CreateOrUpdateSlideDTO(String title,Set<SlideImageDTO> images) {
        this.title = title;
        this.images = images;
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public Set<SlideImageDTO> getImages() {
        return images;
    }
    public void setImages(Set<SlideImageDTO> slideImages) {
        this.images = slideImages;
    }

    /*
    public int getFrequency() {
        return frequency;
    }
    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }
    public boolean isActive() {
        return isActive;
    }
    public void setActive(boolean active) {
        isActive = active;
    }
    public Set<TextBoxDTO> getTextBoxes() {
        return textBoxes;
    }
    public void setTextBoxes(Set<TextBoxDTO> textBoxes) {
        this.textBoxes = textBoxes;
    }

    public Set<SlideVideoDTO> getSlideVideos() {
        return slideVideosDTOs;
    }
    public void setSlideVideos(Set<SlideVideoDTO> slideVideos) {
        this.slideVideosDTOs = slideVideos;
    }
    public String getThemePath() {
        return themePath;
    }
    public void setThemePath(String themePath) {
        this.themePath = themePath;
    }

     */

    @Override
    public String toString() {
        return "CreateOrUpdateSlideDTO{title='" + title + '\'' + ", frequency=" + 0
                + ", isActive=" + true + ", textBoxes=" + null + ", images=" + images
                + ", slideVideos=" + null + ", themePath='" + null + '\'' + '}';
    }


    // ANDRE METODER
    public Slide convertToSlide(){

        // converterer hvert SlideImageDTO-obj på listen til en SlideImage og lægger dem i Set
        Set<SlideImage> slideImages = images.stream().map(SlideImageDTO::convertToSlideImage).collect(Collectors.toSet());

        return new Slide(title, 0, true, null, slideImages, null, null);
    }
}
