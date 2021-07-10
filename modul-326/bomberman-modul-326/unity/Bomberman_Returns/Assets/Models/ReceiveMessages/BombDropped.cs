using System.Collections;
using System.Collections.Generic;

public class BombDropped : Message
{

    private string id;
    private int initialX;
    private int initialY;

    public BombDropped(string id, int initialX, int initialY)
    {
        this.id = id;
        this.initialX = initialX;
        this.initialY = initialY;
    }

    public string Id { get => id; }

    public int InitialX { get => initialX; }

    public int InitialY { get => initialY; }

}
