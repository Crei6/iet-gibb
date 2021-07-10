using System.Collections;
using System.Collections.Generic;

public class PlayerJoined : Message
{

    private string playerName;
    private int initialX;
    private int initialY;

    public PlayerJoined(string playerName, int initialX, int initialY)
    {
        this.playerName = playerName;
        this.initialX = initialX;
        this.initialY = initialY;
    }

    public string PlayerName { get => playerName; }
    public int InitialX { get => initialX; }
    public int InitialY { get => initialY; }
}
