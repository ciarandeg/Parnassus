package ui.graphics.buttons;

import java.awt.event.MouseEvent;

public class LoadButtonListener extends ParnassusButtonListener {
    public LoadButtonListener() {
        super();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.println("foo");
    }
}
