package ch.gibb.bomberman.protocol.client2server;

import ch.gibb.bomberman.protocol.Direction;

public class MovePlayer extends ClientMessage {
  private Direction direction;

  public MovePlayer(String playerName, Direction direction) {
    super(playerName);
    this.direction = direction;
  }

  public Direction getDirection() {
    return direction;
  }
}
