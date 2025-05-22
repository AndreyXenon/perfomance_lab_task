public class task1 {
    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("Укажите два аргумента: n и m");
            return;
        }

        int n = Integer.parseInt(args[0]);
        int m = Integer.parseInt(args[1]);

        int[] circularArray = new int[n];
        for (int i = 0; i < n; i++) {
            circularArray[i] = i + 1;
        }

        StringBuilder path = new StringBuilder();
        int index = 0;

        do {
            path.append(circularArray[index]);
            index = (index + m - 1) % n;
        } while (index != 0);

        System.out.println(path.toString());
    }
}
