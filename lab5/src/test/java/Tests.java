import org.junit.jupiter.api.*;
import static org.mockito.Mockito.*;
import javax.swing.*;
import java.awt.*;

class Tests {
    private ImageComponent imageComponent;
    private Icon mockIcon;

    @BeforeEach
    void setUp() {
        mockIcon = mock(Icon.class);
        imageComponent = new ImageComponent(mockIcon);
    }

    @Test
    void testSetIcon() {
        Icon newIcon = mock(Icon.class);
        imageComponent.setIcon(newIcon);

        Graphics g = mock(Graphics.class);
        imageComponent.paintComponent(g);

        verify(newIcon).paintIcon(eq(imageComponent), eq(g), anyInt(), anyInt());
    }

    private ImageComponent imageComponent;
    private Icon mockIcon;

    @BeforeEach
    void setUp() {
        mockIcon = mock(Icon.class);
        imageComponent = new ImageComponent(mockIcon);
    }

    @Test
    void testSetIcon() {
        Icon newIcon = mock(Icon.class);
        imageComponent.setIcon(newIcon);

        Graphics g = mock(Graphics.class);
        imageComponent.paintComponent(g);

        verify(newIcon).paintIcon(eq(imageComponent), eq(g), anyInt(), anyInt());
    }
}


