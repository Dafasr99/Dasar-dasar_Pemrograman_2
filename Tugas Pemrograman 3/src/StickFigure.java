import java.awt.*;

public class StickFigure {
    private int baseX; // center of the figure 
    private int baseY; // bottom of feet 
    private Color color; // color of the figure
    private int height; // height of the figure
    private int headW; // width of the head
    private int legLength; // length of the legs
    private int legPosition; // # pixels the legs are up from vertical
    private int armLength; // horizontal length of the arms
    private int armToFloor; // distance from base to arms
    private int armPosition; // # pixels arm is above/below horizontal

    // Construct a stick figure given its four attributes
    public StickFigure(int center, int bottom, Color shade, int size) {
        baseX = center;
        baseY = bottom;
        color = shade;
        height = size;

        // Define body positions proportional to height
        headW = height / 5;
        legLength = height / 2;
        armToFloor = 2 * height / 3;
        armLength = height / 3;

        // Set initial position of arms and legs
        legPosition = 25;
        armPosition = -15;
    }

    // Draw the figure
    public void draw(Graphics page) {
        // Compute y-coordinate of top of head
        int top = baseY - height;

        page.setColor(color);

        // Draw head
        page.drawOval(baseX - headW / 2, top, headW, headW);

        // Draw the trunk
        page.drawLine(baseX, top + headW, baseX, baseY - legLength);

        // Draw the legs
        page.drawLine(baseX, baseY - legLength, baseX - legPosition, baseY);
        page.drawLine(baseX, baseY - legLength, baseX + legPosition, baseY);

        // Draw the arms
        int startY = baseY - armToFloor;
        page.drawLine(baseX, startY, baseX - armLength, startY - armPosition);
        page.drawLine(baseX, startY, baseX + armLength, startY - armPosition);
    }

    // Move the figure - first parameter gives the number of pixels over (to the right if over is positive, to the left if over is negative) and up or down
    // (down if the parameter down is positive, up if it is negative)
    public void move(int over, int down) {
        baseX += over;
        baseY += down;
    }

    // Increase the height by the given factor (if the factor is > 1 the figure will "grow" else it will shrink)
    public void grow(double factor) {
        height = (int) (height * factor);

        // Reset body parts proportional to new height
        headW = height / 5;
        legLength = height / 2;
        armToFloor = 2 * height / 3;
        armLength = height / 3;
    }

    // Set the legPosition (dist. from vertical) to a new value
    public void setLegPosition(int newPosition) {
        legPosition = newPosition;
    }

    // Set the armPosition to a new value
    public void setArmPosition(int newPos) {
        armPosition = newPos;
    }
}
