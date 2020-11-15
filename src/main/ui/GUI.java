package ui;

import model.Composition;
import ui.graphics.*;
import ui.graphics.buttons.LoadButtonListener;
import ui.graphics.buttons.ParnassusButton;
import ui.graphics.buttons.SaveButtonListener;
import ui.graphics.buttons.ValidationButtonListener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class GUI extends JFrame {
    public static final int WIDTH_MIN = 1200;
    public static final int HEIGHT_MIN = 200;
    private static final int MAIN_GRID_ROWS = 1;
    private static final int MAIN_GRID_COLS = 1;
    private static final int MAIN_PANEL_PADDING = 25;
    private static final int BUTTON_COUNT = 3;
    private static final int BUTTON_VGAP = 10;
    private static final int DEFAULT_COMPOSITION_SIZE = 8;

    private Composition cmp;
    private JPanel mainPanel;
    private GraphicalComposition compositionPanel;
    private JPanel buttonPanel;

    public GUI() {
        super("Parnassus");
        cmp = new Composition(DEFAULT_COMPOSITION_SIZE);
        compositionPanel = new GraphicalComposition(cmp);
        initGraphics();
    }

    // MODIFIES: this
    // EFFECTS:  draws the JFrame window where this DrawingEditor will operate, and populates the tools to be used
    //           to manipulate this drawing
    private void initGraphics() {
        setMinimumSize(new Dimension(WIDTH_MIN, HEIGHT_MIN));

        initMainPanel();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void initMainPanel() {
        mainPanel = new JPanel(new GridLayout(MAIN_GRID_ROWS, MAIN_GRID_COLS));
        mainPanel.setBorder(new EmptyBorder(MAIN_PANEL_PADDING, MAIN_PANEL_PADDING,
                                            MAIN_PANEL_PADDING, MAIN_PANEL_PADDING));
        initButtonPanel();
        mainPanel.add(compositionPanel);
        mainPanel.add(buttonPanel);
        add(mainPanel);
    }

    private void initButtonPanel() {
        GridLayout buttonPanelLayout = new GridLayout(BUTTON_COUNT, 1);
        buttonPanelLayout.setVgap(BUTTON_VGAP);
        buttonPanel = new JPanel(buttonPanelLayout);

        ParnassusButton loadButton = new ParnassusButton("Load", new LoadButtonListener());
        ParnassusButton saveButton = new ParnassusButton("Save", new SaveButtonListener());
        ParnassusButton validationButton = new ParnassusButton("Validate", new ValidationButtonListener());

        buttonPanel.add(loadButton);
        buttonPanel.add(saveButton);
        buttonPanel.add(validationButton);
    }

    public static void main(String[] args) {
        new GUI();
    }
}
