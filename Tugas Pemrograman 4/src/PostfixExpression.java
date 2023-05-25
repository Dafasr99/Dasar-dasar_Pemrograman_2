import java.util.Stack;
import java.util.StringTokenizer;

public class PostfixExpression {
    public static String convertToPostfix(String infixExpression) {

        // Remove all spaces
        StringBuilder postfixExpression = new StringBuilder();
        Stack<Character> operatorStack = new Stack<>();

        // Tokenize infix expression
        StringTokenizer tokenizer = new StringTokenizer(infixExpression, "+-*/^()", true);

        // Convert infix expression to postfix expression
        while (tokenizer.hasMoreTokens()) {
            String token = tokenizer.nextToken().trim();

            // Check if token is empty
            if (!token.isEmpty()) {

                // Check if token is operand or operator
                if (isOperand(token)) {
                    postfixExpression.append(token).append(" ");
                } else if (isOperator(token)) {
                    char operator = token.charAt(0);

                    // Check if operator is parenthesis
                    if (operator == '(') {
                        operatorStack.push(operator);

                    // Check if operator is closing parenthesis
                    } else if (operator == ')') {
                        while (!operatorStack.isEmpty() && operatorStack.peek() != '(') {
                            postfixExpression.append(operatorStack.pop()).append(" ");
                        }

                        // Check if operator stack is empty
                        if (!operatorStack.isEmpty() && operatorStack.peek() == '(') {
                            operatorStack.pop();

                        // Check if operator stack is empty
                        } else {
                            throw new IllegalArgumentException("Invalid expression: Unbalanced parentheses");
                        }

                    // Check if operator is exponentiation
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

        // Check if operator stack is empty
        while (!operatorStack.isEmpty()) {
            if (operatorStack.peek() == '(' || operatorStack.peek() == ')') {
                throw new IllegalArgumentException("Invalid expression: Unbalanced parentheses");
            }

            // Append operator to postfix expression
            postfixExpression.append(operatorStack.pop()).append(" ");
        }

        return postfixExpression.toString().trim();
    }

    // Evaluate postfix expression
    public static long evaluatePostfix(String postfixExpression) {
        Stack<Long> operandStack = new Stack<>();

        // Tokenize postfix expression
        StringTokenizer tokenizer = new StringTokenizer(postfixExpression);

        // Evaluate postfix expression
        while (tokenizer.hasMoreTokens()) {
            String token = tokenizer.nextToken();

            // Check if token is operand or operator
            if (isOperand(token)) {
                long operand = Long.parseLong(token);
                operandStack.push(operand);
            
            // Check if token is operator
            } else if (isOperator(token)) {
                if (operandStack.size() < 2) {
                    throw new IllegalArgumentException("Invalid expression: Insufficient operands");
                }
                
                // Evaluate operation
                long operand2 = operandStack.pop();
                long operand1 = operandStack.pop();
                long result = evaluateOperation(operand1, operand2, token.charAt(0));
                operandStack.push(result);
            
            // Check if token is parenthesis
            } else {
                throw new IllegalArgumentException("Invalid expression: Unknown token '" + token + "'");
            }
        }

        // Check if operand stack is empty
        if (operandStack.size() != 1) {
            throw new IllegalArgumentException("Invalid expression: Too many operands");
        }

        return operandStack.pop();
    }

    // Check if token is operand
    private static boolean isOperand(String token) {
        return token.matches("\\d+");
    }

    // Check if token is operator
    private static boolean isOperator(String token) {
        return token.matches("[+\\-*/^]");
    }

    // Get precedence of operator
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

    // Evaluate operation
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
