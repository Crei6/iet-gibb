using System.Collections;
using System.Collections.Generic;

public class GameOver : Message
{

    private string winnerName;
    private string[] highscoreList;

    public GameOver(string winnerName, string[] highscoreList)
    {
        this.winnerName = winnerName;
        this.highscoreList = highscoreList;
    }

    public string WinnerName { get => winnerName; }
    public string[] HighscoreList { get => highscoreList; }
}
