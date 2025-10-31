package platzi.play.contenido;

import java.time.LocalDate;

public abstract class Contenido {

    private String titulo;
    private String descripcion;
    private int duracion;
    private Genero genero;
    //public int anioEstreno;
    private double calificacion;
    private boolean disponible;
    private LocalDate fechaEstreno;

    public Contenido(String titulo, int duracion, Genero genero){
        this.titulo = titulo;
        this.duracion = duracion;
        this.genero = genero;
        this.fechaEstreno = LocalDate.now();

    }

    public Contenido(String titulo, int duracion, Genero genero, double calificacion) {
        this(titulo, duracion, genero);
        this.calificacion = calificacion;
        ///this.calificar(calificacion);
    }

    public abstract void reproducir();

    public String obtenerFichaTecnica() {
        return "Título: " + titulo + "\n" +
               "Género: " + genero + "\n" +
               "Año de Estreno: " + fechaEstreno.getYear() + "\n" +
               "Calificación: " + calificacion + "/5" + "\n";
    }

    public void calificar(double calificacion){
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

    public String getTitulo() {
        return titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public int getDuracion() {
        return duracion;
    }

    public Genero getGenero() {
        return genero;
    }

    public double getCalificacion() {
        return calificacion;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public LocalDate getFechaEstreno() {
        return fechaEstreno;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    public void setFechaEstreno(LocalDate fechaEstreno) {
        this.fechaEstreno = fechaEstreno;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }
}
