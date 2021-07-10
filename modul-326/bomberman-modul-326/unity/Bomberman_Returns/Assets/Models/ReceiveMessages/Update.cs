using System.Collections;
using System.Collections.Generic;

public class Update : Message
{

    private char[][] map;

    public Update(char[][] map)
    {
        this.map = map;
    }

    public char[][] Map { get => map; }

}
