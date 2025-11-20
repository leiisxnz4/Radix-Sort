import java.io.*;
import java.util.*;

public class RadixSortNombres {

    // Función para obtener la longitud máxima
    public static int getMaxLength(String[] arr) {
        int max = 0;
        for (String s : arr) {
            if (s.length() > max) {
                max = s.length();
            }
        }
        return max;
    }

    // Radix Sort para cadenas
    public static void radixSort(String[] arr) {
        int maxLen = getMaxLength(arr);

        // Desde la última letra hacia el inicio
        for (int pos = maxLen - 1; pos >= 0; pos--) {

            // 27 buckets: [0] para espacios vacíos, [1–26] para A–Z
            ArrayList<ArrayList<String>> buckets = new ArrayList<>();

            for (int i = 0; i < 27; i++) {
                buckets.add(new ArrayList<>());
            }

            for (String s : arr) {
                int bucketIndex = getCharIndex(s, pos);
                buckets.get(bucketIndex).add(s);
            }

            // Reconstruimos el arreglo juntando los buckets
            int index = 0;
            for (ArrayList<String> bucket : buckets) {
                for (String s : bucket) {
                    arr[index++] = s;
                }
            }
        }
    }

    // Obtiene índice de un caracter según su posición
    public static int getCharIndex(String s, int pos) {
        if (pos >= s.length()) {
            return 0; // espacios vacíos
        }
        char c = Character.toUpperCase(s.charAt(pos));
        return (c - 'A') + 1;
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        try {
            // ARCHIVO DE ENTRADA
            System.out.print("Nombre del archivo de entrada: ");
            String archivoEntrada = sc.nextLine();

            File file = new File(archivoEntrada);
            Scanner lector = new Scanner(file);

            ArrayList<String> lista = new ArrayList<>();

            System.out.println("\n--- CONTENIDO DEL ARCHIVO ---");
            while (lector.hasNextLine()) {
                String nombre = lector.nextLine();
                System.out.println(nombre);
                lista.add(nombre);
            }
            lector.close();

            String[] nombres = lista.toArray(new String[0]);

            // ORDENAR CON RADIX SORT
            radixSort(nombres);

            // ARCHIVO DE SALIDA
            System.out.print("\nNombre del archivo de salida: ");
            String archivoSalida = sc.nextLine();

            PrintWriter writer = new PrintWriter(archivoSalida);

            System.out.println("\n--- NOMBRES ORDENADOS (RADIX) ---");
            for (String n : nombres) {
                System.out.println(n);
                writer.println(n);
            }

            writer.close();
            System.out.println("\n✓ Archivo guardado correctamente.");

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        sc.close();
    }
}
