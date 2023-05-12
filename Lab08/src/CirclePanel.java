//*************************************************************************
// CirclePanel.java
// Represents the primary panel for the Lab08 program on which the
// circles are drawn.
//*************************************************************************

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class CirclePanel extends JPanel {
    private final int WIDTH = 600, HEIGHT = 400;
    private Circle circle;
    private int dragOffsetX;
    private int dragOffsetY;

    //-----------------------------------------------------------------
    // Sets up this panel to listen for mouse events.
    //-----------------------------------------------------------------

    public CirclePanel() {
        CirclesListener listener = new CirclesListener();
        addMouseListener(listener);
        addMouseMotionListener(listener);
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
    }

    //-----------------------------------------------------------------
    // Draws the current circle, if any.
    //-----------------------------------------------------------------

    public void paintComponent(Graphics page) {
        super.paintComponent(page);
        if (circle != null)
            circle.draw(page);
    }

    //*****************************************************************
    // Represents the listener for mouse events.
    //*****************************************************************

    private class CirclesListener implements MouseListener, MouseMotionListener {

        //--------------------------------------------------------------
        // Creates a new circle at the current location whenever the
        // mouse button is clicked and repaints.
        //--------------------------------------------------------------

        public void mouseClicked(MouseEvent event) {
            if (circle == null) {
                circle = new Circle(event.getPoint());
            } else if (circle.isInside(event.getPoint())) {
                circle = null;
            } else {
                circle.move(event.getPoint());
            }
            repaint();
        }

        //--------------------------------------------------------------
        // Records the point at which the mouse button is pressed.
        //--------------------------------------------------------------

        public void mousePressed(MouseEvent event) {
            if (circle != null && circle.isInside(event.getPoint())) {
                dragOffsetX = event.getX() - circle.getCenterX();
                dragOffsetY = event.getY() - circle.getCenterY();
            }
        }

        //--------------------------------------------------------------
        // Provide empty definitions for unused event methods.
        //--------------------------------------------------------------

        public void mouseReleased(MouseEvent event) {
        }

        public void mouseEntered(MouseEvent event) {
            setBackground(Color.WHITE);
        }

        public void mouseExited(MouseEvent event) {
            setBackground(Color.BLACK);
        }

        //--------------------------------------------------------------
        // Moves the circle when the mouse is dragged.
        //--------------------------------------------------------------

        public void mouseDragged(MouseEvent event) {
            if (circle != null) {
                // Calculate new x and y coordinates for circle
                int newX = event.getX() - dragOffsetX; 
                int newY = event.getY() - dragOffsetY;
                circle.move(new Point(newX, newY));
                repaint();
            }
        }


        //--------------------------------------------------------------
        // Provide empty definition for unused event method.
        //--------------------------------------------------------------

        public void mouseMoved(MouseEvent event) {
        }
    }
}
