package ch.gibb.bomberman.protocol.server2client;

import ch.gibb.bomberman.network.Message;

public class BombDropped implements Message {
  private String id;
  private int positionX;
  private int positionY;

  public BombDropped(String id, int positionX, int positionY) {
    this.id = id;
    this.positionX = positionX;
    this.positionY = positionY;
  }

  public int getPositionY() {
    return positionY;
  }

  public String getId() {
    return id;
  }

  public int getPositionX() {
    return positionX;
  }
}
