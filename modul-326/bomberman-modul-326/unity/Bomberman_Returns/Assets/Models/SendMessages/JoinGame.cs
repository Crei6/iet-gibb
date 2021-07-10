using System.Collections;
using System.Collections.Generic;

public class JoinGame : ClientMessage
{
    public JoinGame(string playerName) : base(playerName)
    {
    }

}
