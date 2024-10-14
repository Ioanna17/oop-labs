//import java.awt.*;
//import java.awt.event.*;
//import javax.swing.*;
//
//abstract class Shape {
//    protected int x, y;
//    protected Color color;
//    public Shape(int x, int y, Color color) {
//        this.x = x;
//        this.y = y;
//        this.color = color;
//    }
//
//    public abstract void draw(Graphics g);
//
//    public void move(int dx, int dy) {
//        x += dx;
//        y += dy;
//    }
//
//    public abstract void resize(int factor);
//}
//
//class Circle extends Shape {
//    private int radius;
//
//    public Circle(int x, int y, int radius, Color color) {
//        super(x, y, color);
//        this.radius = radius;
//    }
//
//    @Override
//    public void draw(Graphics g) {
//        g.setColor(color);
//        g.drawOval(x - radius, y - radius, radius * 2, radius * 2);
//    }
//
//    @Override
//    public void resize(int factor) {
//        this.radius += factor; // Змінюємо радіус
//        if (radius < 10) radius = 10; // Мінімальний радіус
//    }
//}
//
//class Square extends Shape {
//    private int side;
//
//    public Square(int x, int y, int side, Color color) {
//        super(x, y, color);
//        this.side = side;
//    }
//
//    @Override
//    public void draw(Graphics g) {
//        g.setColor(color);
//        g.drawRect(x, y, side, side);
//    }
//
//    @Override
//    public void resize(int factor) {
//        this.side += factor;
//        if (side < 10) side = 10;
//    }
//}
//
//class Triangle extends Shape {
//    private int[] xPoints;
//    private int[] yPoints;
//
//    public Triangle(int[] xPoints, int[] yPoints, Color color) {
//        super(0, 0, color);
//        this.xPoints = xPoints;
//        this.yPoints = yPoints;
//    }
//
//    @Override
//    public void draw(Graphics g) {
//        g.setColor(color);
//        g.drawPolygon(xPoints, yPoints, 3);
//    }
//
//    @Override
//    public void resize(int factor) {
//        // Знаходимо центр трикутника
//        int centerX = (xPoints[0] + xPoints[1] + xPoints[2]) / 3;
//        int centerY = (yPoints[0] + yPoints[1] + yPoints[2]) / 3;
//
//        for (int i = 0; i < xPoints.length; i++) {
//            xPoints[i] = centerX + (int)((xPoints[i] - centerX) * (1 + factor / 100.0));
//            yPoints[i] = centerY + (int)((yPoints[i] - centerY) * (1 + factor / 100.0));
//
//        }
//    }
//
//
//    @Override
//    public void move(int dx, int dy) {
//        for (int i = 0; i < xPoints.length; i++) {
//            xPoints[i] += dx;
//            yPoints[i] += dy;
//        }
//    }
//}
//
//public class Lab extends JFrame {
//    private Shape[] shapes;
//    private int selectedShapeIndex = 0;
//
//    public Lab() {
//        setTitle("Малюнки");
//        setSize(960, 960);
//        setDefaultCloseOperation(EXIT_ON_CLOSE);
//
//        shapes = new Shape[] {
//                new Circle(480, 480, 100, Color.GREEN),
//                new Square(200, 200, 200, Color.RED),
//                new Triangle(new int[]{500, 600, 700}, new int[]{600, 500, 600}, Color.ORANGE)
//        };
//
//        addKeyListener(new KeyAdapter() {
//            @Override
//            public void keyPressed(KeyEvent e) {
//                int key = e.getKeyCode();
//                switch (key) {
//                    case KeyEvent.VK_LEFT:
//                        shapes[selectedShapeIndex].move(-10, 0);
//                        break;
//                    case KeyEvent.VK_RIGHT:
//                        shapes[selectedShapeIndex].move(10, 0);
//                        break;
//                    case KeyEvent.VK_UP:
//                        shapes[selectedShapeIndex].move(0, -10);
//                        break;
//                    case KeyEvent.VK_DOWN:
//                        shapes[selectedShapeIndex].move(0, 10);
//                        break;
//                    case KeyEvent.VK_SPACE: // Перемикання фігур на пробіл
//                        selectedShapeIndex = (selectedShapeIndex + 1) % shapes.length;
//                        break;
//                    case KeyEvent.VK_0: // Збільшення розміру
//                        shapes[selectedShapeIndex].resize(10);
//                        break;
//                    case KeyEvent.VK_1: // Зменшення розміру
//                        shapes[selectedShapeIndex].resize(-10);
//                        break;
//                }
//                repaint();
//            }
//        });
//
//        setFocusable(true);
//    }
//
//    @Override
//    public void paint(Graphics g) {
//        super.paint(g);
//
//        for (Shape shape : shapes) {
//            shape.draw(g);
//        }
//    }
//
//    public static void main(String[] args) {
//        Lab t = new Lab();
//        t.setVisible(true);
//    }
//}
