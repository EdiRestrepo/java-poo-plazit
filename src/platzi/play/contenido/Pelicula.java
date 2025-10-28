package platzi.play.contenido;

public class Pelicula {

    public String titulo;
    public String descripcion;
    public int duracion;
    public String genero;
    public int anioEstreno;
    public double calificacion;
    public boolean disponible;

    public void reproducir() {
        System.out.println("Reproduciendo la película: " + titulo);
    }

    public String obtenerFichaTecnica() {
        return "Título: " + titulo + "\n" +
               "Género: " + genero + "\n" +
               "Año de Estreno: " + anioEstreno + "\n" +
               "Calificación: " + calificacion + "/5" + "\n";
    }

    public void califica(double calificacion){
        if(calificacion >= 0 && calificacion <= 5){
            this.calificacion = calificacion;
            System.out.println("Has calificado la película " + titulo + " con " + calificacion + " estrellas.");
        } else {
            System.out.println("Calificación inválida. Debe estar entre 0 y 5.");
        }
    }

    public boolean esPopular(){
        return this.calificacion >= 4.0;
    }

}
