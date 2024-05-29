import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

public class MyPanelTest {
    private MyPanel myPanel;

    @BeforeEach
    public void setUp() {
        myPanel = new MyPanel();
    }

    @Test
    public void layoutTestRightDoor() {
        myPanel.my1floorPanel();
        int pos = myPanel.getRightDoor().getX();
        assertEquals(pos, 900);
    }
    @Test
    public void layoutTestLeftDoor() {
        myPanel.my2floorPanel();
        int pos = myPanel.getLeftDoor().getX();
        assertEquals(pos, 615);
    }
    @Test
    public void layoutTestCenterDoor() {
        myPanel.my1floorPanel();
        int pos = myPanel.getCenterDoor().getX();
        assertEquals(pos, 550);
    }
    @Test
    public void testEndMessage() {
        String message = myPanel.endMessageString();
        myPanel.removeAll();
        assertEquals(message, myPanel.endMessageString());
    }

    @Test
    public void testLayoutIsNull() {
        assertNull(myPanel.getLayout(), "Layout should be null! okay?!");
    }

    @Test
    public void testPanelHasComponentsAfterInitialization() {
        Component[] components = myPanel.getComponents();
        assertTrue(components.length > 0, "Panel should have components, right?..");
    }
}
