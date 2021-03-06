package ab08;

import figuren.*;
import org.junit.Test;

import java.awt.*;

import static org.mockito.Mockito.*;

public class ZeichnungTest {

    private Graphics g = mock(Graphics.class);

    @Test
    public void testZeichneFiguren() {
        Zeichnung zeichnung = new Zeichnung();
        zeichnung.hinzufuegen(
                new Rechteck(10, 10, 30, 50),
                new Kreis(10, 10, 20),
                new Linie(10, 10, 40, 40),
                new Linie(20, 20, 50, 50)
        );

        zeichnung.zeichneFiguren(g);
        verify(g, times(1)).drawRect(10, 10, 30, 50);
        verify(g, times(1)).drawOval(-10, -10, 40, 40);
        verify(g, times(1)).drawLine(10, 10, 40, 40);
        verify(g, times(1)).drawLine(20, 20, 50, 50);

    }

}
