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
    private String top;
    private String left;
    private String width;
    private String height;
    private String zIndex;
    
    
    @ManyToOne
    @JoinColumn(name = "id_slide")
    // @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "filmId")
    private Slide slide;
    
    
    //constructors
    
    
    public SlideImage()
    {
    }
    
    public SlideImage(int id, String imagePath, String top, String left, String width, String height, String zIndex,
                      Slide slide)
    {
        this.id = id;
        this.imagePath = imagePath;
        this.top = top;
        this.left = left;
        this.width = width;
        this.height = height;
        this.zIndex = zIndex;
        this.slide = slide;
    }
    
    public SlideImage(String imagePath, String top, String left, String width, String height, String zIndex) {
        this.imagePath = imagePath;
        this.top = top;
        this.left = left;
        this.width = width;
        this.height = height;
        this.zIndex = zIndex;
    }
    
    // getters + setters
    public int getId()
    {
        return id;
    }
    public void setId(int id)
    {
        this.id = id;
    }
    public String getImagePath()
    {
        return imagePath;
    }
    public void setImagePath(String imagePath)
    {
        this.imagePath = imagePath;
    }
    public String getTop()
    {
        return top;
    }
    public void setTop(String top)
    {
        this.top = top;
    }
    public String getLeft()
    {
        return left;
    }
    public void setLeft(String left)
    {
        this.left = left;
    }
    public String getWidth()
    {
        return width;
    }
    public void setWidth(String width)
    {
        this.width = width;
    }
    public String getHeight()
    {
        return height;
    }
    public void setHeight(String height)
    {
        this.height = height;
    }
    
    public String getzIndex()
    {
        return zIndex;
    }
    
    public void setzIndex(String zIndex)
    {
        this.zIndex = zIndex;
    }
    
    public Slide getSlide()
    {
        return slide;
    }
    public void setSlide(Slide slide)
    {
        this.slide = slide;
    }
}