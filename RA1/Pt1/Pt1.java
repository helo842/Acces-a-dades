package RA1.Pt1;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

// Lectura i anàlisi d'un fitxer de text amb Java
public class Pt1 {

    private static final String RUTA_FITXER = "RA1/Pt1/text.txt";

    public static void main(String[] args) {
        File fitxer = new File(RUTA_FITXER);

        int numCaracters = 0;
        int numLinies = 0;
        int numParaules = 0;
        int[] frequencia = new int[65536];

        boolean dinsParaula = false;
        boolean retornaDeCarroAnterior = false;
        int ultimCodi = -1;

        try (FileReader lector = new FileReader(fitxer, StandardCharsets.UTF_8)) {
            int c;

            while ((c = lector.read()) != -1) {
                char caracter = (char) c;
                ultimCodi = c;

                if (caracter == '\r' || caracter == '\n') {
                    numLinies++;

                    // Un salt de línia de Windows (\r\n) només compta una vegada.
                    if (caracter == '\r') {
                        retornaDeCarroAnterior = true;
                    } else if (retornaDeCarroAnterior) {
                        numLinies--;
                        retornaDeCarroAnterior = false;
                    }
                } else {
                    retornaDeCarroAnterior = false;
                    numCaracters++;

                    if (esSeparador(caracter)) {
                        dinsParaula = false;
                    } else {
                        if (!dinsParaula) {
                            numParaules++;
                            dinsParaula = true;
                        }
                        frequencia[caracter]++;
                    }
                }
            }

            // Cal comptar la darrera línia encara que no tingui salt de línia final.
            if (ultimCodi != -1 && ultimCodi != '\r' && ultimCodi != '\n') {
                numLinies++;
            }

            int mesFrequent = 0;
            char caracterMesFrequent = '\0';

            for (int i = 0; i < frequencia.length; i++) {
                if (frequencia[i] > mesFrequent) {
                    mesFrequent = frequencia[i];
                    caracterMesFrequent = (char) i;
                }
            }

            System.out.println("Nombre de caràcters: " + numCaracters);
            System.out.println("Nombre de línies: " + numLinies);
            System.out.println("Nombre de paraules: " + numParaules);

            if (mesFrequent == 0) {
                System.out.println("Caràcter més repetit: no hi ha cap caràcter");
            } else {
                System.out.println("Caràcter més repetit: " + caracterMesFrequent);
            }

        } catch (FileNotFoundException e) {
            System.err.println("No s'ha pogut obrir el fitxer.");
        } catch (IOException e) {
            System.err.println("S'ha produït un error en llegir el fitxer.");
        } catch (SecurityException e) {
            System.err.println("No s'ha pogut accedir al fitxer.");
        }
    }

    private static boolean esSeparador(char caracter) {
        return caracter == ' ' || caracter == '\t';
    }
}
