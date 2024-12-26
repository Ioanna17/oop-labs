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



} 