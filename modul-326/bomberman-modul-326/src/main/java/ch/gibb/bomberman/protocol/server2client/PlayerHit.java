package ch.gibb.bomberman.protocol.server2client;

import ch.gibb.bomberman.network.Message;

public class PlayerHit implements Message {
  private String playerName;

  public PlayerHit(String playerName) {
    this.playerName = playerName;
  }

  public String getPlayerName() {
    return playerName;
  }
}
