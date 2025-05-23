import java.io.*;
import java.util.*;

public class task4 {
    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Укажите путь к файлу как аргумент командной строки.");
            return;
        }

        String filePath = args[0];
        List<Integer> numbers = new ArrayList<>();

        try (Scanner scanner = new Scanner(new File(filePath))) {
            while (scanner.hasNextInt()) {
                numbers.add(scanner.nextInt());
            }
        } catch (FileNotFoundException e) {
            System.out.println("Файл не найден: " + filePath);
            return;
        }

        if (numbers.isEmpty()) {
            System.out.println("Файл пустой или не содержит чисел.");
            return;
        }

        int[] nums = numbers.stream().mapToInt(i -> i).toArray();
        Arrays.sort(nums);

        int median = nums[nums.length / 2];

        int moves = 0;
        for (int num : nums) {
            moves += Math.abs(num - median);
        }

        System.out.println(moves);
    }
}
