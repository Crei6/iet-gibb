package editor;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class EditorMouseAdapter extends MouseAdapter {

    private EditorControl editorControl;

    public EditorMouseAdapter(EditorControl editorControl) {
        this.editorControl = editorControl;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        editorControl.setErsterPunkt(e.getPoint());
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        editorControl.erzeugeFigurMitZweitemPunkt(e.getPoint());
    }
}
