package ui;

import model.Composition;
import model.Note;
import model.Voice;
import ui.buttons.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class Graphical extends JFrame {
    public static final int WIDTH_MIN = 1200;
    public static final int HEIGHT_MIN = 200;
    private static final int SPINNER_DEFAULT = 60;
    private static final int SPINNER_MIN = 21;
    private static final int SPINNER_MAX = 127;
    private static final int SPINNER_INC = 1;
    private static final int MAIN_GRID_ROWS = 1;
    private static final int MAIN_GRID_COLS = 1;
    private static final int MAIN_PANEL_PADDING = 25;
    private static final int COMPOSITION_VOICE_COUNT = 2;
    private static final int COMPOSITION_NOTE_COUNT = 8;
    private static final int COMPOSITION_PANEL_PADDING = 5;
    private static final int BUTTON_COUNT = 3;
    private static final int BUTTON_VGAP = 10;
    private static final int DEFAULT_COMPOSITION_SIZE = 8;

    private Composition cmp;
    private JPanel mainPanel;
    private JPanel compositionPanel;
    private JPanel buttonPanel;

    public Graphical() {
        super("Parnassus");
        cmp = new Composition(DEFAULT_COMPOSITION_SIZE);
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
        initCompositionPanel();
        initButtonPanel();
        mainPanel.add(compositionPanel);
        mainPanel.add(buttonPanel);
        add(mainPanel);
    }

    private void initCompositionPanel() {
        compositionPanel = new JPanel(new GridLayout(COMPOSITION_VOICE_COUNT, COMPOSITION_NOTE_COUNT));
        compositionPanel.setBorder(new EmptyBorder(COMPOSITION_PANEL_PADDING, COMPOSITION_PANEL_PADDING,
                                                    COMPOSITION_PANEL_PADDING, COMPOSITION_PANEL_PADDING));
        for (Voice v : cmp) {
            for (Note n : v) {
                JSpinner js =
                        new JSpinner(new SpinnerNumberModel(SPINNER_DEFAULT,
                                                            SPINNER_MIN,
                                                            SPINNER_MAX,
                                                            SPINNER_INC));
                js.setFont(new Font("NotoSansMono Nerd Font", Font.BOLD, 24));

                int pitch = n.getPitch();
                if (pitch != Note.REST) {
                    js.setValue(pitch);
                }

                compositionPanel.add(js);
            }
        }
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
        new Graphical();
    }
}
