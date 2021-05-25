package gruppe10.inflowscreen.backend.models.entities;

import lombok.*;

import javax.persistence.*;


@Builder
@Entity
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
    
    
    @ManyToOne
    @JoinColumn(name = "id_slide")
    // @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "filmId")
    private Slide slide;
    
    
    //constructors
    
    
    public SlideImage()
    {
    }

    public SlideImage(int id, String imagePath, String topPx, String leftPx, String width, String height, int zIndex, Slide slide) {
        this.id = id;
        this.imagePath = imagePath;
        this.topPx = topPx;
        this.leftPx = leftPx;
        this.width = width;
        this.height = height;
        this.zIndex = zIndex;
        this.slide = slide;
    }

    public SlideImage(String imagePath, String topPx, String leftPx, String width, String height, int zIndex) {
        this.imagePath = imagePath;
        this.topPx = topPx;
        this.leftPx = leftPx;
        this.width = width;
        this.height = height;
        this.zIndex = zIndex;
    }


    // getters + setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getTopPx() {
        return topPx;
    }

    public void setTopPx(String top1) {
        this.topPx = top1;
    }

    public String getLeftPx() {
        return leftPx;
    }

    public void setLeftPx(String left1) {
        this.leftPx = left1;
    }

    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public int getzIndex() {
        return zIndex;
    }

    public void setzIndex(int zIndex) {
        this.zIndex = zIndex;
    }

    public Slide getSlide() {
        return slide;
    }

    public void setSlide(Slide slide) {
        this.slide = slide;
    }
}