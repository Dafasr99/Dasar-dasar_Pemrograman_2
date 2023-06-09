import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MovePanel extends JPanel {
    private final int WIDTH = 600, HEIGHT = 400;

    private final int JUMP = 5; // number of pixels moved each step

    // the following give the initial parameters for the figure
    private final int START_CENTER = WIDTH / 2;
    private final int START_BOTTOM = HEIGHT - 40;
    private final int SIZE = HEIGHT / 2;

    private StickFigure stickMan;

    // constructor

    public MovePanel() {
        addKeyListener(new MoveListener());

        stickMan = new StickFigure(START_CENTER, START_BOTTOM, Color.blue, SIZE);

        setBackground(Color.black);
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setFocusable(true);
        
        setToolTipText("Anda dapat memanipulasi gambar orang ini dengan keyboard (tombol-tombol panah, g, s, u, d, l, r, dan m).");
    }

    // draw the figure

    public void paintComponent(Graphics page) {
        super.paintComponent(page);
        stickMan.draw(page);
    }

    // represents the listener for keyboard activity
    private class MoveListener implements KeyListener {
        // Handle a key-pressed event: arrow keys cause the figure to move horizontally or vertically; the g ket causes the figure to "grow", the s key causes the figure to shrink, the u key causes arms and legs to move up, m puts them in the middle, and d down.
        public void keyPressed(KeyEvent event) {
            switch (event.getKeyCode()) {
                case KeyEvent.VK_LEFT -> stickMan.move(-1 * JUMP, 0);
                case KeyEvent.VK_RIGHT -> stickMan.move(JUMP, 0);
                case KeyEvent.VK_UP -> stickMan.move(0, -1 * JUMP);
                case KeyEvent.VK_DOWN -> stickMan.move(0, JUMP);
                case KeyEvent.VK_G -> stickMan.grow(1.5);
                case KeyEvent.VK_S -> stickMan.grow(0.5);
                case KeyEvent.VK_U -> {
                    stickMan.setArmDirection("0");
                    stickMan.setArmPosition(60);
                    stickMan.setLegPosition(40);
                }
                case KeyEvent.VK_M -> {
                    stickMan.setArmDirection("0");
                    stickMan.setArmPosition(0);
                    stickMan.setLegPosition(20);
                }
                case KeyEvent.VK_D -> {
                    stickMan.setArmDirection("0");
                    stickMan.setArmPosition(-60);
                    stickMan.setLegPosition(10);
                }
                // press l to move the arm to the left
                case KeyEvent.VK_L -> stickMan.setArmDirection("l");
                // press r to move the arm to the right
                case KeyEvent.VK_R -> stickMan.setArmDirection("r");

                default -> {
                }
            }

            repaint();
        }

        // Define empty bodies for key event methods not used
        public void keyTyped(KeyEvent event) {}
        public void keyReleased(KeyEvent event) {}
    }
}
