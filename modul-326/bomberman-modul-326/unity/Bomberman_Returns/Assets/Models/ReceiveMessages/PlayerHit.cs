using System.Collections;
using System.Collections.Generic;

public class PlayerHit : Message
{

    private string playerName;

    public PlayerHit(string playerName)
    {
        this.playerName = playerName;
    }

    public string PlayerName { get => playerName; }

}
