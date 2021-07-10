package ch.gibb.bomberman.protocol.server2client;

import ch.gibb.bomberman.network.Message;

public class BombExploded implements Message {
  private String id;

  public BombExploded(String id) {
    this.id = id;
  }

  public String getId() {
    return id;
  }
}
