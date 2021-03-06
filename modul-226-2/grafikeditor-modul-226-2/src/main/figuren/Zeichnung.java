package figuren;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Zeichnung {

    private List<Figur> figuren;

    public Zeichnung() {
        this.figuren = new ArrayList<>();
    }

    public List<Figur> getFiguren() {
        return figuren;
    }

    public void hinzufuegen(Figur... f) {
        figuren.addAll(Arrays.asList(f));
    }

    public void hinzufuegen(List<Figur> f) {
        figuren.addAll(f);
    }

    public void zeichneFiguren(Graphics g) {
        for (Figur f : figuren) {
            f.zeichne(g);
        }
    }

    public void moveFiguren(int newX, int newY) {
        for (Figur f : figuren) {
            f.move(newX, newY);
        }
    }
}
