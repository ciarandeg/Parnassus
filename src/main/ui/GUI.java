package ui;

import exceptions.*;
import model.Composition;
import model.Validator;
import persistence.JsonReader;
import persistence.JsonWriter;
import ui.graphics.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.io.FileNotFoundException;
import java.io.IOException;

public class GUI extends JFrame {
    public static final int WIDTH_MIN = 1200;
    public static final int HEIGHT_MIN = 200;
    private static final int MAIN_GRID_ROWS = 1;
    private static final int MAIN_GRID_COLS = 1;
    private static final int MAIN_PANEL_PADDING = 25;
    private static final int BUTTON_COUNT = 3;
    private static final int BUTTON_VGAP = 10;
    private static final int DEFAULT_COMPOSITION_SIZE = 8;
    private final JFileChooser fc = new JFileChooser();

    private Composition cmp;
    private JPanel mainPanel;
    private GraphicalComposition compositionPanel;
    private ButtonPanel buttonPanel;

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
        buttonPanel = new ButtonPanel();
        mainPanel.add(compositionPanel);
        mainPanel.add(buttonPanel);
        add(mainPanel);
    }

    private void setComposition(Composition cmp) {
        this.cmp = cmp;
        mainPanel.remove(compositionPanel);
        compositionPanel = new GraphicalComposition(cmp);
        mainPanel.add(compositionPanel, 0);
        mainPanel.validate();
    }

    public static void main(String[] args) {
        new GUI();
    }

    private class ButtonPanel extends JPanel {
        private GridLayout layout;

        public ButtonPanel() {
            super(new GridLayout(BUTTON_COUNT, 1));
            layout = (GridLayout) getLayout();
            layout.setVgap(BUTTON_VGAP);

            ParnassusButton loadButton = new ParnassusButton("Load", new LoadButtonListener());
            ParnassusButton saveButton = new ParnassusButton("Save", new SaveButtonListener());
            ParnassusButton validationButton = new ParnassusButton("Validate", new ValidationButtonListener());

            add(loadButton);
            add(saveButton);
            add(validationButton);
        }
    }

    private class LoadButtonListener extends ParnassusButtonListener {
        @Override
        public void mouseClicked(MouseEvent e) {
            if (fc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                JsonReader jsr = new JsonReader(fc.getSelectedFile().getPath());
                try {
                    Composition cmp = jsr.read();
                    setComposition(cmp);
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        }
    }

    private class SaveButtonListener extends ParnassusButtonListener {
        @Override
        public void mouseClicked(MouseEvent e) {
            if (fc.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
                JsonWriter jsw = new JsonWriter(fc.getSelectedFile().getPath());
                try {
                    jsw.open();
                    jsw.write(cmp);
                    jsw.close();
                } catch (FileNotFoundException fileNotFoundException) {
                    fileNotFoundException.printStackTrace();
                }
            }
        }
    }

    private class ValidationButtonListener extends ParnassusButtonListener {
        @Override
        public void mouseClicked(MouseEvent e) {
            Validator validator = new Validator();
            String validationMessage = "Your composition is valid!";
            int messageType = JOptionPane.INFORMATION_MESSAGE;
            boolean valid = false;
            try {
                validator.validate(cmp);
                valid = true;
            } catch (VoiceNotFullException voiceNotFullException) {
                validationMessage = "At least one voice is not full.";
            } catch (NotAllIntervalsConsonantException notAllIntervalsConsonantException) {
                validationMessage = "Not all intervals are consonant.";
            } catch (FirstIntervalNotPerfectException firstIntervalNotPerfectException) {
                validationMessage = "The first interval is not a perfect consonance.";
            } catch (LastIntervalNotPerfectException lastIntervalNotPerfectException) {
                validationMessage = "The last interval is not a perfect consonance.";
            } catch (ParallelToPerfectException parallelToPerfectException) {
                validationMessage = "At least one perfect consonance is approached by parallel motion.";
            }

            if (!valid) {
                validationMessage = "Invalid! " + validationMessage;
                messageType = JOptionPane.ERROR_MESSAGE;
            }

            JOptionPane.showMessageDialog(null, validationMessage, "Validation Results", messageType);
        }
    }
}
