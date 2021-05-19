package gruppe10.inflowscreen.backend.models.dto;

import java.util.Set;

public class CreateOrUpdateSlideDTO {
    
    private String title;
    private int frequency;
    private boolean isActive;
    private Set<TextBoxDTO> textBoxes;
    private Set<SlideImageDTO> slideImages;
    private Set<SlideVideoDTO> slideVideos;
    private String themePath;


    public CreateOrUpdateSlideDTO() {}
    public CreateOrUpdateSlideDTO(String title, int frequency, boolean isActive,
                                  Set<TextBoxDTO> textBoxes, Set<SlideImageDTO> slideImages,
                                  Set<SlideVideoDTO> slideVideos, String themePath) {
        this.title = title;
        this.frequency = frequency;
        this.isActive = isActive;
        this.textBoxes = textBoxes;
        this.slideImages = slideImages;
        this.slideVideos = slideVideos;
        this.themePath = themePath;
    }

  
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
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
    public Set<SlideImageDTO> getSlideImages() {
        return slideImages;
    }
    public void setSlideImages(Set<SlideImageDTO> slideImages) {
        this.slideImages = slideImages;
    }
    public Set<SlideVideoDTO> getSlideVideos() {
        return slideVideos;
    }
    public void setSlideVideos(Set<SlideVideoDTO> slideVideos) {
        this.slideVideos = slideVideos;
    }
    public String getThemePath() {
        return themePath;
    }
    public void setThemePath(String themePath) {
        this.themePath = themePath;
    }

    @Override
    public String toString() {
        return "CreateOrUpdateSlideDTO{title='" + title + '\'' + ", frequency=" + frequency
                + ", isActive=" + isActive + ", textBoxes=" + textBoxes + ", slideImages=" + slideImages
                + ", slideVideos=" + slideVideos + ", themePath='" + themePath + '\'' + '}';
    }
}
