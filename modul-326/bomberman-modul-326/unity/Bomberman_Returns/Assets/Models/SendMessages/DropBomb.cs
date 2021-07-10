using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class DropBomb : ClientMessage
{

    private int positionX;

    private int positionY;

    public DropBomb(string playerName) : base(playerName)
    {
    }

    public int PositionX { get => positionX; }

    public int PositionY { get => positionY; }

}
