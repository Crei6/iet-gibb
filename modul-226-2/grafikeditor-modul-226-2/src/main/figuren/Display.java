package figuren;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * Die Klasse figuren.Display stellt ein Fenster auf dem Bildschirm zur Verf�gung, in welchem
 * figuren.Figur-Objekte dargestellt werden k�nnen.
 * Siehe auch Java-Grundkurs Abschnitt 10.2 und 10.3.
 * 
 * @author Andres Scheidegger
 */
@SuppressWarnings("serial")
public class Display extends JFrame {
  /** Die Liste der dargestellten figuren.Figur-Objekte */
  private List<Figur> figuren = new ArrayList<Figur>();

  /**
   * Konstruktor. Initialisiert das Fenster in der Mitte des Bildschirms und erzeugt ein
   * JFrame-Objekt, auf welchem die Figuren gezeichnet werden.
   */
  public Display() {
    Dimension windowSize = new Dimension(800, 600);
    setSize(windowSize);
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    int windowPositionX = (screenSize.width - windowSize.width) / 2;
    int windowPositionY = (screenSize.height - windowSize.height) / 2;
    Point windowPosition = new Point(windowPositionX, windowPositionY);
    setLocation(windowPosition);
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    createAndAddDrawingPanel();
    setVisible(true);
  }

  private void createAndAddDrawingPanel() {
    // Das JPanel-Objekt ist ein Objekt einer anonymen Unterklasse von JPanel
    // Siehe Java-Grundkurs Abschnitt 3.9
    add(new JPanel() {
      // Die paintComponent()-Methode wird automatisch aufgerufen, wenn irgendwer die repaint()-
      // Methode beim Dsiplay aufruft.
      @Override
      protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        zeichneFiguren(g);
      }
    });
  }

  /**
   * Zeichnet alle Figuren.
   * @param g Referenz auf das Graphics-Objekt zum zeichnen.
   */
  private void zeichneFiguren(Graphics g) {
    Zeichnung zeichnung = new Zeichnung();
    zeichnung.hinzufuegen(figuren);
    zeichnung.zeichneFiguren(g);
  }

  /**
   * F�gt eine weitere figuren.Figur hinzu und l�st die Auffrischung des Fensterinhaltes aus.
   * @param figur Referenz auf das weitere figuren.Figur-Objekt.
   */
  public void hinzufuegen(Figur figur) {
    figuren.add(figur);
    repaint();
  }

  /**
   * L�scht alle Figuren und l�st die Auffrischung des Fensterinhaltes aus.
   */
  public void allesLoeschen() {
    figuren.clear();
    repaint();
  }
}
