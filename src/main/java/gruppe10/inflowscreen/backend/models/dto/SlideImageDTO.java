package gruppe10.inflowscreen.backend.models.dto;

public class SlideImageDTO {
    
    private String base64;
    private boolean fullscreen;
    private double x;
    private double y;
    
    public SlideImageDTO() {}
    
    public SlideImageDTO(String base64, boolean fullscreen, double x, double y) {
        this.base64 = base64;
        this.fullscreen = fullscreen;
        this.x = x;
        this.y = y;
    }
}
