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
    private char[][] circle;

    public Circle(int x, int y, int radius) {
        super(x, y);
        this.radius = radius;
        this.circle = new char[150][150];
    }

    @Override
    public void draw() {
        int graphHeight = 40;  // Можна змінити за потребою
        int graphWidth = 80;   // Можна змінити за потребою
        int aspectedGraphHeight = graphHeight / 2;

        // Ініціалізація графічного поля
        for (int row = 0; row <= aspectedGraphHeight; row++) {
            for (int column = 0; column <= graphWidth; column++) {
                circle[row][column] = ' ';
            }
        }

        // Горизонтальна вісь
        for (int column = 0; column <= graphWidth; column++) {
            int row = aspectedGraphHeight / 2;
            circle[row][column] = '-';
        }

        // Вертикальна вісь
        for (int row = 0; row <= aspectedGraphHeight; row++) {
            int column = graphWidth / 2;
            circle[row][column] = '|';
        }

        // Малювання кола
        for (int angle = 0; angle <= 360; angle++) {
            int circleX = (int) (radius * Math.cos(Math.toRadians(angle)));
            int circleY = (int) (radius * Math.sin(Math.toRadians(angle)));

            int row = (aspectedGraphHeight / 2 - circleY / 2);
            int column = graphWidth / 2 + circleX;

            if (row >= 0 && row < aspectedGraphHeight && column >= 0 && column < graphWidth) {
                circle[row][column] = '*';
            }
        }

        // Виведення кола
        for (int row = 0; row <= aspectedGraphHeight; row++) {
            for (int column = 0; column <= graphWidth; column++) {
                System.out.print(circle[row][column]);
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
                    System.out.print("*  ");
                } else {
                    System.out.print("   ");
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

class EquilateralTriangle extends Shape {
    private int side;

    public EquilateralTriangle(int x, int y, int side) {
        super(x, y);
        this.side = side;
    }

    @Override
    public void draw() {
        for (int i = 0; i < side; i++) {
            System.out.print(" ".repeat(x + side - i - 1) + "*");
            if (i > 0 && i < side - 1) System.out.print(" ".repeat(2 * i - 1) + "*");
            else if (i == side - 1) System.out.print(" *".repeat(i));
            System.out.println();
        }
        System.out.println("Правильний трикутник: вершина (" + x + ", " + y + "), сторона " + side);
    }

    @Override
    public void move(int dx, int dy) {
        x += dx;
        y += dy;
        System.out.println("Трикутник переміщено на (" + x + ", " + y + ")");
    }

    @Override
    public void resize(double factor) {
        side *= factor;
        System.out.println("Розмір трикутника змінено. Нова сторона: " + side);
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

        public void moveShape(int index, int dx, int dy) {
            if (index >= 0 && index < shapes.size()) {
                shapes.get(index).move(dx, dy);
            }
        }

        public void resizeShape(int index, double factor) {
            if (index >= 0 && index < shapes.size()) {
                shapes.get(index).resize(factor);
            }
        }

        public int getShapeCount() {
            return shapes.size();
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
                        moveShape();
                        break;
                    case 4:
                        resizeShape();
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
            System.out.println("3. Перемістити одну фігуру");
            System.out.println("4. Змінити розмір однієї фігури");
            System.out.println("5. Вийти");
        }

        private static void addShape() {
            System.out.println("\n=== Додавання фігури ===");
            System.out.println("1. Коло");
            System.out.println("2. Прямокутник");
            System.out.println("3. Рівносторонній трикутник");
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
                    int side = getIntInput("Введіть довжину сторони: ");
                    shape = new EquilateralTriangle(x, y, side);
                    break;
                default:
                    System.out.println("Невірний тип фігури.");
                    return;
            }

            shapeManager.addShape(shape);
            System.out.println("Фігуру додано!");
        }

        private static void moveShape() {
            int index = selectShape();
            if (index != -1) {
                int dx = getIntInput("Введіть зміщення по x: ");
                int dy = getIntInput("Введіть зміщення по y: ");
                shapeManager.moveShape(index, dx, dy);
            }
        }

        private static void resizeShape() {
            int index = selectShape();
            if (index != -1) {
                double factor = getDoubleInput("Введіть коефіцієнт зміни розміру: ");
                shapeManager.resizeShape(index, factor);
            }
        }

        private static int selectShape() {
            shapeManager.drawAllShapes();
            int totalShapes = shapeManager.getShapeCount();
            if (totalShapes == 0) {
                System.out.println("Немає жодної фігури.");
                return -1;
            }
            int index = getIntInput("Виберіть номер фігури (1-" + totalShapes + "): ") - 1;
            if (index < 0 || index >= totalShapes) {
                System.out.println("Невірний номер фігури.");
                return -1;
            }
            return index;
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
