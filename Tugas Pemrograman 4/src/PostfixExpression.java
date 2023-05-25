import java.util.Stack;
import java.util.StringTokenizer;

public class PostfixExpression {
    public static String convertToPostfix(String infixExpression) {
        StringBuilder postfixExpression = new StringBuilder();
        Stack<Character> operatorStack = new Stack<>();

        StringTokenizer tokenizer = new StringTokenizer(infixExpression, "+-*/^()", true);

        while (tokenizer.hasMoreTokens()) {
            String token = tokenizer.nextToken().trim();

            if (!token.isEmpty()) {
                if (isOperand(token)) {
                    postfixExpression.append(token).append(" ");
                } else if (isOperator(token)) {
                    char operator = token.charAt(0);

                    if (operator == '(') {
                        operatorStack.push(operator);
                    } else if (operator == ')') {
                        while (!operatorStack.isEmpty() && operatorStack.peek() != '(') {
                            postfixExpression.append(operatorStack.pop()).append(" ");
                        }

                        if (!operatorStack.isEmpty() && operatorStack.peek() == '(') {
                            operatorStack.pop();
                        } else {
                            throw new IllegalArgumentException("Invalid expression: Unbalanced parentheses");
                        }
                    } else {
                        while (!operatorStack.isEmpty() && getPrecedence(operator) <= getPrecedence(operatorStack.peek())) {
                            postfixExpression.append(operatorStack.pop()).append(" ");
                        }

                        operatorStack.push(operator);
                    }
                } else {
                    throw new IllegalArgumentException("Invalid expression: Unknown token '" + token + "'");
                }
            }
        }

        while (!operatorStack.isEmpty()) {
            if (operatorStack.peek() == '(' || operatorStack.peek() == ')') {
                throw new IllegalArgumentException("Invalid expression: Unbalanced parentheses");
            }

            postfixExpression.append(operatorStack.pop()).append(" ");
        }

        return postfixExpression.toString().trim();
    }

    public static long evaluatePostfix(String postfixExpression) {
        Stack<Long> operandStack = new Stack<>();

        StringTokenizer tokenizer = new StringTokenizer(postfixExpression);

        while (tokenizer.hasMoreTokens()) {
            String token = tokenizer.nextToken();

            if (isOperand(token)) {
                long operand = Long.parseLong(token);
                operandStack.push(operand);
            } else if (isOperator(token)) {
                if (operandStack.size() < 2) {
                    throw new IllegalArgumentException("Invalid expression: Insufficient operands");
                }

                long operand2 = operandStack.pop();
                long operand1 = operandStack.pop();
                long result = evaluateOperation(operand1, operand2, token.charAt(0));
                operandStack.push(result);
            } else {
                throw new IllegalArgumentException("Invalid expression: Unknown token '" + token + "'");
            }
        }

        if (operandStack.size() != 1) {
            throw new IllegalArgumentException("Invalid expression: Too many operands");
        }

        return operandStack.pop();
    }

    private static boolean isOperand(String token) {
        return token.matches("\\d+");
    }

    private static boolean isOperator(String token) {
        return token.matches("[+\\-*/^]");
    }

    private static int getPrecedence(char operator) {
        if (operator == '+' || operator == '-') {
            return 1;
        } else if (operator == '*' || operator == '/') {
            return 2;
        } else if (operator == '^') {
            return 3;
        } else {
            throw new IllegalArgumentException("Invalid operator: " + operator);
        }
    }

    private static long evaluateOperation(long operand1, long operand2, char operator) {
        if (operator == '+') {
            return operand1 + operand2;
        } else if (operator == '-') {
            return operand1 - operand2;
        } else if (operator == '*') {
            return operand1 * operand2;
        } else if (operator == '/') {
            if (operand2 == 0) {
                throw new ArithmeticException("Invalid expression: Division by zero");
            }
            return operand1 / operand2;
        } else if (operator == '^') {
            return (long) Math.pow(operand1, operand2);
        } else {
            throw new IllegalArgumentException("Invalid operator: " + operator);
        }
    }
}
