package gruppe10.inflowscreen.backend.models.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


public class IndexSlideDTO {
    
    private int id;
    private String title;
    private boolean isActive;
    
    
    // constructor
    public IndexSlideDTO() {
    }
    
    public IndexSlideDTO(int id, String title, boolean isActive) {
        this.id = id;
        this.title = title;
        this.isActive = isActive;
    }
    
    // setters + getters
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public boolean isActive() {
        return isActive;
    }
    public void setActive(boolean active) {
        isActive = active;
    }
    
    @Override
    public String toString() {
        return "IndexSlideDTO{" +
                       "id=" + id +
                       ", title='" + title + '\'' +
                       ", isActive=" + isActive +
                       '}';
    }
}
