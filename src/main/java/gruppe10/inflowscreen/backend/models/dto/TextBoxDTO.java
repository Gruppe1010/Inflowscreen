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

    public TextBox convertToTextBox(){

        return new TextBox(text, font, fontSize, fontColour, isList, bold, italic, underline, margin, left, top);


    }
}
