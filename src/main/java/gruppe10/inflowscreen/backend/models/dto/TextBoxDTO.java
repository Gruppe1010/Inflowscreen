package gruppe10.inflowscreen.backend.models.dto;

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
    private double x;
    private double y;

    public TextBoxDTO() {
    }

    public TextBoxDTO(String text, String font, int fontSize, String fontColour, boolean isList, boolean bold, boolean italic, boolean underline, String margin, double x, double y) {
        this.text = text;
        this.font = font;
        this.fontSize = fontSize;
        this.fontColour = fontColour;
        this.isList = isList;
        this.bold = bold;
        this.italic = italic;
        this.underline = underline;
        this.margin = margin;
        this.x = x;
        this.y = y;
    }

    public String getText() {
        return text;
    }
    public void setText(String text) {
        this.text = text;
    }
    public String getFont() {
        return font;
    }
    public void setFont(String font) {
        this.font = font;
    }
    public int getFontSize() {
        return fontSize;
    }
    public void setFontSize(int fontSize) {
        this.fontSize = fontSize;
    }
    public String getFontColour() {
        return fontColour;
    }
    public void setFontColour(String fontColour) {
        this.fontColour = fontColour;
    }
    public boolean isList() {
        return isList;
    }
    public void setList(boolean list) {
        isList = list;
    }
    public boolean isBold() {
        return bold;
    }
    public void setBold(boolean bold) {
        this.bold = bold;
    }
    public boolean isItalic() {
        return italic;
    }
    public void setItalic(boolean italic) {
        this.italic = italic;
    }
    public boolean isUnderline() {
        return underline;
    }
    public void setUnderline(boolean underline) {
        this.underline = underline;
    }
    public String getMargin() {
        return margin;
    }
    public void setMargin(String margin) {
        this.margin = margin;
    }
    public double getX() {
        return x;
    }
    public void setX(double x) {
        this.x = x;
    }
    public double getY() {
        return y;
    }
    public void setY(double y) {
        this.y = y;
    }
}
