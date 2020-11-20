package ui.graphics;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

// A MouseListener that ignores all actions other than a click
public abstract class ParnassusButtonListener implements MouseListener {
    @Override
    public abstract void mouseClicked(MouseEvent e);

    @Override
    public void mousePressed(MouseEvent e) {
        // do nothing
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // do nothing
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // do nothing
    }

    @Override
    public void mouseExited(MouseEvent e) {
        // do nothing
    }
}
