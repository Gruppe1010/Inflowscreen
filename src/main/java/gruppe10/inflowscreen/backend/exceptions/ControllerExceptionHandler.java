package gruppe10.inflowscreen.backend.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

// + denne controller håndterer vores exceptions
// + den bliver kaldt automatisk når vi får en exeption - det er noget som spring gør

@ControllerAdvice
public class ControllerExceptionHandler {

    /*
    * denne metode sørger for at sende noget tilbage, når der sker en exception
    * Spring kalder den automatisk fordi vi har brugt annotationen: @ControllerAdvice over klassen
    *
    * */
    // TODO i stedet for at returnere det komplicerede svar til brugeren kan man gemme det i en logfil
    @ExceptionHandler(Exception.class) // ? TROR: her definerer jeg hvilke typer exceptions den skal fange - undersøg
    public ResponseEntity<ErrorMessage> globalExceptionHandler(Exception e, WebRequest webRequest){


        ErrorMessage errorMessage = new ErrorMessage(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                new Date(),
                e.getMessage(),
                webRequest.getDescription(false)); // ? hvorfor siger vi false?

        return new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);

    }

    // vi laver nu en ny specifik metode som skal håndtere fejlen: vi prøver at dividere med 0
    // det definerer vi i @ExceptionHandler annotationen
    @ExceptionHandler(ArithmeticException.class)
    public ResponseEntity<ErrorMessage> globalArithmaticExceptionHandler(Exception e, WebRequest request){

        ErrorMessage errorMessage = new ErrorMessage(
        HttpStatus.I_AM_A_TEAPOT.value(),
        new Date(),
                "Nu dividerede du lige med 0" + e.getMessage(),
                request.getDescription(false));

        return new ResponseEntity<>(errorMessage, HttpStatus.I_AM_A_TEAPOT);
    }


}
