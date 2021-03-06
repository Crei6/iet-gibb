package ab08;

public class GraphicsStub extends ConcreteGraphics {

    int nbOfDrawOvalCalls;
    int x;
    int y;
    int width;
    int height;

    @Override
    public void drawOval(int x, int y, int width, int height) {
        nbOfDrawOvalCalls++;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }
}
