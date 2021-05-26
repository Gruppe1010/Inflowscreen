package gruppe10.inflowscreen.backend.models.entities;

import lombok.*;

import javax.persistence.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name="text_boxes")
public class TextBox {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_slide")
    private Slide slide;
    
    private String text;
    private String topPx;
    private String leftPx;
    private String width;
    private String height;
    private boolean isBold;
    private boolean isItalic;
    private boolean isUnderlined;
    private boolean isList;
    private String font;
    private int fontSize;
    private String fontColour;
    private String margin;
    
    
    public TextBox(String text, String top, String left, String width, String height, boolean isBold, boolean isItalic,
                   boolean isUnderlined, boolean isList, String font, int fontSize, String fontColour, String margin) {
        this.text = text;
        this.topPx = top;
        this.leftPx = left;
        this.width = width;
        this.height = height;
        this.isBold = isBold;
        this.isItalic = isItalic;
        this.isUnderlined = isUnderlined;
        this.isList = isList;
        this.font = font;
        this.fontSize = fontSize;
        this.fontColour = fontColour;
        this.margin = margin;
    }
}
