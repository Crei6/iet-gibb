package ch.gibb.bomberman.server;

import ch.gibb.bomberman.network.Message;
import ch.gibb.bomberman.network.client.ClientApplicationInterface;
import ch.gibb.bomberman.network.client.ServerProxy;
import ch.gibb.bomberman.protocol.client2server.JoinGame;
import ch.gibb.bomberman.protocol.server2client.PlayerJoined;

/**
 * Die Klasse ServerProxyStub ist von ServerProxy abgeleitet und dient dazu den Client
 * isoliert von der Netzwerkschicht testen zu können. Hierzu muss der Client ein Objekt
 * von dieser Klasse erzeugen. Er kann dann die send-Methode aufrufen. Damit kann das 
 * Senden einer Meldung an den Server simuliert werden. Die Meldung wird lediglich auf
 * der Konsole ausgegeben. Die send-Methode kann den Testbedürfnissen entsprechend
 * angepasst werden. Insbesondere können aus der send-Methode heraus Antworten vom 
 * Server simuliert werden. Hierzu steht die Methode deliverResponseMessageToClient
 * zur Verfügung. Diese liefert eine beliebige Meldung an den Client aus und benützt
 * dafür einen separaten Thread, so wie dies auch mit der richtigen Netzwerkschicht
 * der Fall sein wird.
 * 
 * @author Andres Scheidegger
 *
 */
public class ServerProxyStub extends ServerProxy {

  /**
   * Konstruktor. Muss vom Client aufgerufen werden.
   * @param clientApplication Eine Referenz auf ein Objekt des Clients, welches das
   *                          Interface ClientApplicationInterface implementiert.
   */
  public ServerProxyStub(ClientApplicationInterface clientApplication) {
    super(clientApplication);
  }


  @Override
  public void send(Message message) {
    System.out.println(message);
    // Hier kommt die Antwort. Z.B.:
    // PlayerJoined playerJoinedMessage = new PlayerJoined("Donald Duck", 0, 0);
    // deliverResponseMessageToClient(playerJoinedMessage);
    if (message instanceof JoinGame) {
      JoinGame joinGame = (JoinGame)message;
      System.out.println(joinGame.getPlayerName());
      PlayerJoined response = new PlayerJoined(joinGame.getPlayerName(), 0, 0);
      deliverResponseMessageToClient(response);
    }
  }

  /**
   * Simuliert den Empfang einer Meldeung vom Server über das Netzwerk.
   * @param responseMessage Das Meldungsobjekt, welches an den Client ausgeliefert
   *                        werden soll.
   */
  private void deliverResponseMessageToClient(final Message responseMessage) {
    Thread responseThread = new Thread() {
      @Override
      public void run() {
        clientApplication.handleMessage(responseMessage);
      }
    };
    responseThread.start();
  }
}
