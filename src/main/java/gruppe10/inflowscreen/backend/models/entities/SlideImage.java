package gruppe10.inflowscreen.backend.models.entities;


import lombok.*;

import javax.persistence.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
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

    @ManyToOne
    @JoinColumn(name = "id_slide")
    // @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "filmId")
    private Slide slide;

    public SlideImage(String imagePath, String top, String left, String width, String height) {
        this.imagePath = imagePath;
        this.top = top;
        this.left = left;
        this.width = width;
        this.height = height;
    }
}
