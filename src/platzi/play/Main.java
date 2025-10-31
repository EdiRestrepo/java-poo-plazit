package platzi.play;

import platzi.play.contenido.*;
import platzi.play.excepcion.PeliculaExistenteException;
import platzi.play.plataforma.Plataforma;
import platzi.play.util.FileUtils;
import platzi.play.util.ScannerUtils;

import java.util.List;

public class Main {


    public static final String NOMBRE_PLATAFORMA = "PLATZI PLAY 🚀";
    public static final String VERSION = "1.0.0";
    public static final int SALIR = 9;
    public static final int AGREGAR_CONTENIDO = 1;
    public static final int MOSTRAR_TODO = 2;
    public static final int BUSCAR_POR_TITULO = 3;
    public static final int BUSCAR_POR_GENERO = 4;
    public static final int VER_POPULARES = 5;
    public static final int REPRODUCIR = 6;
    public static final int BUSCAR_POR_TIPO = 7;
    public static final int ELIMINAR_CONTENIDO = 8;

    public static void main(String[] args) {
        System.out.println(NOMBRE_PLATAFORMA +"v" + VERSION);

        Plataforma plataforma = new Plataforma(NOMBRE_PLATAFORMA);

        cargarPeliculas(plataforma);

        System.out.println("Mas de " +plataforma.getDuracionTotal() + " minutos de entretenimiento disponible!");

        plataforma.getPromocionables().forEach(promocionable -> System.out.println(promocionable.promocionar()));

        while(true){
            int opcionElegida = ScannerUtils.capturarNumero("""
                    Ingrese una de las siguientes opciones:
                    1. Agregar contenido
                    2. Mostrar todo
                    3. Buscar por título
                    4. Buscar por género
                    5. Ver populares
                    6. Reproducir contenido
                    7. Buscar por tipo
                    8. Eliminar contenido
                    9. Salir
                    """
            );

            System.out.println("Usted eligió la opción: " + opcionElegida);

            switch (opcionElegida){
                case AGREGAR_CONTENIDO -> {

                    int tipoContenido = ScannerUtils.capturarNumero("""
                            ¿Qué tipo de contenido desea agregar?
                            1. Película
                            2. Documental
                            """);
                    String nombre = ScannerUtils.capturarTexto("Nombre del contenido");
                    Genero genero = ScannerUtils.capturarGenero("Género del contenido");
                    int duracion = ScannerUtils.capturarNumero("Duración del contenido");
                    double calificacion = ScannerUtils.capturarDecimal("Calificación del contenido");

                    try{
                        if(tipoContenido == 1){
                            plataforma.agregar(new Pelicula(nombre, duracion, genero, calificacion));
                        }else {
                            String narrador = ScannerUtils.capturarTexto("Nombre del narrador del documental");
                            plataforma.agregar(new Documental(nombre, duracion, genero, calificacion,narrador));
                        }

                    }catch (PeliculaExistenteException e){
                        System.out.println(e.getMessage());
                    }


                }
                //case MOSTRAR_TODO -> plataforma.getTitulos();
                case MOSTRAR_TODO -> {
                    //var titulos = plataforma.getTitulos();
                    List<ResumenContenido> contenidoResumido = plataforma.getResumenContenido();
                    contenidoResumido.forEach(resumen -> System.out.println(resumen.toString()));
                }
                case BUSCAR_POR_TITULO -> {
                    String tituloBuscado = ScannerUtils.capturarTexto("Ingrese el título a buscar");
                    Contenido contenidoEncontrada = plataforma.buscarPorTitulo(tituloBuscado);
                    if(contenidoEncontrada != null){
                        System.out.println("Película encontrada:");
                        System.out.println(contenidoEncontrada.obtenerFichaTecnica());
                    } else {
                        System.out.println("No se encontró ninguna película con el título: " + tituloBuscado);
                    }

                }
                case BUSCAR_POR_GENERO -> {
                    Genero generoBuscado = ScannerUtils.capturarGenero("Ingrese el género a buscar");
                    var peliculasPorGenero = plataforma.buscarPorGenero(generoBuscado);
                    if(!peliculasPorGenero.isEmpty()){
                        System.out.println(peliculasPorGenero.size() + " películas encontradas del género: " + generoBuscado);
                        peliculasPorGenero.forEach(pelicula -> System.out.println(pelicula.obtenerFichaTecnica() +"\n"));
                    } else {
                        System.out.println("No se encontraron películas del género: " + generoBuscado);
                    }
                }
                case VER_POPULARES -> {
                    int cantidadPopulares = ScannerUtils.capturarNumero("¿Cuántas películas populares desea ver?");
                    var peliculasPopulares = plataforma.getPeliculasPopulares(cantidadPopulares);
                    System.out.println("Películas populares:");
                    peliculasPopulares.forEach(pelicula -> System.out.println(pelicula.obtenerFichaTecnica() + "\n"));
                }
                case REPRODUCIR -> {
                    String tituloAReproducir = ScannerUtils.capturarTexto("Ingrese el título a reproducir");
                    Contenido contenidoAReproducir = plataforma.buscarPorTitulo(tituloAReproducir);
                    if(contenidoAReproducir != null){
                        plataforma.reproducir(contenidoAReproducir);
                    } else {
                        System.out.println("No se encontró ninguna película con el título: " + tituloAReproducir);
                    }
                }
                case BUSCAR_POR_TIPO -> {
                    int tipoDeContenido = ScannerUtils.capturarNumero("""
                            ¿Qué tipo de contenido desea buscar?
                            1. Película
                            2. Documental
                            """);

                    if(tipoDeContenido == 1){
                        List<Pelicula> peliculas = plataforma.getPeliculas();
                        peliculas.forEach(pelicula -> System.out.println(pelicula.obtenerFichaTecnica() + "\n"));
                    }else {
                        List<Documental> documentales = plataforma.getDocumentales();
                        documentales.forEach(documental -> System.out.println(documental.obtenerFichaTecnica() + "\n"));
                    }
                }
                case ELIMINAR_CONTENIDO -> {
                    String tituloAEliminar = ScannerUtils.capturarTexto("Ingrese el título a eliminar");
                    Contenido contenidoAEliminar = plataforma.buscarPorTitulo(tituloAEliminar);
                    if(contenidoAEliminar != null){
                        plataforma.eleminar(contenidoAEliminar);
                        System.out.println("Película '" + tituloAEliminar + "' eliminada correctamente.");
                    } else {
                        System.out.println("No se encontró ninguna película con el título: " + tituloAEliminar);
                    }
                }
                case SALIR -> {
                }
            }


      /*      if(opcionElegida == AGREGAR_CONTENIDO) {

            } else if (opcionElegida == MOSTRAR_TODO) {
                plataforma.mostrarTitulos();
            } else if (opcionElegida == BUSCAR_POR_TITULO) {

            } else if (opcionElegida == ELIMINAR_CONTENIDO) {

            } else if(opcionElegida == SALIR){
                System.out.println("Gracias por usar " + NOMBRE_PLATAFORMA);
                System.exit(0);
                break;
            }*/
        }

       /* String nombre = ScannerUtils.capturarTexto("Nombre del contenido");
        String genero = ScannerUtils.capturarTexto("Género del contenido");
        int duracion = ScannerUtils.capturarNumero("Duración del contenido");
        double calificacion = ScannerUtils.capturarDecimal("Calificación del contenido");


        Pelicula pelicula1 = new Pelicula(nombre, duracion, genero, calificacion);
        Pelicula pelicula2 = new Pelicula("Inception", 148, "Ciencia Ficción", 4.8);

        plataforma.agregar(pelicula1);
        plataforma.agregar(pelicula2);
        plataforma.eleminar(pelicula1);

        System.out.println("Numero de películas en la plataforma: " + plataforma.getContenido().size());
        plataforma.mostrarTitulos();
        //pelicula1.titulo = nombre;
        //pelicula1.genero = genero;
        //pelicula1.fechaEstreno = LocalDate.of(2010, 7, 16);
        //pelicula1.calificacion = calificacion;
        //pelicula1.descripcion = "Un ladrón que roba secretos corporativos a través del uso de la tecnología de compartir sueños es dado la tarea inversa de plantar una idea en la mente de un CEO.";
        //pelicula1.duracion = duracion;

        //long duracionLong = pelicula1.duracion;
        //System.out.println("Duración de la película en minutos: " + duracionLong);

        //int calificacionInt = (int) pelicula1.calificacion;
        //System.out.println("Calificación de la película como entero: " + calificacionInt);

        //long numeroDePremios = (int) Long.parseLong("2500000000");
        //System.out.println("Número de premios: " + numeroDePremios);

        //pelicula1.disponible = true;
        //pelicula1.reproducir();
        //System.out.println(pelicula1.obtenerFichaTecnica());
        //pelicula1.calificar(4.5);
        //System.out.println("¿Es popular? " + (pelicula1.esPopular() ? "Sí" : "No"));

        Usuario usuario1 = new Usuario("Ana", "ana@plazit.com");
        //usuario1.nombre = "Carlos";
        //usuario1.fechaRegistro = LocalDateTime.of(2022, 5, 10, 14, 30, 14);
        //System.out.println(usuario1.fechaRegistro);
        usuario1.ver(pelicula1);*/




    }

    private static void cargarPeliculas(Plataforma plataforma) {

        plataforma.getContenido().addAll(FileUtils.leerPeliculasDesdeArchivo());

       /* plataforma.agregar(new Pelicula("Inception", 148, Genero.CIENCIA_FICCION, 4.8));
        plataforma.agregar(new Pelicula("The Dark Knight", 152, Genero.ACCION, 4.9));
        plataforma.agregar(new Pelicula("Interstellar", 169, Genero.AVENTURA, 4.7));
        plataforma.agregar(new Pelicula("Parasite", 132, Genero.DRAMA, 4.6));*/


    }
}
