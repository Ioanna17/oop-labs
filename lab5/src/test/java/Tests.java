import org.junit.jupiter.api.*;
import static org.mockito.Mockito.*;

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

        // Create a mock Graphics object to test paintComponent
        Graphics g = mock(Graphics.class);
        imageComponent.paintComponent(g);

        // Verify that the new icon's paintIcon method was called
        verify(newIcon).paintIcon(eq(imageComponent), eq(g), anyInt(), anyInt());
    }
}
