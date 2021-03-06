package save;

import figuren.Figur;
import figuren.Kreis;
import figuren.Linie;
import figuren.Rechteck;

import java.io.FileWriter;

public class FigurSaver {

    public void saveFiguren(Figur[] figuren) {
        try {
            FileWriter writer = new FileWriter("figuren.txt");

            StringBuilder sb = new StringBuilder();
            for (Figur f : figuren) {
                if (f instanceof Rechteck) {
                    sb.append("figuren.Rechteck ").append(f.toString());
                } else if (f instanceof Kreis) {
                    sb.append("figuren.Kreis ").append(f.toString());
                } else if (f instanceof Linie) {
                    sb.append("figuren.Linie ").append(f.toString());
                }
                sb.append("\n");
            }
            writer.write(sb.toString());
            writer.close();
        } catch (Exception ignored) { }
    }

}
