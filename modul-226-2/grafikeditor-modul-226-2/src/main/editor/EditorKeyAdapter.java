package editor;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class EditorKeyAdapter extends KeyAdapter {

    private EditorControl editorControl;

    public EditorKeyAdapter(EditorControl editorControl) {
        this.editorControl = editorControl;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        editorControl.setFigurTyp(e.getKeyChar());
    }
}
