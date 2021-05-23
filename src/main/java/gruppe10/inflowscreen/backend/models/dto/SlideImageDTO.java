package gruppe10.inflowscreen.backend.models.dto;

import gruppe10.inflowscreen.backend.models.entities.SlideImage;

import java.awt.*;

public class SlideImageDTO {
    
    private String base64;
    private String top;
    private String left;
    private String width;
    private String height;
    
    public SlideImageDTO() {}

    public SlideImageDTO(String base64, String top, String left, String width, String height) {
        //this.base64 = base64;
        this.top = top;
        this.left = left;
        this.width = width;
        this.height = height;
    }


    public String getBase64() {
        return base64;
    }
    public void setBase64(String base64) {
        this.base64 = base64;
    }
    public String getTop() {
        return top;
    }
    public void setTop(String top) {
        this.top = top;
    }
    public String getLeft() {
        return left;
    }
    public void setLeft(String left) {
        this.left = left;
    }
    public String getWidth() {
        return width;
    }
    public void setWidth(String width) {
        this.width = width;
    }
    public String getHeight() {
        return height;
    }
    public void setHeight(String height) {
        this.height = height;
    }




    @Override
    public String toString() {
        return "SlideImageDTO{" +
                "top='" + top + '\'' +
                ", left='" + left + '\'' +
                ", width='" + width + '\'' +
                ", height='" + height + '\'' +
                '}';
    }

    // OTHERS
    public SlideImage convertToSlideImage(){

        return new SlideImage(createFileFromBase64(), top, left, width, height);

    }

    // TODO LAV DENNE
    public String createFileFromBase64(){


        return "hej";

    }

}
