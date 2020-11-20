package ui.graphics;

import javax.swing.*;

// A simple button for general use in Parnassus' GUI
public class ParnassusButton extends JButton {
    // EFFECTS: construct a JButton with label and a ParnassusButtonListener
    public ParnassusButton(String buttonText, ParnassusButtonListener listener) {
        super(buttonText);
        addMouseListener(listener);
    }
}
