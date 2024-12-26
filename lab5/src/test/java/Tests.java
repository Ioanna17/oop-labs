import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class Tests {
    @Mock
    private Icon mockIcon;
    private ImageComponent imageComponent;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
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

class MediaIteratorTest {
    private List<ImageProxy> mediaList;
    private MediaIterator iterator;

    @BeforeEach
    void setUp() throws Exception {
        mediaList = new ArrayList<>();
        mediaList.add(new ImageProxy(new URL("http://example.com/1.jpg"), "Test1"));
        mediaList.add(new ImageProxy(new URL("http://example.com/2.jpg"), "Test2"));
        mediaList.add(new ImageProxy(new URL("http://example.com/3.jpg"), "different"));

        iterator = new MediaIterator(mediaList);
    }

    @Test
    void testHasNextWithNoFilter() {
        assertTrue(iterator.hasNext());
        iterator.next();
        assertTrue(iterator.hasNext());
        iterator.next();
        assertTrue(iterator.hasNext());
        iterator.next();
        assertFalse(iterator.hasNext());
    }

    @Test
    void testFilterKeyword() {
        iterator.setFilterKeyword("test");
        assertTrue(iterator.hasNext());
        assertEquals("Test1", iterator.next().name);
        assertTrue(iterator.hasNext());
        assertEquals("Test2", iterator.next().name);
        assertFalse(iterator.hasNext());
    }

    @Test
    void testResetIterator() {
        iterator.next();
        iterator.resetIterator();
        assertTrue(iterator.hasNext());
        assertEquals("Test1", iterator.next().name);
    }
}

class ImageProxyTest {
    private ImageProxy imageProxy;
    private URL testUrl;

    @BeforeEach
    void setUp() throws Exception {
        testUrl = new URL("http://example.com/test.jpg");
        imageProxy = new ImageProxy(testUrl, "TestImage");
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

        verify(mockGraphics).drawString(eq("Loading album cover, please wait..."), anyInt(), anyInt());
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