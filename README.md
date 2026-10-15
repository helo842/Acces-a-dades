# Exercicis d'Accés a Dades — DAM2B

Aquesta carpeta conté els exercicis de l'assignatura d'**Accés a Dades** del cicle formatiu de **Grau Superior en Desenvolupament d'Aplicacions Multipataforma (DAM)**.

## Índex

1. [Estructura dels exercicis](#estructura-dels-exercicis)
2. [Exercici 1: Lectura i anàlisi](#exercici-1-lectura-i-anàlisi)
3. [Exercici 2: Xifrat i desxifrat amb wrappers](#exercici-2-xifrat-i-desxifrat-amb-wrappers)
4. [Exercici 3: Gestió de videojocs amb serialització](#exercici-3-gestio-de-videojocs-amb-serialitzacio)
5. [Exercici 4: Generació i lectura d'un XML](#exercici-4-generacio-i-lectura-dun-xml)
6. [Execució dels exercicis](#execucio-dels-exercicis)

## Estructura dels exercicis

```text
.
├── README.md
└── RA1/
    ├── Pt1/
    │   ├── Pt1.java
    │   └── text.txt
    ├── Pt2/
    │   ├── Pt2.java
    │   ├── entrada.txt
    │   ├── xifrat.txt
    │   └── desxifrat.txt
    ├── Pt3/
    │   ├── Pt3.java
    │   └── videojocs.dat
    └── Pt4/
        ├── Pt4.java
        └── zoo.xml
```

## Exercici 1: Lectura i anàlisi

El codi està a `RA1/Pt1/Pt1.java` i utilitza `RA1/Pt1/text.txt` com a fitxer d'entrada.

L'aplicació llegeix el fitxer caràcter a caràcter amb `FileReader` i mostra:

- El nombre total de caràcters, sense comptar els salts de línia.
- El nombre total de línies.
- El nombre total de paraules.
- El caràcter que apareix més vegades.
- Els espais i les tabulacions com a caràcters vàlids.
- Les majúscules i minúscules com a caràcters diferents.

Els espais, les tabulacions i els salts de línia no s'utilitzen per trobar el caràcter més repetit. La lectura es realitza en una sola passada.

El codi utilitza:

- `StandardCharsets.UTF_8` per treballar amb accents i caràcters especials.
- `try-with-resources` per tancar el fitxer automàticament.
- `FileNotFoundException`, `IOException` i `SecurityException` per gestionar els errors d'accés i lectura.

Sortida amb el fitxer actual:

```text
Nombre de caràcters: 30
Nombre de línies: 2
Nombre de paraules: 5
Caràcter més repetit: e
```

## Exercici 2: Xifrat i desxifrat amb wrappers

El codi està a `RA1/Pt2/Pt2.java`.

L'aplicació utilitza `RA1/Pt2/entrada.txt` com a fitxer d'entrada i crea els fitxers `xifrat.txt` i `desxifrat.txt`.

### Xifrat

1. Llegeix cada línia amb `BufferedReader`.
2. Inverteix la línia.
3. Aplica un xifrat Cèsar amb una clau fixa de 3.
4. Escriu el resultat amb `BufferedWriter`.

Per exemple, la línia:

```text
Hola món
```

es transforma en:

```text
nóm aloH
```

abans d'aplicar el desplaçament de la clau.

### Desxifrat

1. Llegeix cada línia de `xifrat.txt`.
2. Aplica el desplaçament invers de la clau.
3. Torna a invertir la línia.
4. Escriu el resultat a `desxifrat.txt`.

El resultat final ha de coincidir amb `entrada.txt`.

### Wrappers utilitzats

- `BufferedReader` envolupa el fluxe `FileInputStream` i permet llegir línies mitjançant `readLine()`.
- `BufferedWriter` envolupa el fluxe `FileOutputStream` i permet escriure mitjançant `write()` i `newLine()`.
- `InputStreamReader` i `OutputStreamWriter` utilitzen UTF-8.
- `try-with-resources` tanca tots els fluxos automàticament.

L'aplicació mostra un missatge per cada línia processada i missatges en acabar el xifrat i el desxifrat.

## Exercici 3: Gestió de videojocs amb serialització

El codi està a `RA1/Pt3/Pt3.java`.

Aquesta pràctica implementa un petit programa de gestió de videojocs mitjançant una llista en memòria i persistència en fitxer binari. La classe `Videojoc` és serializable i es guarda en un fitxer `RA1/Pt3/videojocs.dat`.

### Funcionalitats

- Afegir un videojoc.
- Llistar tots els videojocs guardats.
- Cercar videojocs per títol.
- Actualitzar les dades d'un videojoc.
- Eliminar un videojoc.
- Sortir del programa i guardar automàticament els canvis.

### Tecnologies utilitzades

- `ArrayList` per guardar la col·lecció de videojocs.
- `Serializable` per guardar objectes en disc.
- `ObjectInputStream` i `ObjectOutputStream` per llegir i escriure el fitxer binari.
- `Scanner` per interactuar amb l'usuari a través del menú.

L'aplicació mostra un menú textual i conserva les dades entre execucions gràcies a la persistència en fitxer.

## Exercici 4: Generació i lectura d'un XML

El codi està a `RA1/Pt4/Pt4.java` i genera el fitxer `RA1/Pt4/zoo.xml`.

Aquesta pràctica crea un document XML amb un element `Zoo` i un arbre d'animals. Es crea l'element `animal` amb l'atribut `id`, i dins d'aquest hi ha les dades del nom, espècie, aliment i edat.

### Procés que realitza

1. Crea un `Document` buit amb `DocumentBuilderFactory` i `DocumentBuilder`.
2. Genera l'estructura XML amb `Element` i `setTextContent()`.
3. Guarda el document al fitxer `zoo.xml` amb `Transformer`.
4. Torna a llegir el mateix document amb `DocumentBuilder.parse()`.
5. Recorre els elements `animal` i mostra per pantalla les dades llegides.

### Estructura XML creada

```xml
<?xml version="1.0" encoding="UTF-8" standalone="no"?>
<Zoo>
    <animal id="01">
        <nom>Pinguí</nom>
        <especie>Apetrodytes forsteri</especie>
        <aliment>Pescado</aliment>
        <edat>20</edat>
    </animal>
</Zoo>
```

Es tracta d'una pràctica introductòria al tractament de documents XML amb Java, utilitzant `DOM` per construir i llegir l'arbre de nodes.

## Execució dels exercicis

Les ordres s'executen des de l'arrel de la carpeta.

### Compilar els exercicis

```bash
mkdir -p .build
javac -encoding UTF-8 -d .build \
  RA1/Pt1/Pt1.java \
  RA1/Pt2/Pt2.java \
  RA1/Pt3/Pt3.java \
  RA1/Pt4/Pt4.java
```

### Executar l'exercici 1

```bash
java -cp .build RA1.Pt1.Pt1
```

### Executar l'exercici 2

```bash
java -cp .build RA1.Pt2.Pt2
```

### Executar l'exercici 3

```bash
java -cp .build RA1.Pt3.Pt3
```

### Executar l'exercici 4

```bash
java -cp .build Pt4
```

### Netejar els fitxers compilats

```bash
rm -rf .build
```
