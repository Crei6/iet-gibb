package ab08;

import figuren.Kreis;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class KreisTest {

    private GraphicsStub g = new GraphicsStub();

    @Test
    public void testZeichne() {
        Kreis k = new Kreis(10, 10, 20);
        k.zeichne(g);

        assertEquals(1, g.nbOfDrawOvalCalls);
        assertEquals(-10, g.x);
        assertEquals(-10, g.y);
        assertEquals(40, g.width);
        assertEquals(40, g.height);
    }

}
