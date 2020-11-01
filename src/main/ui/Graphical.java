package ui;

import ui.buttons.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.Button;

public class Graphical extends JFrame {
    public static final int WIDTH = 500;
    public static final int HEIGHT = 100;

    private static final int SPINNER_DEFAULT = 60;
    private static final int SPINNER_MIN = 21;
    private static final int SPINNER_MAX = 127;
    private static final int SPINNER_INC = 1;

    public Graphical() {
        super("Parnassus");
        initializeGraphics();
    }

    // CITATION: adapted in part from UBC CPSC 210's DrawingPlayer
    // MODIFIES: this
    // EFFECTS:  draws the JFrame window where this DrawingEditor will operate, and populates the tools to be used
    //           to manipulate this drawing
    private void initializeGraphics() {
        setMinimumSize(new Dimension(1200, 150));
        JPanel mainPanel = new JPanel(new GridLayout(1, 2));
        mainPanel.setBorder(new EmptyBorder(25, 25, 25, 25));

        JPanel compositionPanel = new JPanel(new GridLayout(2, 8));
        compositionPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        for (int i = 0; i < 16; i++) {

            JSpinner js = new JSpinner(new SpinnerNumberModel(SPINNER_DEFAULT, SPINNER_MIN, SPINNER_MAX, SPINNER_INC));
            js.setFont(new Font("NotoSansMono Nerd Font", Font.BOLD, 24));
            compositionPanel.add(js);
        }

        GridLayout buttonPanelLayout = new GridLayout(3, 1);
        buttonPanelLayout.setVgap(10);
        JPanel buttonPanel = new JPanel(buttonPanelLayout);

        ParnassusButton loadButton = new ParnassusButton("Load", new LoadButtonListener());
        ParnassusButton saveButton = new ParnassusButton("Save", new SaveButtonListener());
        ParnassusButton validationButton = new ParnassusButton("Validate", new ValidationButtonListener());

        buttonPanel.add(loadButton);
        buttonPanel.add(saveButton);
        buttonPanel.add(validationButton);

        mainPanel.add(compositionPanel);
        mainPanel.add(buttonPanel);

        add(mainPanel);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setSize(500, 100);
        setVisible(true);
    }

    public static void main(String[] args) {
        new Graphical();
    }

}
