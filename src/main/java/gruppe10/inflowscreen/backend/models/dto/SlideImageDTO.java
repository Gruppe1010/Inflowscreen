package gruppe10.inflowscreen.backend.models.dto;

//import gruppe10.inflowscreen.backend.models.entities.SlideImage;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.istack.NotNull;
import gruppe10.inflowscreen.backend.models.entities.SlideImage;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.io.FileUtils;

import javax.xml.bind.DatatypeConverter;
import java.io.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SlideImageDTO {

    private String fileName;
    private String base64;
    private String top;
    private String left;
    private String width;
    private String height;
    @NotNull
    private int zIndex;

    @Override
    public String toString() {
        return "SlideImageDTO{" +
                "fileName='" + fileName + '\'' +
                ", base64='" + base64.substring(0,20) + '\'' +
                ", top='" + top + '\'' +
                ", left='" + left + '\'' +
                ", width='" + width + '\'' +
                ", height='" + height + '\'' +
                ", zIndex=" + zIndex +
                '}';
    }

    // OTHERS
    
    public SlideImage convertToSlideImage() {
        return new SlideImage(createFileFromBase64(), top, left, width, height, zIndex);
    }
    
    

    /**
     * konverterer base64 til en local fil og returnerer imagePath'en
     * */
    public String createFileFromBase64() {
        // https://stackoverflow.com/questions/23979842/convert-base64-string-to-image
        String[] strings = base64.split(",");

        //convert base64 string to binary data
        byte[] data = DatatypeConverter.parseBase64Binary(strings[1]);
        String path = "src/main/resources/static/images/slides/" + fileName;// + "." + extension;
        String correctPath = path;

        File newFile = new File(path);
        File potentialOldFile = new File(path);   // hvis den allerede findes - så laves dens filPath om
        boolean pathAlreadyExists = false;
        if(newFile.exists()) {
            pathAlreadyExists = true;
            fileName = "1" + fileName;

            path = "src/main/resources/static/images/slides/" + fileName;
            newFile = new File(path);
        }

        try (OutputStream outputStream = new BufferedOutputStream(new FileOutputStream(newFile))) {
            outputStream.write(data);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // hvis den allerede fandtes
        if(pathAlreadyExists){
            try{
                // tjek om de to filer er ens - hvis ja - SLET DEN
                if(FileUtils.contentEquals(newFile, potentialOldFile)){
                    // TODO tag måske imod resultatet
                    newFile.delete();
                }
                else{ // hvis billederne IKKE ens men bare havde samme navn, så skal det være den nye opdaterede path
                    correctPath = path;
                }
            }catch(Exception e){
                System.err.println(e.getMessage());
            }
        }
        return correctPath;
    }

}
