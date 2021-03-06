package figuren;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Gruppe extends Figur {

    private List<Figur> figuren;

    public Gruppe(int x, int y) {
        super(x, y);
        figuren = new ArrayList<>();
    }

    public void hinzufuegen(Figur... f) {
        figuren.addAll(Arrays.asList(f));
    }

    public void loeschen(Figur f) {
        figuren.remove(f);
    }

    @Override
    public void zeichne(Graphics g) {
        for (Figur f : figuren) {
            f.zeichne(g);
        }
    }

    @Override
    public void move(int addX, int addY) {
        x += addX;
        y += addY;
        for (Figur f : figuren) {
            f.move(addX, addY);
        }
    }
}
