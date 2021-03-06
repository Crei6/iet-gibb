package figuren;

import java.awt.*;

public abstract class Figur {

    protected int x;
    protected int y;

    public Figur(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public String toString() {
        return x + " " + y;
    }

    public abstract void zeichne(Graphics g);

    public void move(int addX, int addY) {
        x += addX;
        y += addY;
    }
}
