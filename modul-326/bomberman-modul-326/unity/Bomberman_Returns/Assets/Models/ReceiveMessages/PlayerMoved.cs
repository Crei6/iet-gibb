using System.Collections;
using System.Collections.Generic;

public class PlayerMoved : Message
{
    private string playerName;

    private Direction direction;

    public PlayerMoved(string playerName, Direction direction)
    {
        this.playerName = playerName;
        this.direction = direction;
    }

    public Direction Direction { get => direction; }
    public string PlayerName { get => playerName; }

}
