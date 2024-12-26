import org.junit.jupiter.api.*;
import static org.mockito.Mockito.*;
import javax.swing.*;
import java.awt.*;
import static org.junit.jupiter.api.Assertions.*;
import java.net.URL;
import java.util.*;

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

    private List<ImageProxy> mediaList;
    private MediaIterator iterator;

    @BeforeEach
    void setUp() {
        mediaList = new ArrayList<>();
        mediaList.add(new ImageProxy(null)); // Adding dummy ImageProxy with null URL
        mediaList.add(new ImageProxy(null)); // Adding dummy ImageProxy with null URL
        iterator = new MediaIterator(mediaList);
    }

    @Test
    void testHasNext() {
        assertTrue(iterator.hasNext());
        iterator.next();
        assertTrue(iterator.hasNext());
    }

    @Test
    void testSetFilterKeyword() {
        iterator.setFilterKeyword("dummy");
        assertTrue(iterator.hasNext());
    }

    @Test
    void testResetIterator() {
        iterator.next(); // advance iterator
        iterator.resetIterator();
        assertTrue(iterator.hasNext());
    }

    private ImageProxy imageProxy;

    @BeforeEach
    void setUp() throws Exception {
        URL testUrl = new URL("http://example.com/test.jpg");
        imageProxy = new ImageProxy(testUrl);
    }

    @Test
    void testDefaultDimensions() {
        assertEquals(800, imageProxy.getIconWidth());
        assertEquals(600, imageProxy.getIconHeight());
    }

    @Test
    void testPaintIconBeforeLoading() {
        Graphics mockGraphics = mock(Graphics.class);
        Component mockComponent = mock(Component.class);

        imageProxy.paintIcon(mockComponent, mockGraphics, 0, 0);

        // Verify that loading message is drawn
        verify(mockGraphics).drawString(eq("Loading image..."), anyInt(), anyInt());
    }

    @Test
    void testSetImageIcon() {
        ImageIcon mockImageIcon = mock(ImageIcon.class);
        when(mockImageIcon.getIconWidth()).thenReturn(100);
        when(mockImageIcon.getIconHeight()).thenReturn(100);

        imageProxy.setImageIcon(mockImageIcon);

        assertEquals(100, imageProxy.getIconWidth());
        assertEquals(100, imageProxy.getIconHeight());
    }
}


