package ch.gibb.bomberman.protocol.server2client;

import ch.gibb.bomberman.network.Message;

/**
 * Diese Meldung wird vom Server an alle bereits im Spiel angemeldeten Spieler gesendet,
 * sobald sich ein neuer Spieler angemeldet hat und die nötige Anzahl der Spieler noch
 * nicht überschritten ist. Diese Meldung wird auch zur Bestätigung an den neu angemeldeten
 * Spieler gesendet.
 * Die Meldung enthält den Spielernamen des neu angemeldeten Spielers und seine
 * Startposition auf dem Spielfeld.
 * 
 * @author Andres Scheidegger
 *
 */
public class PlayerJoined implements Message {
  private String playerName;
  private int initialX;
  private int initialY;

  public PlayerJoined(String playerName, int initialX, int initialY) {
    this.playerName = playerName;
    this.initialX = initialX;
    this.initialY = initialY;
  }

  public String getPlayerName() {
    return playerName;
  }
  public int getInitialPositionX() {
    return initialX;
  }

  public int getInitialPositionY() {
    return initialY;
  }
}
