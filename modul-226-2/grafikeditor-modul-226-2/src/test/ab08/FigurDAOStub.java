package ab08;

public class FigurDAOStub implements FigurDAO {

    private String[] testFiguren = {
            "Kreis,10,10,20",
            "Rechteck,10,10,50,70",

            "Linie,10,10,40,40"
    };

    private int currentFigur = 0;

    @Override
    public String[] readNextFigurData() {
        if (currentFigur < testFiguren.length) {
            String[] figurData = testFiguren[currentFigur].split(",");
            currentFigur++;
            return figurData;
        }
        return null;
    }
}
