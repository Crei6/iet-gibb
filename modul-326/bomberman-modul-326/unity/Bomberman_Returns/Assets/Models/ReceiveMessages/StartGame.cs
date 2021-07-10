using System.Collections;
using System.Collections.Generic;

public class StartGame : Message
{

    private char[][] map;

    public StartGame(char[][] map)
    {
        this.map = map;
    }

    public char[][] Map { get => map; }

}
