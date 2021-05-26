package gruppe10.inflowscreen.backend.models.dto;


import gruppe10.inflowscreen.backend.models.entities.TextBox;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TextBoxDTO {
    
    private String text;
    private String top;
    private String left;
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
    
    
    public TextBox convertToTextBox(){
        return new TextBox(text, top, left, width, height, isBold, isItalic, isUnderlined, isList, font, fontSize,
                fontColour, margin);
    }
    
    @Override
    public String toString() {
        return "TextBoxDTO{" +
                       "text='" + text + '\'' +
                       ", top='" + top + '\'' +
                       ", left='" + left + '\'' +
                       ", width='" + width + '\'' +
                       ", height='" + height + '\'' +
                       ", isBold=" + isBold +
                       ", isItalic=" + isItalic +
                       ", isUnderlined=" + isUnderlined +
                       ", isList=" + isList +
                       ", font='" + font + '\'' +
                       ", fontSize=" + fontSize +
                       ", fontColour='" + fontColour + '\'' +
                       ", margin='" + margin + '\'' +
                       '}';
    }
}
