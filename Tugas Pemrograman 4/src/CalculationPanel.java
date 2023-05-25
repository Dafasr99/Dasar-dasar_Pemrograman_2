import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class CalculationPanel extends JPanel {
    private JTextField infixTextField;
    private JTextArea postfixTextArea;
    private JLabel resultLabel;
    private JLabel errorMessageLabel;

    // Constructor
    public CalculationPanel() {
        setLayout(new BorderLayout());

        // Create input panel
        JLabel infixInputLabel = new JLabel("Enter infix expression:");
        JLabel postfixLabel = new JLabel("Postfix expression:");
        resultLabel = new JLabel("Result:");
        JLabel errorLabel = new JLabel("Error message:");

        // Create input value panel
        JPanel inputLabelPanel = new JPanel(new GridLayout(4, 1));
        inputLabelPanel.add(infixInputLabel);
        inputLabelPanel.add(postfixLabel);
        inputLabelPanel.add(resultLabel);
        inputLabelPanel.add(errorLabel);

        // Create input value panel
        infixTextField = new JTextField();
        infixTextField.setPreferredSize(new Dimension(200, 25));
        infixTextField.setBackground(Color.YELLOW);

        // Create postfix text area
        postfixTextArea = new JTextArea();
        postfixTextArea.setEditable(false);
        JScrollPane postfixScrollPane = new JScrollPane(postfixTextArea);

        // Create result label
        resultLabel = new JLabel();
        resultLabel.setFont(resultLabel.getFont().deriveFont(Font.BOLD, 14));
        resultLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));

        // Create error message label
        errorMessageLabel = new JLabel();
        errorMessageLabel.setForeground(Color.RED);
        errorMessageLabel.setFont(errorMessageLabel.getFont().deriveFont(Font.BOLD));
        errorMessageLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));

        // Create input value panel
        JPanel inputValuePanel = new JPanel(new GridLayout(4, 1));
        inputValuePanel.add(infixTextField);
        inputValuePanel.add(postfixScrollPane);
        inputValuePanel.add(resultLabel);
        inputValuePanel.add(errorMessageLabel);

        // Add panels to main panel
        add(inputLabelPanel, BorderLayout.WEST);
        add(inputValuePanel, BorderLayout.CENTER);

        // Add key listener to infix text field
        infixTextField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            // Calculate postfix expression when enter key is pressed
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    calculate();
                }
            }

            // Calculate postfix expression when enter key is released
            @Override
            public void keyReleased(KeyEvent e) {
            }
        });
    }

    // Calculate postfix expression
    private void calculate() {
        String infixExpression = infixTextField.getText().trim();

        // Calculate postfix expression if infix expression is not empty
        if (!infixExpression.isEmpty()) {

            // Calculate postfix expression
            try {
                String postfixExpression = PostfixExpression.convertToPostfix(infixExpression);
                long result = PostfixExpression.evaluatePostfix(postfixExpression);

                // Display postfix expression and result
                postfixTextArea.setText(postfixExpression);
                resultLabel.setText(String.format(" %s ", result ));
                errorMessageLabel.setText("");

            // Display error message if infix expression is invalid
            } catch (IllegalArgumentException ex) {
                String errorMessage = ex.getMessage();
                postfixTextArea.setText("");
                resultLabel.setText("");
                errorMessageLabel.setText(String.format("[" + " %s ", errorMessage + " ]" ));
            
            // Display error message if postfix expression is invalid
            } catch (ArithmeticException ex) {
                String errorMessage = ex.getMessage();
                postfixTextArea.setText("");
                resultLabel.setText("");
                errorMessageLabel.setText(String.format("[" + " %s ", errorMessage + " ]" ));
            }
        }
    }
}
