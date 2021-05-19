package gruppe10.inflowscreen.backend.models.entities;


import lombok.*;

import javax.persistence.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name="slide-images")
public class SlideImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String imagePath;
    private boolean fullscreen;
    private double x;
    private double y;

    @ManyToOne
    @JoinColumn(name = "id_slide")
    // @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "filmId")
    private Slide slide;

}
