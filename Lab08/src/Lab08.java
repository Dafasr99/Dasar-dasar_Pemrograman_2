import javax.swing.JFrame;
 //-----------------------------------------------------------------
 // Lab08.java

public class Lab08 {
 //-----------------------------------------------------------------
 // Creates and displays the application frame.
 //-----------------------------------------------------------------
    public static void main (String[] args) {
    JFrame circlesFrame = new JFrame ("Lab 08 DDP2-2023");
    circlesFrame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);

    circlesFrame.getContentPane().add (new CirclePanel());
    
    circlesFrame.pack();
    circlesFrame.setVisible(true);
    }
}