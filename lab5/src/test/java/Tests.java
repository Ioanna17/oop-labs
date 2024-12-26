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
}


