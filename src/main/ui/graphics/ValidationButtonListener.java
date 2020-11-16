package ui.graphics;

import ui.graphics.ParnassusButtonListener;

import java.awt.event.MouseEvent;

public class ValidationButtonListener extends ParnassusButtonListener {
    public ValidationButtonListener() {
        super();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.println("baz");
    }
}
