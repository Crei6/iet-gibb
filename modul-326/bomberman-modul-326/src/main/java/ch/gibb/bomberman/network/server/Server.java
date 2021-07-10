package ch.gibb.bomberman.network.server;

import ch.gibb.bomberman.network.Message;

/**
 * Diese Klasse definiert die Schnittstelle, welche die Netzwerkschicht der Bomberman-Server-Komponente
 * anbietet. Die Klasse ist abstrakt und muss innerhalb der Netzwerkschicht durch Ableitung implementiert
 * werden. Die Bomberman-Server-Komponente muss beim Start ein Objekt von dieser Implementationsklasse
 * erzeugen.
 * 
 * @author Andres Scheidegger
 *
 */
public abstract class Server {
  /**
   * Referenz auf dasjenige Objekt innerhalb der Bomberman-Server-Komponente, welches von den Clients
   * empfangene Nachrichten verarbeitet (Empfängerobjekt).
   */
  protected final ServerApplicationInterface serverApplication;
  
  /**
   * Konstruktor. Initialisiert das neue Server-Objekt mit der Referenz auf das Empfängerobjekt.
   * @param serverApplication Das Empfängerobjekt des Bomberman-Servers für Nachrichten.
   */
  public Server(ServerApplicationInterface serverApplication) {
    this.serverApplication = serverApplication;
  }
  
  /**
   * Sendet eine Nachricht an einen bestimmten Client. Diese Methode muss innerhalb der Netzwerkschicht
   * implementiert werden.
   * @param message Das Nachrichtenobjekt, welches versendet werden soll.
   * @param connectionId Die ID der Netzwerkverbindung zum Client.
   */
  public abstract void send(Message message, String connectionId);
  
  /**
   * Sendet eine Nachricht an alle Clients. Diese Methode muss innerhalb der Netzwerkschicht
   * implementiert werden.
   * @param message Das Nachrichtenobjekt, welches versendet werden soll.
   */
  public abstract void broadcast(Message message);
}
