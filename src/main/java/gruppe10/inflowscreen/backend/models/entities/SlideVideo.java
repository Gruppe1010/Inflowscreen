package gruppe10.inflowscreen.backend.models.entities;

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

    private String imagePath;
    private boolean fullscreen;
    private double x;
    private double y;
    
    public SlideVideo(String imagePath, boolean fullscreen, double x, double y)
    {
        this.imagePath = imagePath;
        this.fullscreen = fullscreen;
        this.x = x;
        this.y = y;
    }
}

