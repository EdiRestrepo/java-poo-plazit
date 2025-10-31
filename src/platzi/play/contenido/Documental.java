package platzi.play.contenido;

public class Documental extends Contenido implements Promocionable {

    private String narrador;

    public Documental(String titulo, int duracion, Genero genero) {
        super(titulo, duracion, genero);
    }


    public Documental(String titulo, int duracion, Genero genero, double calificacion, String narrador) {
        super(titulo, duracion, genero, calificacion);
        this.narrador = narrador;
    }


    @Override
    public void reproducir() {
        System.out.println("Reproduciendo el documental: " + getTitulo() + ", narrado por " + narrador);
    }

    @Override
    public String promocionar() {
        return "✨ No te pierdas el documental '" + this.getTitulo() + "' narrado por " + narrador + "! ✨";
    }

    public String getNarrador() {
        return narrador;
    }


}
