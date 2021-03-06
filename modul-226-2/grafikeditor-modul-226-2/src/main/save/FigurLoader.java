package save;

import figuren.Figur;
import figuren.Kreis;
import figuren.Linie;
import figuren.Rechteck;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FigurLoader {

    public Figur[] loadFiguren() {
        List<Figur> figuren = new ArrayList<Figur>();
        try {
            File file = new File("figuren.txt");
            Scanner reader = new Scanner(file);

            while (reader.hasNextLine()) {
                String[] data = reader.nextLine().split(" ");
                switch (data[0]) {
                    case "figuren.Rechteck" -> figuren.add(new Rechteck(Integer.parseInt(data[1]), Integer.parseInt(data[2]),
                            Integer.parseInt(data[3]), Integer.parseInt(data[4])));
                    case "figuren.Kreis" -> figuren.add(new Kreis(Integer.parseInt(data[1]), Integer.parseInt(data[2]),
                            Integer.parseInt(data[3])));
                    case "figuren.Linie" -> figuren.add(new Linie(Integer.parseInt(data[1]), Integer.parseInt(data[2]),
                            Integer.parseInt(data[3]), Integer.parseInt(data[4])));
                }
            }
            reader.close();
        } catch (Exception ignored) { }

        Figur[] figurenArr = new Figur[figuren.size()];
        for (int i = 0; i < figurenArr.length; i++) {
            figurenArr[i] = figuren.get(i);
        }

        return figurenArr;
    }

}
