import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {

        JFrame frame = new JFrame("Postfix Calculator");
        
        SwingUtilities.invokeLater(() -> {
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(400, 200);
            frame.setContentPane(new CalculationPanel());
            frame.setVisible(true);
        });
    }
}