package ui;

import exceptions.GraphicalCompositionTooLargeException;
import model.Composition;
import org.junit.jupiter.api.Test;
import ui.graphics.GraphicalComposition;

import static org.junit.jupiter.api.Assertions.fail;

public class GraphicalCompositionTest {
    private Composition cmp;
    private GraphicalComposition gCmp;

    @Test
    public void testGraphicalCompositionException() {
        cmp = new Composition(GraphicalComposition.MAX_SIZE);
        try {
            gCmp = new GraphicalComposition(cmp);
        } catch (GraphicalCompositionTooLargeException graphicalCompositionTooLargeException) {
            fail();
        }

        cmp = new Composition(GraphicalComposition.MAX_SIZE + 1);
        try {
            gCmp = new GraphicalComposition(cmp);
            fail();
        } catch (GraphicalCompositionTooLargeException graphicalCompositionTooLargeException) {
        }
    }
}
