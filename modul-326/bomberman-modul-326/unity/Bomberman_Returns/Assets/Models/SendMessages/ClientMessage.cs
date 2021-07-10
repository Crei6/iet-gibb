using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public abstract class ClientMessage : Message
{
    private string playerName;

    public ClientMessage(string playerName)
    {
        this.playerName = playerName;
    }

    public string PlayerName { get => playerName; }

}
