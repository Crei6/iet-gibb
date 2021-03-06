package figuren;

import java.awt.*;

public class Dreieck extends Figur {

    int[] xPoints;
    int[] yPoints;

    public Dreieck(int x, int y, int[] xPoints, int[] yPoints) {
        super(x, y);
        this.xPoints = xPoints;
        this.yPoints = yPoints;
    }

    @Override
    public void zeichne(Graphics g) {
        g.drawPolygon(xPoints, yPoints, 3);
    }
}
