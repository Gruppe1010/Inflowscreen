package gruppe10.inflowscreen.backend.models.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


public class IndexSlideDTO implements Comparable<IndexSlideDTO> {
    
    private int id;
    private int activeSlideOrder;
    private String title;
    private boolean isActive;
    
    
    // constructor
    public IndexSlideDTO() {
    }
    
    public IndexSlideDTO(int id, int activeSlideOrder, String title, boolean isActive) {
        this.id = id;
        this.activeSlideOrder = activeSlideOrder;
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
    public int getActiveSlideOrder() {
        return activeSlideOrder;
    }
    public void setActiveSlideOrder(int activeSlideOrder) {
        this.activeSlideOrder = activeSlideOrder;
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
                       ", activeSlideOrder=" + activeSlideOrder +
                       ", title='" + title + '\'' +
                       ", isActive=" + isActive +
                       '}';
    }
    
    @Override
    public int compareTo(IndexSlideDTO o) {
        
        if(activeSlideOrder < o.activeSlideOrder) return -1;
        else if(activeSlideOrder > o.activeSlideOrder) return 1;
        return 0;
    }
}
