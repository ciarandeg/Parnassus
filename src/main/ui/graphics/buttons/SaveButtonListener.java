package ui.graphics.buttons;

import java.awt.event.MouseEvent;

public class SaveButtonListener extends ParnassusButtonListener {
    public SaveButtonListener() {
        super();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.println("bar");
    }
}
