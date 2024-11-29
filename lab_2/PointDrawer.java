import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;

public class PointDrawer extends JPanel {
    private final List<double[]> points = new ArrayList<>();
    private final int canvasWidth = 960;
    private final int canvasHeight = 540;

    public PointDrawer(String filename) {
        readDataset(filename);
        setPreferredSize(new Dimension(canvasWidth, canvasHeight));
    }

    private void readDataset(String filename) {
        File file = new File(filename);
        System.out.println("File absolute path: " + file.getAbsolutePath());
        System.out.println("File exists: " + file.exists());
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(" ");
                if (parts.length == 2) {
                    try {
                        double x = Double.parseDouble(parts[0]);
                        double y = Double.parseDouble(parts[1]);
                        points.add(new double[]{x, y});
                        System.out.println("Added point: (" + x + ", " + y + ")");
                    } catch (NumberFormatException e) {
                        System.err.println("Invalid data: " + line);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        this.setBackground(Color.WHITE);
        g.drawString("", 10, 20);
        if (points.isEmpty()) return;

        double minX = Double.MAX_VALUE;
        double maxX = -Double.MAX_VALUE;
        double minY = Double.MAX_VALUE;
        double maxY = -Double.MAX_VALUE;

        for (double[] p : points) {
            if (p[0] < minX) minX = p[0];
            if (p[0] > maxX) maxX = p[0];
            if (p[1] < minY) minY = p[1];
            if (p[1] > maxY) maxY = p[1];
        }

        if (maxX == minX || maxY == minY) {
            System.err.println("Cannot scale points; all points have the same x or y value.");
            return;
        }

        double scaleWidth = canvasWidth * 0.9 / (maxX - minX);
        double scaleHeight = canvasHeight * 0.9 / (maxY - minY);

        g.setColor(Color.BLACK);
        g.drawLine(0, canvasHeight / 2, canvasWidth, canvasHeight / 2); // Horizontal axis
        g.drawLine(canvasWidth / 2, 0, canvasWidth / 2, canvasHeight);  // Vertical axis

        for (double[] p : points) {
            int screenX = (int) ((p[0] - minX) * scaleWidth + canvasWidth * 0.05);
            int screenY = (int) (canvasHeight - (p[1] - minY) * scaleHeight - canvasHeight * 0.05);
            g.fillRect(screenX - 2, screenY - 2, 4, 4);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            String filename = "C:\\Users\\Ivanka\\Documents\\lab_2\\DS5.txt";
            PointDrawer panel = new PointDrawer(filename);

            JFrame frame = new JFrame("Point Drawer");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.add(panel);
            frame.pack();
            frame.setVisible(true);

            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            BufferedImage image = new BufferedImage(panel.getWidth(), panel.getHeight(), BufferedImage.TYPE_INT_RGB);
            Graphics2D g2d = image.createGraphics();
            panel.printAll(g2d);
            g2d.dispose();

            try {
                ImageIO.write(image, "png", new File("output.png"));
                System.out.println("Image saved to output.png");
            } catch (IOException e) {
                e.printStackTrace();
            }});
    }
}