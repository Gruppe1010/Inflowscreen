package gruppe10.inflowscreen.backend.models.entities;

import lombok.*;

import javax.persistence.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name="slide_text_boxes")
public class TextBox {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "id_slide")
    private Slide slide;

    private String text;
    private String font;
    private int fontSize;
    private String fontColour;
    private boolean isList;
    private boolean bold;
    private boolean italic;
    private boolean underline;
    private String margin;
    private double left;
    private double top;

    public TextBox(String text, String font, int fontSize, String fontColour, boolean isList, boolean bold, boolean italic, boolean underline, String margin, double left, double top) {
        this.text = text;
        this.font = font;
        this.fontSize = fontSize;
        this.fontColour = fontColour;
        this.isList = isList;
        this.bold = bold;
        this.italic = italic;
        this.underline = underline;
        this.margin = margin;
        this.left = left;
        this.top = top;
    }
}
