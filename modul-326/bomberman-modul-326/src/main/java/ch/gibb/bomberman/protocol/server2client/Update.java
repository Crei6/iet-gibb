package ch.gibb.bomberman.protocol.server2client;

import ch.gibb.bomberman.network.Message;

public class Update implements Message {
  private char[][] map;

  public Update(char[][] map) {
    this.map = map;
  }

  public char[][] getMap() {
    return map;
  }
}
