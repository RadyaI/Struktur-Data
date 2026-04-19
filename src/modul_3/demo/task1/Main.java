package modul_3.demo.task1;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        InfixToPostfix converter = new InfixToPostfix();
        PostfixEvaluator evaluator = new PostfixEvaluator();

        while (true) {
            System.out.print("\nMasukkan ekspresi infix (atau 'exit' untuk keluar): ");
            String input = scanner.nextLine().trim();

            if (input.equalsIgnoreCase("exit")) break;

            System.out.println("Infix   : " + input);

            String postfix = converter.convert(input);
            System.out.println("Postfix : " + postfix);

            double result = evaluator.evaluate(postfix);

            if (result == Math.floor(result) && !Double.isInfinite(result)) {
                System.out.println("Result  : " + (int) result);
            } else {
                System.out.println("Result  : " + result);
            }
        }

        scanner.close();
        System.out.println("Program selesai.");
    }
}