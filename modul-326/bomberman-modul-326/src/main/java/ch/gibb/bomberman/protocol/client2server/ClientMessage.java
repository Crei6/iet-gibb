package ch.gibb.bomberman.protocol.client2server;

import ch.gibb.bomberman.network.Message;

/**
 * ClientMessage ist die Basisklasse aller Nachrichten, welche von den Clients an den Server
 * gesendet werden. Ein ClientMessage-Objekt enthält als Identifikation des sendenden Clients
 * den Playernamen.
 * 
 * @author Andres Scheidegger
 *
 */
public abstract class ClientMessage implements Message {
  private String playerName;
  public ClientMessage(String playerName) {
    this.playerName = playerName;
  }

  public String getPlayerName() {
    return playerName;
  }
}
