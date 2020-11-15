package ui.graphics;

import model.Composition;
import model.Voice;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class GraphicalComposition extends JPanel {
    private static final int PADDING = 5;
    private Composition composition;

    public GraphicalComposition(Composition composition) {
        super(new GridLayout(2, 1));
        setBorder(new EmptyBorder(PADDING, PADDING, PADDING, PADDING));
        this.composition = composition;

        for (Voice v : this.composition) {
            add(new GraphicalVoice(v));
        }
    }
}
