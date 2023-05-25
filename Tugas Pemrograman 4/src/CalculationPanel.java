import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CalculationPanel extends JPanel {
    private JTextField infixTextField;
    private JTextArea postfixTextArea;
    private JLabel resultLabel;
    private JLabel errorMessageLabel;

    public CalculationPanel() {
        setLayout(new BorderLayout());

        JLabel infixInputLabel = new JLabel("Enter infix expression:");
        JLabel postfixLabel = new JLabel("Postfix expression:");
        resultLabel = new JLabel();
        JLabel errorLabel = new JLabel("Error message:");

        JPanel inputLabelPanel = new JPanel(new GridLayout(4, 1));
        inputLabelPanel.add(infixInputLabel);
        inputLabelPanel.add(postfixLabel);
        inputLabelPanel.add(resultLabel);
        inputLabelPanel.add(errorLabel);

        infixTextField = new JTextField();
        infixTextField.setPreferredSize(new Dimension(200, 25));

        postfixTextArea = new JTextArea();
        postfixTextArea.setEditable(false);
        JScrollPane postfixScrollPane = new JScrollPane(postfixTextArea);

        resultLabel = new JLabel();
        resultLabel.setFont(resultLabel.getFont().deriveFont(Font.BOLD, 14));
        resultLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));

        errorMessageLabel = new JLabel();
        errorMessageLabel.setForeground(Color.RED);
        errorMessageLabel.setFont(errorMessageLabel.getFont().deriveFont(Font.BOLD));
        errorMessageLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));

        JPanel inputValuePanel = new JPanel(new GridLayout(4, 1));
        inputValuePanel.add(infixTextField);
        inputValuePanel.add(postfixScrollPane);
        inputValuePanel.add(resultLabel);
        inputValuePanel.add(errorMessageLabel);

        add(inputLabelPanel, BorderLayout.WEST);
        add(inputValuePanel, BorderLayout.CENTER);

        JButton calculateButton = new JButton("Calculate");
        CalculateButtonListener calculateButtonListener = new CalculateButtonListener();
        calculateButton.addActionListener(calculateButtonListener);

        add(calculateButton, BorderLayout.EAST);
    }

    private class CalculateButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String infixExpression = infixTextField.getText().trim();

            if (!infixExpression.isEmpty()) {
                try {
                    String postfixExpression = PostfixExpression.convertToPostfix(infixExpression);
                    long result = PostfixExpression.evaluatePostfix(postfixExpression);

                    postfixTextArea.setText(postfixExpression);
                    resultLabel.setText(String.format("Result:[ %d ]", result));
                    errorMessageLabel.setText(""); // Clear previous error message

                    // Update labels
                    JLabel infixInputLabel = (JLabel) ((JPanel) getComponent(0)).getComponent(0);
                    JLabel postfixLabel = (JLabel) ((JPanel) getComponent(0)).getComponent(1);
                    infixInputLabel.setText(String.format("Enter infix expression:[ %s ]", infixExpression));
                    postfixLabel.setText(String.format("Postfix expression:[ %s ]", postfixExpression));
                } catch (IllegalArgumentException ex) {
                    String errorMessage = ex.getMessage();
                    postfixTextArea.setText("");
                    resultLabel.setText("");
                    errorMessageLabel.setText(String.format("[ %s ]", errorMessage));
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
