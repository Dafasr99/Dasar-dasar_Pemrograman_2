import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CalculationPanel extends JPanel {
    private JTextField infixTextField;
    private JTextArea postfixTextArea;
    private JLabel resultLabel;
    private JLabel errorMessageLabel;

    // Constructor
    public CalculationPanel() { 
        setLayout(new BorderLayout());

        // Create labels
        JLabel infixInputLabel = new JLabel("Enter infix expression:");
        JLabel postfixLabel = new JLabel("Postfix expression:");
        resultLabel = new JLabel();
        JLabel errorLabel = new JLabel("Error message:");

        // Set font
        JPanel inputLabelPanel = new JPanel(new GridLayout(4, 1));
        inputLabelPanel.add(infixInputLabel);
        inputLabelPanel.add(postfixLabel);
        inputLabelPanel.add(resultLabel);
        inputLabelPanel.add(errorLabel);

        // Create text fields
        infixTextField = new JTextField();
        infixTextField.setPreferredSize(new Dimension(200, 25));

        // Create text area
        postfixTextArea = new JTextArea();
        postfixTextArea.setEditable(false);
        JScrollPane postfixScrollPane = new JScrollPane(postfixTextArea);

        // Create labels
        resultLabel = new JLabel();
        resultLabel.setFont(resultLabel.getFont().deriveFont(Font.BOLD, 14));
        resultLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));

        // Create error message label
        errorMessageLabel = new JLabel();
        errorMessageLabel.setForeground(Color.RED);
        errorMessageLabel.setFont(errorMessageLabel.getFont().deriveFont(Font.BOLD));
        errorMessageLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));

        // Set font
        JPanel inputValuePanel = new JPanel(new GridLayout(4, 1));
        inputValuePanel.add(infixTextField);
        inputValuePanel.add(postfixScrollPane);
        inputValuePanel.add(resultLabel);
        inputValuePanel.add(errorMessageLabel);

        // Add components
        add(inputLabelPanel, BorderLayout.WEST);
        add(inputValuePanel, BorderLayout.CENTER);

        // Create calculate button
        JButton calculateButton = new JButton("Calculate");
        CalculateButtonListener calculateButtonListener = new CalculateButtonListener();
        calculateButton.addActionListener(calculateButtonListener);

        // Add calculate button
        add(calculateButton, BorderLayout.EAST);
    }

    private class CalculateButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String infixExpression = infixTextField.getText().trim();

            // Check if infix expression is empty
            if (!infixExpression.isEmpty()) {
                // Calculate postfix expression
                try {

                    // Convert infix expression to postfix expression
                    String postfixExpression = PostfixExpression.convertToPostfix(infixExpression);
                    long result = PostfixExpression.evaluatePostfix(postfixExpression);

                    // Update text area and label
                    postfixTextArea.setText(postfixExpression);
                    resultLabel.setText(String.format("Result:[ %d ]", result));
                    errorMessageLabel.setText(""); // Clear previous error message

                    // Update input label
                    JLabel infixInputLabel = (JLabel) ((JPanel) getComponent(0)).getComponent(0);
                    JLabel postfixLabel = (JLabel) ((JPanel) getComponent(0)).getComponent(1);
                    infixInputLabel.setText(String.format("Enter infix expression:[ %s ]", infixExpression));
                    postfixLabel.setText(String.format("Postfix expression:[ %s ]", postfixExpression));

                // Catch exceptions
                } catch (IllegalArgumentException ex) {
                    String errorMessage = ex.getMessage();
                    postfixTextArea.setText("");
                    resultLabel.setText("");
                    errorMessageLabel.setText(String.format("[ %s ]", errorMessage));
                
                // Catch arithmetic exceptions
                } catch (ArithmeticException ex) {
                    String errorMessage = ex.getMessage();
                    postfixTextArea.setText("");
                    resultLabel.setText("");
                    errorMessageLabel.setText(String.format(" [ %s ]", errorMessage));
                }
            }
        }
    }
}
