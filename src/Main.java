import game.Game;

/**
 * <b>Únik z vesmíru</b>
 * <br>
 * Krátká hra o pilotovi Adamovi,
 * <br>
 * který při jeho vesmírné misi
 * <br>
 * zůstane v rovnoměrném přímočarém pohybu,
 * <br>
 * když proletí kolem silné kosmické bouře.
 * <br><br>
 * Kvůli tomu se přetíží energetický systém
 * <br>
 * co neví je, že se také vybije a poškodí
 * <br>
 * hlavní baterie na pohon kosmické lodi.
 * <br><br>
 * Spolu s jeho posádkou musí tento problém
 * <br>
 * vyřešit a dostat se zpět na Zemi.
 * @author Matouš Hruška, C2b
 * @version 0.1
 */
public class Main {
    public static void main(String[] args) {
        Game game = new Game();
        game.play();
    }
}