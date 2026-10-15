package RA1.Pt3;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

// Gestió d'un catàleg de videojocs amb persistència en un fitxer binari.
public class Pt3 {

    private static final String RUTA_FITXER = "RA1/Pt3/videojocs.dat";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // Forcem el separador decimal '.' perquè en alguns sistemes seria ','.
        sc.useLocale(Locale.ROOT);
        ArrayList<Videojoc> videojocs = carregarVideojocs();

        int opcio;
        do {
            mostrarMenu();
            opcio = llegirOpcio(sc);

            switch (opcio) {
                case 1:
                    afegirVideojoc(sc, videojocs);
                    break;
                case 2:
                    llistarVideojocs(videojocs);
                    break;
                case 3:
                    cercarPerTitol(sc, videojocs);
                    break;
                case 4:
                    actualitzarVideojoc(sc, videojocs);
                    break;
                case 5:
                    eliminarVideojoc(sc, videojocs);
                    break;
                case 6:
                    System.out.println("Sortint del programa...");
                    break;
                default:
                    System.out.println("Opció incorrecta. Torna-ho a provar.");
            }
        } while (opcio != 6);

        // En sortir desem tots els canvis i tanquem el Scanner.
        desarVideojocs(videojocs);
        sc.close();
    }

    private static void mostrarMenu() {
        System.out.println("\n--- Menú ---");
        System.out.println("1. Afegir videojoc");
        System.out.println("2. Llistar tots els videojocs");
        System.out.println("3. Cercar videojocs per títol");
        System.out.println("4. Actualitzar un videogoc");
        System.out.println("5. Eliminar un videogoc");
        System.out.println("6. Sortir del programa");
        System.out.print("Tria una opció: ");
    }

    private static int llegirOpcio(Scanner sc) {
        // Comprovem que hi hagi un enter abans de llegir-lo per no llançar l'error.
        if (!sc.hasNextInt()) {
            sc.nextLine();
            System.out.println("Has d'introduir un número.");
            return -1;
        }
        int opcio = sc.nextInt();
        sc.nextLine(); // netejar búfer
        return opcio;
    }

    // Llegeix un enter de l'usuari sense que el programa falli si escriu una lletra.
    private static int llegirInt(Scanner sc, String missatge) {
        while (true) {
            System.out.print(missatge);
            if (sc.hasNextInt()) {
                int valor = sc.nextInt();
                sc.nextLine(); // netejar búfer
                return valor;
            }
            sc.nextLine();
            System.out.println("Has d'introduir un número.");
        }
    }

    // Llegeix un decimal de l'usuari sense que el programa falli si escriu una lletra.
    private static double llegirDouble(Scanner sc, String missatge) {
        while (true) {
            System.out.print(missatge);
            if (sc.hasNextDouble()) {
                double valor = sc.nextDouble();
                sc.nextLine(); // netejar búfer
                return valor;
            }
            sc.nextLine();
            System.out.println("Has d'introduir un número.");
        }
    }

    // ---------- Opcions del menú ----------

    private static void afegirVideojoc(Scanner sc, ArrayList<Videojoc> videojocs) {
        System.out.print("Títol: ");
        String titol = sc.nextLine();
        System.out.print("Gènere: ");
        String genere = sc.nextLine();
        int anyLlanament = llegirInt(sc, "Any de llançament: ");
        System.out.print("Plataforma: ");
        String plataforma = sc.nextLine();
        double preu = llegirDouble(sc, "Preu: ");

        videojocs.add(new Videojoc(titol, genere, anyLlanament, plataforma, preu));
        desarVideojocs(videojocs);
        System.out.println("Videojoc desat correctament.");
    }

    private static void llistarVideojocs(ArrayList<Videojoc> videojocs) {
        if (videojocs.isEmpty()) {
            System.out.println("No hi ha videojocs desats.");
            return;
        }

        System.out.println("--- Llista de videojocs ---");
        for (Videojoc v : videojocs) {
            System.out.println(v);
        }
    }

    private static void cercarPerTitol(Scanner sc, ArrayList<Videojoc> videojocs) {
        System.out.print("Títol a cercar: ");
        String cercat = sc.nextLine().toLowerCase();

        boolean trobat = false;
        for (Videojoc v : videojocs) {
            // Comparació parcial i sense distingir majúscules.
            if (v.getTitol().toLowerCase().contains(cercat)) {
                System.out.println(v);
                trobat = true;
            }
        }

        if (!trobat) {
            System.out.println("No s'ha trobat cap videogoc amb aquest títol.");
        }
    }

    private static void actualitzarVideojoc(Scanner sc, ArrayList<Videojoc> videojocs) {
        System.out.print("Títol del videogoc a actualitzar: ");
        String cercat = sc.nextLine().toLowerCase();

        for (int i = 0; i < videojocs.size(); i++) {
            Videojoc v = videojocs.get(i);

            if (v.getTitol().toLowerCase().contains(cercat)) {
                System.out.println("Dades actuals: " + v);

                System.out.print("Nou títol: ");
                v.setTitol(sc.nextLine());
                System.out.print("Nou gènere: ");
                v.setGenere(sc.nextLine());
                v.setAnyLlanament(llegirInt(sc, "Nou any de llançament: "));
                System.out.print("Nova plataforma: ");
                v.setPlataforma(sc.nextLine());
                v.setPreu(llegirDouble(sc, "Nou preu: "));

                desarVideojocs(videojocs);
                System.out.println("Videojoc actualitzat correctament.");
                return;
            }
        }

        System.out.println("No s'ha trobat cap videogoc amb aquest títol.");
    }

    private static void eliminarVideojoc(Scanner sc, ArrayList<Videojoc> videojocs) {
        System.out.print("Títol del videogoc a eliminar: ");
        String cercat = sc.nextLine().toLowerCase();

        for (int i = 0; i < videojocs.size(); i++) {
            Videojoc v = videojocs.get(i);

            if (v.getTitol().toLowerCase().contains(cercat)) {
                videojocs.remove(i);
                desarVideojocs(videojocs);
                System.out.println("Videojoc eliminat correctament.");
                return;
            }
        }

        System.out.println("No s'ha trobat cap videogoc amb aquest títol.");
    }

    // ---------- Mètodes de persistència ----------

    @SuppressWarnings("unchecked")
    private static ArrayList<Videojoc> carregarVideojocs() {
        ArrayList<Videojoc> videojocs = new ArrayList<>();
        File fitxer = new File(RUTA_FITXER);

        if (fitxer.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fitxer))) {
                // readObject() retorna Object, així que cal fer el cast.
                videojocs = (ArrayList<Videojoc>) ois.readObject();
            } catch (IOException | ClassNotFoundException e) {
                System.err.println("Error carregant els videojocs: " + e.getMessage());
            }
        }

        return videojocs;
    }

    private static void desarVideojocs(ArrayList<Videojoc> videojocs) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(RUTA_FITXER))) {
            oos.writeObject(videojocs);
        } catch (IOException e) {
            System.err.println("Error desant els videojocs: " + e.getMessage());
        }
    }
}

// Classe que volem desar al fitxer binari.
class Videojoc implements Serializable {

    // Controla la compatibilitat entre versions de la classe.
    private static final long serialVersionUID = 1L;

    private String titol;
    private String genere;
    private int anyLlanament;
    private String plataforma;
    private double preu;

    public Videojoc(String titol, String genere, int anyLlanament, String plataforma, double preu) {
        this.titol = titol;
        this.genere = genere;
        this.anyLlanament = anyLlanament;
        this.plataforma = plataforma;
        this.preu = preu;
    }

    public String getTitol() {
        return titol;
    }

    public void setTitol(String titol) {
        this.titol = titol;
    }

    public String getGenere() {
        return genere;
    }

    public void setGenere(String genere) {
        this.genere = genere;
    }

    public int getAnyLlanament() {
        return anyLlanament;
    }

    public void setAnyLlanament(int anyLlanament) {
        this.anyLlanament = anyLlanament;
    }

    public String getPlataforma() {
        return plataforma;
    }

    public void setPlataforma(String plataforma) {
        this.plataforma = plataforma;
    }

    public double getPreu() {
        return preu;
    }

    public void setPreu(double preu) {
        this.preu = preu;
    }

    @Override
    public String toString() {
        return "Títol: " + titol
                + ", Gènere: " + genere
                + ", Any de llançament: " + anyLlanament
                + ", Plataforma: " + plataforma
                + ", Preu: " + preu + " €";
    }
}