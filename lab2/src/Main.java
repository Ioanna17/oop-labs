import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

abstract class Shape {
    protected int x, y;

    public Shape(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public abstract void draw();
    public abstract void move(int dx, int dy);
    public abstract void resize(double factor);
}

class Circle extends Shape {
    private int radius;

    public Circle(int x, int y, int radius) {
        super(x, y);
        this.radius = radius;
    }

    @Override
    public void draw() {
        for (int i = -radius; i <= radius; i++) {
            for (int j = -radius; j <= radius; j++) {
                if (i*i + j*j <= radius*radius) {
                    System.out.print("O");
                } else {
                    System.out.print(" ");
                }
            }
            System.out.println();
        }
        System.out.println("Коло: центр (" + x + ", " + y + "), радіус " + radius);
    }

    @Override
    public void move(int dx, int dy) {
        x += dx;
        y += dy;
        System.out.println("Коло переміщено на (" + x + ", " + y + ")");
    }

    @Override
    public void resize(double factor) {
        radius *= factor;
        System.out.println("Розмір кола змінено. Новий радіус: " + radius);
    }
}

class Rectangle extends Shape {
    private int width, height;

    public Rectangle(int x, int y, int width, int height) {
        super(x, y);
        this.width = width;
        this.height = height;
    }

    @Override
    public void draw() {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (i == 0 || i == height - 1 || j == 0 || j == width - 1) {
                    System.out.print("*");
                } else {
                    System.out.print(" ");
                }
            }
            System.out.println();
        }
        System.out.println("Прямокутник: верхній лівий кут (" + x + ", " + y + "), ширина " + width + ", висота " + height);
    }

    @Override
    public void move(int dx, int dy) {
        x += dx;
        y += dy;
        System.out.println("Прямокутник переміщено на (" + x + ", " + y + ")");
    }

    @Override
    public void resize(double factor) {
        width *= factor;
        height *= factor;
        System.out.println("Розмір прямокутника змінено. Нова ширина: " + width + ", нова висота: " + height);
    }
}

class IsoscelesTriangle extends Shape {
    private int base, height;

    public IsoscelesTriangle(int x, int y, int base, int height) {
        super(x, y);
        this.base = base;
        this.height = height;
    }

    @Override
    public void draw() {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < base; j++) {
                if (j == base/2 - i || j == base/2 + i || i == height - 1) {
                    System.out.print("*");
                } else {
                    System.out.print(" ");
                }
            }
            System.out.println();
        }
        System.out.println("Рівнобедрений трикутник: вершина (" + x + ", " + y + "), основа " + base + ", висота " + height);
    }

    @Override
    public void move(int dx, int dy) {
        x += dx;
        y += dy;
        System.out.println("Трикутник переміщено на (" + x + ", " + y + ")");
    }

    @Override
    public void resize(double factor) {
        base *= factor;
        height *= factor;
        System.out.println("Розмір трикутника змінено. Нова основа: " + base + ", нова висота: " + height);
    }
}

class RightTriangle extends Shape {
    private int base, height;

    public RightTriangle(int x, int y, int base, int height) {
        super(x, y);
        this.base = base;
        this.height = height;
    }

    @Override
    public void draw() {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < base; j++) {
                // Малюємо бічні сторони трикутника та основу
                if (j == 0 || i == height - 1 || j == i) {
                    System.out.print("*");
                } else {
                    System.out.print(" ");
                }
            }
            System.out.println();
        }
        System.out.println("Прямокутний трикутник: вершина (" + x + ", " + y + "), основа " + base + ", висота " + height);
    }

    @Override
    public void move(int dx, int dy) {
        x += dx;
        y += dy;
        System.out.println("Трикутник переміщено на (" + x + ", " + y + ")");
    }

    @Override
    public void resize(double factor) {
        base *= factor;
        height *= factor;
        System.out.println("Розмір трикутника змінено. Нова основа: " + base + ", нова висота: " + height);
    }
}

class ShapeManager {
    private List<Shape> shapes = new ArrayList<>();

    public void addShape(Shape shape) {
        shapes.add(shape);
    }

    public void drawAllShapes() {
        for (int i = 0; i < shapes.size(); i++) {
            System.out.println("\nФігура " + (i + 1) + ":");
            shapes.get(i).draw();
        }
    }

    public void moveAllShapes(int dx, int dy) {
        for (Shape shape : shapes) {
            shape.move(dx, dy);
        }
    }

    public void resizeAllShapes(double factor) {
        for (Shape shape : shapes) {
            shape.resize(factor);
        }
    }
}

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static ShapeManager shapeManager = new ShapeManager();

    public static void main(String[] args) {
        boolean running = true;
        while (running) {
            printMenu();
            int choice = getIntInput("Виберіть опцію: ");

            switch (choice) {
                case 1:
                    addShape();
                    break;
                case 2:
                    shapeManager.drawAllShapes();
                    break;
                case 3:
                    moveShapes();
                    break;
                case 4:
                    resizeShapes();
                    break;
                case 5:
                    running = false;
                    break;
                default:
                    System.out.println("Невірний вибір. Спробуйте ще раз.");
            }
        }
        System.out.println("Дякуємо за використання графічного редактора!");
    }

    private static void printMenu() {
        System.out.println("\n=== Графічний редактор ===");
        System.out.println("1. Додати фігуру");
        System.out.println("2. Показати всі фігури");
        System.out.println("3. Перемістити всі фігури");
        System.out.println("4. Змінити розмір всіх фігур");
        System.out.println("5. Вийти");
    }

    private static void addShape() {
        System.out.println("\n=== Додавання фігури ===");
        System.out.println("1. Коло");
        System.out.println("2. Прямокутник");
        System.out.println("3. Рівнобедрений трикутник");
        System.out.println("4. Прямокутний трикутник");
        int shapeType = getIntInput("Виберіть тип фігури: ");

        int x = getIntInput("Введіть x-координату: ");
        int y = getIntInput("Введіть y-координату: ");

        Shape shape = null;
        switch (shapeType) {
            case 1:
                int radius = getIntInput("Введіть радіус: ");
                shape = new Circle(x, y, radius);
                break;
            case 2:
                int width = getIntInput("Введіть ширину: ");
                int height = getIntInput("Введіть висоту: ");
                shape = new Rectangle(x, y, width, height);
                break;
            case 3:
                int baseIso = getIntInput("Введіть довжину основи: ");
                int heightIso = getIntInput("Введіть висоту: ");
                shape = new IsoscelesTriangle(x, y, baseIso, heightIso);
                break;
            case 4:
                int baseRight = getIntInput("Введіть довжину основи: ");
                int heightRight = getIntInput("Введіть висоту: ");
                shape = new RightTriangle(x, y, baseRight, heightRight);
                break;
            default:
                System.out.println("Невірний тип фігури.");
                return;
        }

        shapeManager.addShape(shape);
        System.out.println("Фігуру додано!");
    }

    private static void moveShapes() {
        int dx = getIntInput("Введіть зміщення по x: ");
        int dy = getIntInput("Введіть зміщення по y: ");
        shapeManager.moveAllShapes(dx, dy);
    }

    private static void resizeShapes() {
        double factor = getDoubleInput("Введіть коефіцієнт зміни розміру: ");
        shapeManager.resizeAllShapes(factor);
    }

    private static int getIntInput(String prompt) {
        System.out.print(prompt);
        while (!scanner.hasNextInt()) {
            System.out.println("Будь ласка, введіть ціле число.");
            scanner.next();
        }
        return scanner.nextInt();
    }

    private static double getDoubleInput(String prompt) {
        System.out.print(prompt);
        while (!scanner.hasNextDouble()) {
            System.out.println("Будь ласка, введіть число.");
            scanner.next();
        }
        return scanner.nextDouble();
    }
}