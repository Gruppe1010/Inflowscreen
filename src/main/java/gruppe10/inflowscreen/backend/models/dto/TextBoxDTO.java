package gruppe10.inflowscreen.backend.models.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
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
    // annotation for at den kan tage imod boolsk værdi i JSON-format i fetch
    // https://stackoverflow.com/questions/21913955/json-post-request-for-boolean-field-sends-false-by-default
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
