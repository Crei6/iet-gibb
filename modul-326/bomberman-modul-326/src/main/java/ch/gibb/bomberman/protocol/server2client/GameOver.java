package ch.gibb.bomberman.protocol.server2client;

import ch.gibb.bomberman.network.Message;

public class GameOver implements Message {
  private String winnerName;
  private String[] highscoreList;

  public GameOver(String winnerName, String[] highscoreList) {
    this.winnerName = winnerName;
    this.highscoreList = highscoreList;
  }

  public String getWinnerName() {
    return winnerName;
  }

  public String[] getHighscoreList() {
    return highscoreList;
  }
}
