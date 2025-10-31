package platzi.play.util;

import platzi.play.contenido.Genero;

import java.util.Scanner;

public class ScannerUtils {

    public static Scanner SCANNER = new Scanner(System.in);

    public static String capturarTexto(String mensaje){
        System.out.println(mensaje + ": ");
        return SCANNER.nextLine();
    }

    public static int capturarNumero(String mensaje){
        System.out.println(mensaje + ": ");

        while (!SCANNER.hasNextInt()) {
            System.out.println("Por favor, ingrese un número válido."+mensaje + ": ");
            SCANNER.next(); // Limpiar la entrada inválida
        }
        int dato = SCANNER.nextInt();
        SCANNER.nextLine(); // Limpiar el buffer
        return dato;
    }

    public static double capturarDecimal(String mensaje){
        System.out.println(mensaje + ": ");

        while (!SCANNER.hasNextDouble()) {
            System.out.println("Por favor, ingrese un número decimal válido."+mensaje + ": ");
            SCANNER.next(); // Limpiar la entrada inválida
        }
        double dato = SCANNER.nextDouble();
        SCANNER.nextLine(); // Limpiar el buffer
        return dato;
    }

    public static Genero capturarGenero(String mensaje){

        while (true) {
            System.out.println(mensaje + " ....Opciones: " );
            for (Genero genero : Genero.values()) {
                System.out.println("- " + genero.name());
            }

            System.out.println("Cual es tu elección?");
            String input = SCANNER.nextLine();
            try {
                return Genero.valueOf(input.toUpperCase());
            } catch (IllegalArgumentException e) {
                System.out.println("Género inválido. Por favor, ingrese un género válido."+mensaje + ": ");
            }
        }
    }
}
