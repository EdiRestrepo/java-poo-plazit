package platzi.play.plataforma;

import platzi.play.contenido.*;
import platzi.play.excepcion.PeliculaExistenteException;
import platzi.play.util.FileUtils;

import java.util.*;

public class Plataforma {

    private String nombre;
    private List<Contenido> contenido;
    private Map<Contenido, Integer> vistas;

    public Plataforma(String nombre){
        this.nombre = nombre;
        this.contenido = new ArrayList<>();
        this.vistas = new HashMap<>();
    }

    public void agregar(Contenido contenido){

        Contenido contenidoExistente = buscarPorTitulo(contenido.getTitulo());
        if(contenidoExistente != null){
            throw new PeliculaExistenteException(contenido.getTitulo());
        }

        FileUtils.escribirPeliculasEnArchivo(contenido);
        this.contenido.add(contenido);
    }

    public void  reproducir(Contenido contenido){
        int conteoVistas = vistas.getOrDefault(contenido, 0);
        System.out.println("Esta película ha sido vista " + conteoVistas + " veces.");
        this.contarVisualizacion(contenido);
        contenido.reproducir();
    }

    private void  contarVisualizacion(Contenido contenido){
        int conteoVistas = vistas.getOrDefault(contenido, 0);
        vistas.put(contenido, conteoVistas + 1);
    }

    public String getNombre() {
        return nombre;
    }

    public List<Contenido> getContenido() {
        return contenido;
    }

    public  List<String> getTitulos(){
       /* for (Pelicula pelicula : contenido) {
            System.out.println(pelicula.getTitulo());
        }*/

        //contenido.forEach(pelicula -> System.out.println(pelicula.getTitulo()));
       return contenido.stream()
                .map(Contenido::getTitulo)
                .toList();

    }

    public  List<ResumenContenido> getResumenContenido(){
       /* List<ResumenContenido> resumenes = new ArrayList<>();
        for (Pelicula pelicula : contenido) {
            ResumenContenido resumen = new ResumenContenido(
                    pelicula.getTitulo(),
                    pelicula.getDuracion(),
                    pelicula.getGenero()
            );
            resumenes.add(resumen);
        }
        return resumenes;*/

       return contenido.stream()
                .map(pelicula -> new ResumenContenido(
                        pelicula.getTitulo(),
                        pelicula.getDuracion(),
                        pelicula.getGenero()
                ))
                .toList();
    }

    public void  eleminar(Contenido contenido){
        this.contenido.remove(contenido);
    }

    public Contenido buscarPorTitulo(String titulo){
       /* for (Pelicula pelicula : contenido) {
            if(pelicula.getTitulo().equalsIgnoreCase(titulo)){
                return pelicula;
            }

            return null;
        }*/

       return contenido.stream()
                .filter(pelicula -> pelicula.getTitulo().equalsIgnoreCase(titulo))
                .findFirst()
                .orElse(null);

    }

    public List<Contenido> buscarPorGenero(Genero genero){
       /* List<Pelicula> resultados = new ArrayList<>();

        for (Pelicula pelicula : contenido) {
            if(pelicula.getGenero().equalsIgnoreCase(genero)){
                resultados.add(pelicula);
            }
        }

        return resultados;*/
        return contenido.stream()
                .filter(pelicula -> pelicula.getGenero().equals(genero))
                .toList();
    }

    public int getDuracionTotal(){
        /*int duracionTotal = 0;
        for (Pelicula pelicula : contenido) {
            duracionTotal += pelicula.getDuracion();
        }
        return duracionTotal;*/

        return contenido.stream()
                .mapToInt(Contenido::getDuracion)
                .sum();
    }

    public  List<Contenido> getPeliculasPopulares(int limite){
        /*List<Pelicula> populares = new ArrayList<>();
        for (Pelicula pelicula : contenido) {
            if(pelicula.esPopular()){
                populares.add(pelicula);
            }
        }
        return populares;*/

        return contenido.stream()
                .sorted(Comparator.comparing(Contenido::getCalificacion).reversed())
                .limit(limite)
                .toList();
    }

    public List<Pelicula> getPeliculas(){
        return contenido.stream()
                .filter(contenido -> contenido instanceof Pelicula)
                .map(contenidoFiltrado -> (Pelicula) contenidoFiltrado)
                .toList();
    }

    public List<Documental> getDocumentales(){
        return contenido.stream()
                .filter(contenido -> contenido instanceof Documental)
                .map(contenidoFiltrado -> (Documental) contenidoFiltrado)
                .toList();
    }

    public List<Promocionable> getPromocionables(){
        return contenido.stream()
                .filter(contenido -> contenido instanceof Promocionable)
                .map(contenidoFiltrado -> (Promocionable) contenidoFiltrado)
                .toList();
    }

}
