package ch.gibb.bomberman.protocol.client2server;

/**
 * Diese Meldung wird vom Client an den Server gesendet, wenn sich ein neuer Spieler anmeldet.
 * Die Meldung enthält den Namen des Spielers.
 * 
 * @author Andres Scheidegger
 *
 */
public class JoinGame extends ClientMessage {
  public JoinGame(String playerName) {
    super(playerName);
  }
}
