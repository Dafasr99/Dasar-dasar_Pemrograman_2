import javax.swing.JFrame;

public class MoveStickMan {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Moving a Stick Man with the Keyboard");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.add (new MovePanel ());

        frame.pack();
        frame.setVisible(true);
    }
}

