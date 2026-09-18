package RA1.Pt1;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

// Exercici — Lectura i anàlisi d’un fitxer de text amb Java

public class Pt1 {
    public static void main(String[] args) {

        File fitxer = new File("RA1/Pt1/text.txt");

        try (FileReader reader = new FileReader("RA1/Pt1/text.txt", StandardCharsets.UTF_8)) {
        
            System.out.println("Ruta relativa: " + fitxer.getPath());
            System.out.println("Ruta absoluta: " + fitxer.getAbsolutePath());

            int c;
            int numCaracters;
            int numLinies;
            int numParaules;
            int[] frequencia = new int[65536];

            while((c = reader.read()) != -1){
                System.out.print((char) c);
            }

            reader.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
