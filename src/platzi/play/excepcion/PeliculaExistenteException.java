package platzi.play.excepcion;

public class PeliculaExistenteException extends  RuntimeException {

    public PeliculaExistenteException(String mensaje){
        super("El cotenido "+mensaje+" ya existe en la plataforma.");
    }
}
