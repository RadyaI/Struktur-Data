package modul_3.demo.task1;

import java.util.Stack;

public class PostfixEvaluator {

    public double evaluate(String postfix) {
        Stack<Double> stack = new Stack<>();
        String[] tokens = postfix.split("\\s+");

        for (String token : tokens) {
            if (token.matches("-?\\d+(\\.\\d+)?")) {
                stack.push(Double.parseDouble(token));
            } else {
                double b = stack.pop();
                double a = stack.pop();

                switch (token) {
                    case "+": stack.push(a + b); break;
                    case "-": stack.push(a - b); break;
                    case "*": stack.push(a * b); break;
                    case "/":
                        if (b == 0) {
                            System.out.println("Error: Division by zero!");
                            return Double.NaN;
                        }
                        stack.push(a / b);
                        break;
                }
            }
        }

        return stack.pop();
    }
}