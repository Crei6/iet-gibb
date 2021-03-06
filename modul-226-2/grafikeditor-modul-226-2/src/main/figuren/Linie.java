package figuren;

import java.awt.*;

public class Linie extends Figur {

    private int endX;
    private int endY;

    public Linie(int x, int y, int endX, int endY) {
        super(x, y);
        this.endX = endX;
        this.endY = endY;
    }

    public int getEndX() {
        return endX;
    }

    public int getEndY() {
        return endY;
    }

    @Override
    public String toString() {
        return "Linie: " + x + " " + y + " " + endX + " " + endY;
    }

    @Override
    public void zeichne(Graphics g) {
        g.drawLine(x, y, endX, endY);
    }

    @Override
    public void move(int addX, int addY) {
        x += addX;
        endX += addX;
        y += addY;
        endY += addY;
    }
}
