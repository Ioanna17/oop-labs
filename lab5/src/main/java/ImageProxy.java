import javax.swing.*;
import java.awt.*;
import java.net.URL;

class ImageProxy implements Icon {
    private ImageIcon imageIcon;
    private final URL imageURL;

    public ImageProxy(URL url) {
        this.imageURL = url;
    }

    public int getIconWidth() {
        return imageIcon != null ? imageIcon.getIconWidth() : 800;
    }

    public int getIconHeight() {
        return imageIcon != null ? imageIcon.getIconHeight() : 600;
    }

    public void setImageIcon(ImageIcon imageIcon) {
        this.imageIcon = imageIcon;
    }

    public void paintIcon(Component c, Graphics g, int x, int y) {
        if (imageIcon != null) {
            imageIcon.paintIcon(c, g, x, y);
        } else {
            g.drawString("Loading image...", x + 300, y + 190);
        }
    }
}
