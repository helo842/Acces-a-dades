package RA1.Pt2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;

public class Pt2 {

    private static final int CLAU_CESAR = 3;
    private static final String RUTA_ENTRADA = "RA1/Pt2/entrada.txt";
    private static final String RUTA_XIFRAT = "RA1/Pt2/xifrat.txt";
    private static final String RUTA_DESXIFRAT = "RA1/Pt2/desxifrat.txt";

    public static void main(String[] args) {
        try {
            xifrar(RUTA_ENTRADA, RUTA_XIFRAT, CLAU_CESAR);
            System.out.println("Procés de xifrat completat.");

            desxifrar(RUTA_XIFRAT, RUTA_DESXIFRAT, CLAU_CESAR);
            System.out.println("Procés de desxifrat completat.");
        } catch (FileNotFoundException e) {
            System.err.println("No s'ha pogut obrir algun dels fitxers.");
        } catch (IOException e) {
            System.err.println("S'ha produït un error de lectura o escriptura.");
        } catch (SecurityException e) {
            System.err.println("No s'ha pogut accedir als fitxers.");
        }
    }

    private static void xifrar(String entrada, String sortida, int clau) throws IOException {
        // Els wrappers permeten llegir i escriure línies amb un búfer intermedi.
        try (BufferedReader lector = crearLector(entrada);
             BufferedWriter escritor = crearEscritor(sortida)) {

            String linia;
            int numeroLinia = 0;

            while ((linia = lector.readLine()) != null) {
                numeroLinia++;
                // Primer invertim la línia i després apliquem el xifrat.
                String liniaInvertida = invertirLinia(linia);
                String liniaXifrada = aplicarCesar(liniaInvertida, clau);

                escritor.write(liniaXifrada);
                escritor.newLine();

                System.out.println("Línia " + numeroLinia + " xifrada.");
            }
        }
    }

    private static void desxifrar(String entrada, String sortida, int clau) throws IOException {
        try (BufferedReader lector = crearLector(entrada);
             BufferedWriter escritor = crearEscritor(sortida)) {

            String linia;
            int numeroLinia = 0;

            while ((linia = lector.readLine()) != null) {
                numeroLinia++;
                // Desfem el xifrat abans de recuperar l'ordre original.
                String liniaDesxifrada = aplicarCesar(linia, -clau);
                String liniaOriginal = invertirLinia(liniaDesxifrada);

                escritor.write(liniaOriginal);
                escritor.newLine();

                System.out.println("Línia " + numeroLinia + " desxifrada.");
            }
        }
    }

    private static BufferedReader crearLector(String ruta) throws IOException {
        return new BufferedReader(
                new InputStreamReader(new FileInputStream(ruta), StandardCharsets.UTF_8)
        );
    }

    private static BufferedWriter crearEscritor(String ruta) throws IOException {
        return new BufferedWriter(
                new OutputStreamWriter(new FileOutputStream(ruta), StandardCharsets.UTF_8)
        );
    }

    private static String invertirLinia(String linia) {
        return new StringBuilder(linia).reverse().toString();
    }

    private static String aplicarCesar(String text, int desplacament) {
        StringBuilder resultat = new StringBuilder(text.length());

        for (int i = 0; i < text.length(); i++) {
            char caracter = text.charAt(i);
            resultat.append((char) (caracter + desplacament));
        }

        return resultat.toString();
    }
}
