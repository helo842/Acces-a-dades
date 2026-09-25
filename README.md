# Exercicis d'Accés a Dades — DAM2B

Aquesta carpeta conté els exercicis de l'assignatura d'**Accés a Dades** del cicle formatiu de **Grau Superior en Desenvolupament d'Aplicacions Multipataforma (DAM)**.

## Índex

1. [Estructura dels exercicis](#estructura-dels-exercicis)
2. [Exercici 1: Lectura i anàlisi](#exercici-1-lectura-i-anàlisi)
3. [Exercici 2: Xifrat i desxifrat amb wrappers](#exercici-2-xifrat-i-desxifrat-amb-wrappers)
4. [Execució dels exercicis](#execucio-dels-exercicis)

## Estructura dels exercicis

```text
.
├── README.md
└── RA1/
    ├── Pt1/
    │   ├── Pt1.java
    │   └── text.txt
    └── Pt2/
        ├── Pt2.java
        ├── entrada.txt
        ├── xifrat.txt
        └── desxifrat.txt
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

## Execució dels exercicis

Les ordres s'executen des de l'arrel de la carpeta.

### Compilar els dos exercicis

```bash
mkdir -p .build
javac -encoding UTF-8 -d .build RA1/Pt1/Pt1.java RA1/Pt2/Pt2.java
```

### Executar l'exercici 1

```bash
java -cp .build RA1.Pt1.Pt1
```

### Executar l'exercici 2

```bash
java -cp .build RA1.Pt2.Pt2
```

### Netejar els fitxers compilats

```bash
rm -rf .build
```
