package platzi.play.plataforma;

import platzi.play.contenido.Contenido;

import java.time.LocalDateTime;

public class Usuario {

    private String nombre;
    private String email;
    private LocalDateTime fechaRegistro;

    public Usuario(String nombre, String email) {
        this.nombre = nombre;
        this.email = email;
        this.fechaRegistro = LocalDateTime.now();
    }

    public void ver(Contenido contenido) {
        //System.out.println(nombre + " está viendo la película: " + pelicula.titulo);
        contenido.reproducir();
    }

    public String getNombre() {
        return nombre;
    }

    public String getEmail() {
        return email;
    }

    public LocalDateTime getFechaRegistro() {
        return fechaRegistro;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setFechaRegistro(LocalDateTime fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }
}
