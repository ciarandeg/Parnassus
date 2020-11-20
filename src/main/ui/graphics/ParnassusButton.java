package ui.graphics;

import javax.swing.*;

public class ParnassusButton extends JButton {
    // EFFECTS: construct a JButton with label and a ParnassusButtonListener
    public ParnassusButton(String buttonText, ParnassusButtonListener listener) {
        super(buttonText);
        addMouseListener(listener);
    }
}
