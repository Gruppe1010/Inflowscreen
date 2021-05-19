package gruppe10.inflowscreen.backend.models.dto;

public class SlideVideoDTO {
    
    //private String videoBase64;
    private boolean fullscreen;
    private double x;
    private double y;
    
    
    public boolean isFullscreen()
    {
        return fullscreen;
    }
    public void setFullscreen(boolean fullscreen)
    {
        this.fullscreen = fullscreen;
    }
    public double getX()
    {
        return x;
    }
    public void setX(double x)
    {
        this.x = x;
    }
    public double getY()
    {
        return y;
    }
    public void setY(double y)
    {
        this.y = y;
    }
}
