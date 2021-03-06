package editor;

import figuren.*;

import java.awt.Graphics;
import java.awt.Point;

final class EditorControl {

  private Zeichnung zeichnung = new Zeichnung();
  private char figurTyp;
  private Point ersterPunkt;
  private Point zweiterPunkt;

  private EditorFrame editorFrame;

  public EditorControl(EditorFrame editorFrame) {
    this.editorFrame = editorFrame;
    figurTyp = 'r';
  }

  public void allesNeuZeichnen(Graphics g) {
    zeichnung.zeichneFiguren(g);
  }

  public void setFigurTyp(char figurTyp) {
    this.figurTyp = figurTyp;
  }

  public void setErsterPunkt(Point ersterPunkt) {
    this.ersterPunkt = ersterPunkt;
  }

  public void erzeugeFigurMitZweitemPunkt(Point zweiterPunkt) {
    this.zweiterPunkt = zweiterPunkt;
    switch (figurTyp) {
      case 'r' -> erzeugeRechteck();
      case 'k' -> erzeugeKreis();
      case 'l' -> erzeugeLinie();
      case 'd' -> erzeugeDreieck();
    }
    allesNeuZeichnen(editorFrame.getGraphics());
  }

  private void erzeugeRechteck() {
    int breite = zweiterPunkt.x - ersterPunkt.x;
    int hoehe = zweiterPunkt.y - ersterPunkt.y;

    zeichnung.hinzufuegen(new Rechteck(
            Math.min(ersterPunkt.x, zweiterPunkt.x),
            Math.min(ersterPunkt.y, zweiterPunkt.y),
            Math.abs(breite),
            Math.abs(hoehe))
    );
  }

  private void erzeugeKreis() {
    zeichnung.hinzufuegen(new Kreis(
            ersterPunkt.x,
            zweiterPunkt.y,
            (int) Math.sqrt((ersterPunkt.y - zweiterPunkt.y) * (ersterPunkt.y - zweiterPunkt.y)
                    + (ersterPunkt.x - zweiterPunkt.x) * (ersterPunkt.x - zweiterPunkt.x)) / 2
    ));
  }

  private void erzeugeLinie() {
    zeichnung.hinzufuegen(new Linie(
            ersterPunkt.x,
            ersterPunkt.y,
            zweiterPunkt.x,
            zweiterPunkt.y
    ));
  }

  private void erzeugeDreieck() {
    zeichnung.hinzufuegen(new Dreieck(
            ersterPunkt.x,
            ersterPunkt.y,
            new int[]{ersterPunkt.x, ersterPunkt.x, zweiterPunkt.x},
            new int[]{ersterPunkt.y, zweiterPunkt.y, zweiterPunkt.y}
    ));
  }
}
