package gruppe10.inflowscreen.backend.exceptions;

// HER laver vi vores EGEN Exception
// extends RuntimeException == vi laver den unchecked - Erik er usikker på om den skal være checked
// vi bruger denne klasse til at vi kan throwe den type exception i vores FilmRestController
public class ResourceNotFound extends RuntimeException {

    // TODO læs på denne
    private static final long serialVersionUID = 1L;

    public ResourceNotFound(String message){
        super(message); // RuntimeExceptions constructor
    }


}
