package ab08;

import figuren.*;
import org.junit.*;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class FigurParserTest {

    private FigurDAO dao;
    private FigurParser fp;

    @Before
    public void setup() {
        dao = new FigurDAOStub();
        fp = new FigurParser(dao);
    }

    @Test
    public void test() {
        List<Figur> figuren = fp.parse();

        assertEquals(3, figuren.size());
        assertEquals("Rechteck: 10 10 50 70", figuren.get(1).toString());
        assertEquals("Kreis: 10 10 20", figuren.get(0).toString());
        assertEquals("Linie: 10 10 40 40", figuren.get(2).toString());
    }



}
