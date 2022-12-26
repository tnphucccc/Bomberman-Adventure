package Controls;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

public class MouseHandler extends MouseAdapter implements MouseMotionListener {
    public boolean isPressed = false;
    public double x = 0.0, y = 0.0;

    public void mousePressed(MouseEvent e) {
        isPressed = true;
    }

    public void mouseReleased(MouseEvent e) {
        isPressed = false;
    }

    public void mouseMoved(MouseEvent e) {
        this.x = e.getX();
        this.y = e.getY();
    }

    public boolean checkInteractWithRect(MouseHandler mouseH, Rectangle rect){
        return mouseH.getX() >= rect.x && mouseH.getX() <= rect.x + rect.width &&
                mouseH.getY() >= rect.y && mouseH.getY() <= rect.y + rect.height;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }
}