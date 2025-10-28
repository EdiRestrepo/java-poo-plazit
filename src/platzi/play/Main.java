package platzi.play;

import platzi.play.contenido.Pelicula;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        System.out.println("PLATZI PLAY 🚀");

        Pelicula pelicula1 = new Pelicula();
        pelicula1.titulo = "Inception";
        pelicula1.genero = "Ciencia Ficción";
        pelicula1.anioEstreno = 2010;
        pelicula1.calificacion = 4.8;
        pelicula1.descripcion = "Un ladrón que roba secretos corporativos a través del uso de la tecnología de compartir sueños es dado la tarea inversa de plantar una idea en la mente de un CEO.";
        pelicula1.duracion = 148;
        pelicula1.disponible = true;
        pelicula1.reproducir();
        System.out.println(pelicula1.obtenerFichaTecnica());
        pelicula1.califica(4.5);
        System.out.println("¿Es popular? " + (pelicula1.esPopular() ? "Sí" : "No"));


/*        Scanner scanner = new Scanner(System.in);

        System.out.println("Cual es tu nombre?");

        String nombre = scanner.nextLine();

        System.out.println("Hola "+nombre + "esto es Platzi play");

        System.out.println(nombre + "cuantos años tienes?");
        int edad = scanner.nextInt();
        System.out.println("Wow! " + edad + " años, tienes toda una vida por delante!");*/
    }
}
