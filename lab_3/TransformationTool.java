import java.util.List;
import java.util.ArrayList;
import java.awt.Point;
import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.Dimension;

public class TransformationTool {
    public static void main(String[] args) {
        File dataset = new File("DS5.txt");
        if (!dataset.exists()) {
            System.err.println("Dataset file DS5.txt not found! Exiting...");
            return;
        }

        List<Point> points = readPointsFromFile("DS5.txt");

        int n = 5;
        double alphaDegrees = 10 * (n + 1);
        double alphaRadians = Math.toRadians(alphaDegrees);

        double[][] M = getTransformationMatrix(alphaRadians);

        List<Point> transformedPoints = applyTransformation(M, points);

        JFrame frame = new JFrame("Affine Transformation");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new PointPanel(transformedPoints));
        frame.pack();
        frame.setVisible(true);

        saveImage(transformedPoints, "output.png");
    }

    private static List<Point> readPointsFromFile(String filename) {
        List<Point> points = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(" ");
                if (parts.length == 2) {
                    int x = Integer.parseInt(parts[0]);
                    int y = Integer.parseInt(parts[1]);
                    points.add(new Point(x, y));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return points;
    }

    private static double[][] getTransformationMatrix(double alpha) {
        double cos = Math.cos(alpha);
        double sin = Math.sin(alpha);

        double[][] T1 = {
                {1, 0, -480},
                {0, 1, -480},
                {0, 0, 1}
        };

        double[][] R = {
                {cos, -sin, 0},
                {sin, cos, 0},
                {0, 0, 1}
        };

        double[][] T2 = {
                {1, 0, 480},
                {0, 1, 480},
                {0, 0, 1}
        };

        double[][] MR = multiply(R, T1);
        return multiply(T2, MR);
    }

    private static double[][] multiply(double[][] a, double[][] b) {
        int rowsA = a.length;
        int colsA = a[0].length;
        int colsB = b[0].length;
        double[][] result = new double[rowsA][colsB];
        for (int i = 0; i < rowsA; i++) {
            for (int j = 0; j < colsB; j++) {
                for (int k = 0; k < colsA; k++) {
                    result[i][j] += a[i][k] * b[k][j];
                }
            }
        }
        return result;
    }

    private static List<Point> applyTransformation(double[][] M, List<Point> points) {
        List<Point> transformedPoints = new ArrayList<>();
        for (Point p : points) {
            double x = M[0][0] * p.x + M[0][1] * p.y + M[0][2] * 1;
            double y = M[1][0] * p.x + M[1][1] * p.y + M[1][2] * 1;

            int newX = (int) Math.round(x);
            int newY = (int) Math.round(y);

            if (newX < 0 || newX >= 960 || newY < 0 || newY >= 960) {
                System.out.println("Point out of bounds: (" + newX + ", " + newY + ")");
                continue;
            }

            transformedPoints.add(new Point(newX, newY));
        }
        return transformedPoints;
    }

    private static void saveImage(List<Point> points, String filename) {
        BufferedImage image = new BufferedImage(960, 960, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2 = image.createGraphics();

        drawGraphics(g2, points);

        g2.dispose();

        try {
            File outputFile = new File(filename);
            ImageIO.write(image, "png", outputFile);
            System.out.println("Image saved to: " + outputFile.getAbsolutePath());
        } catch (IOException e) {
            System.err.println("Error saving image: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void drawGraphics(Graphics2D g2, List<Point> points) {
        g2.setColor(Color.WHITE);
        g2.fillRect(0, 0, 960, 960);

        g2.setColor(Color.BLACK);
        g2.drawLine(0, 480, 960, 480);
        g2.drawLine(480, 0, 480, 960);

        g2.setColor(Color.BLUE);
        for (Point p : points) {
            int x = p.x;
            int y = 960 - p.y;
            if (x >= 0 && x < 960 && y >= 0 && y < 960) {
                g2.fillOval(x - 2, y - 2, 5, 5);
            }
        }
    }
}

class PointPanel extends JPanel {
    private List<Point> points;

    public PointPanel(List<Point> points) {
        this.points = points;
        setBackground(Color.WHITE);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(960, 960);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        TransformationTool.drawGraphics(g2, points);
    }
}
