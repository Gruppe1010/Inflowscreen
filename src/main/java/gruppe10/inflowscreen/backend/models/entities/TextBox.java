package gruppe10.inflowscreen.backend.models.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
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
    @JsonProperty
    private boolean isBold;
    @JsonProperty
    private boolean isItalic;
    @JsonProperty
    private boolean isUnderlined;
    @JsonProperty
    private boolean isList;
    private String font;
    private String fontSize;
    private String fontColour;
    private String margin;
    
    
    public TextBox(String text, String top, String left, String width, String height, boolean isBold, boolean isItalic,
                   boolean isUnderlined, boolean isList, String font, String fontSize, String fontColour,
                   String margin) {
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
    
    @Override
    public String toString() {
    
        int slideID = -1;
        if(slide != null){
            slideID = slide.getId();
        }
    
        return "TextBox{" +
                       "id=" + id +
                       ", slideId=" + slideID +
                       ", text='" + text + '\'' +
                       ", topPx='" + topPx + '\'' +
                       ", leftPx='" + leftPx + '\'' +
                       ", width='" + width + '\'' +
                       ", height='" + height + '\'' +
                       ", isBold=" + isBold +
                       ", isItalic=" + isItalic +
                       ", isUnderlined=" + isUnderlined +
                       ", isList=" + isList +
                       ", font='" + font + '\'' +
                       ", fontSize='" + fontSize + '\'' +
                       ", fontColour='" + fontColour + '\'' +
                       ", margin='" + margin + '\'' +
                       '}';
    }
}
