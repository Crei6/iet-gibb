package ch.gibb.bomberman.protocol.server2client;

import ch.gibb.bomberman.network.Message;
import ch.gibb.bomberman.protocol.Direction;

public class PlayerMoved implements Message {
  private String playerName;
  private Direction direction;

  public PlayerMoved(String playerName, Direction direction) {
    this.playerName = playerName;
    this.direction = direction;
  }

  public String getPlayerName() {
    return playerName;
  }

  public Direction getDirection() {
    return direction;
  }
}
