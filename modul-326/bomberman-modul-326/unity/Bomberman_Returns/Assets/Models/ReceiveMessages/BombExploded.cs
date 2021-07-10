using System.Collections;
using System.Collections.Generic;

public class BombExploded : Message
{

    private string id;

    public BombExploded(string id)
    {
        this.id = id;
    }

    public string Id { get => id; set => id = value; }

}
