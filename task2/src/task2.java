import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class task2 {
    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("Укажите два аргумента: файл с окружностью и файл с точками.");
            return;
        }

        String circleFilePath = args[0];
        String pointsFilePath = args[1];

        double centerX, centerY, radius;

        try (Scanner circleScanner = new Scanner(new File(circleFilePath))) {
            centerX = circleScanner.nextDouble();
            centerY = circleScanner.nextDouble();
            radius = circleScanner.nextDouble();
        } catch (Exception e) {
            System.out.println("Ошибка при чтении файла окружности: " + e.getMessage());
            return;
        }

        double radiusSquared = radius * radius;

        try (Scanner pointScanner = new Scanner(new File(pointsFilePath))) {
            while (pointScanner.hasNextDouble()) {
                double x = pointScanner.nextDouble();
                double y = pointScanner.nextDouble();

                double dx = x - centerX;
                double dy = y - centerY;
                double distanceSquared = dx * dx + dy * dy;

                if (Math.abs(distanceSquared - radiusSquared) < 1e-9) {
                    System.out.println(0);
                } else if (distanceSquared < radiusSquared) {
                    System.out.println(1);
                } else {
                    System.out.println(2);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Файл с точками не найден: " + e.getMessage());
        }
    }
}

