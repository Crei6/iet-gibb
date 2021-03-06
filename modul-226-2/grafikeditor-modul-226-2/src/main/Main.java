import figuren.Display;
import figuren.Gruppe;
import figuren.Kreis;
import figuren.Rechteck;

public class Main {

    private static final Display display = new Display();

    public static void main(String[] args) {
//        display.hinzufuegen(new figuren.Rechteck(5, 5, 100, 120));
//        display.hinzufuegen(new figuren.Kreis(5, 5, 100));
//        display.hinzufuegen(new figuren.Linie(5, 5, 100, 120));

//        figuren.Figur[] figuren = new figuren.Figur[5];
//        figuren[0] = new figuren.Rechteck(10, 10, 10, 10);
//        figuren[1] = new figuren.Kreis(30, 10, 10);
//        figuren[2] = new figuren.Linie(10, 20, 10, 10);
//        figuren[3] = new figuren.Rechteck(10, 10, 10, 10);
//        figuren[4] = new figuren.Rechteck(10, 10, 10, 10);
//
//        save.FigurSaver figurSaver = new save.FigurSaver();
//        save.FigurLoader figurLoader = new save.FigurLoader();
//
//        figurSaver.saveFiguren(figuren);
//        figuren = figurLoader.loadFiguren();
//        for (figuren.Figur f : figuren) {
//            System.out.println(f.toString());
//        }

        Gruppe g = new Gruppe(10, 10);
        g.hinzufuegen(
                new Rechteck(10, 10, 20, 30),
                new Kreis(50, 50, 20)
        );

        g.move(300, 50);

        display.hinzufuegen(g);
    }

}
