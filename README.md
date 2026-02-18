<h1>Únik z vesmíru</h1>

Krátká textová hra o pilotovi Adamovi, který při jeho vesmírné misi zůstane v rovnoměrném přímočarém pohybu, když proletí kolem silné kosmické bouře. Kvůli tomu se přetíží energetický systém co neví je, že se také vybije a poškodí hlavní baterie na pohon kosmické lodi. Spolu s jeho posádkou musí tento problém vyřešit a dostat se zpět na Zemi.


---

# Mechaniky

- **Pohyb** po mapě
- **Mluvení** s postavy
- **Sbírání** a **pokládání** itemů
- **Použití** předmětu
- **Prozkoumání** místnosti
- **Kombinace** itemů

## Mapa hry
<div align="center">
<img src="spaceshipMap.svg" alt="mapa" width=40%>
</div>

## Příkazy

| příkaz             | popis                                    |
|--------------------|------------------------------------------|
| `jdi <místnost>`   | pohyb mezi propojenými místnostmi        |
| `konec`            | ukončení hry                             |
| `pomoc`            | zobrazení dostupných příkazů             |
| `napoveda`         | nápověda pro postup ve hře               |
| `vezmi <předmět>`  | vzít konkrétní předmět z místnosti       |
| `poloz`            | položit předmět z inventáře              |
| `pouzij <předmět>` | použít předmět v místnosti               |
| `mluv <postava>`   | mluvit s konkrétní postavou              |
| `prozkoumat`       | zjištění informací o místnosti           |
| `zkombinovat`      | zkombinování předmětů do jiného předmětu |

## Systémové požadavky pro kompilaci

- IntelliJ IDEA 2024.2.1
- Oracle OpenJDK 22.0.1
- FasterXML's Jackson 2.17.1
- JUnit 5.9.3 (pro testování)

## Požadavky pro spuštění z konzole

- [Java 22.0.1 (2024-04-16)](https://www.oracle.com/java/technologies/downloads/)

### Lze spustit pomocí:

```batch
chcp 65001
java -jar jackson-demo.jar
```