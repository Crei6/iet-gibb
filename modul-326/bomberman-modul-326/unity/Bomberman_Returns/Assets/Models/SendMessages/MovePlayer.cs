using System.Collections;
using System.Collections.Generic;

[System.Serializable]
public class MovePlayer : ClientMessage
{
    public Direction direction;

    public MovePlayer(string playerName, Direction direction) : base(playerName)
    {
        this.direction = direction;
    }

    public Direction Direction { get => direction; }
}
