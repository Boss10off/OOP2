package Exceptions;

public class CalcInString {
    public static void main(String[] args) {
        System.out.println(calculate("12+/123"));
    }

    public static float calculate(String s) {
        String[] operands = new String[s.length()];
        for (int i = 0; i < s.length(); i++) {
            operands[i] = s.substring(i, i + 1);
        }

        float a = 0;
        float b = 0;
        String op = "";

        for (String operand : operands) {
            try {
                b = Float.parseFloat(operand) + (b * 10);
            } catch (NumberFormatException e) {
                a = b;
                b = 0;
                if (op.isEmpty()) {
                    op = operand;
                }
                else{
                    throw new NoOperationException("No calculation found");
                }
            }
        }

        return switch (op) {
            case "+" -> a + b;
            case "-" -> a - b;
            case "*" -> a * b;
            case "/" -> a / b;
            case "%" -> a % b;
            default -> throw new NoOperationException("No calculation found");
        };
    }
}
