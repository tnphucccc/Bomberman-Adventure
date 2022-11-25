package GUI;

import Variables.Constant;

import java.awt.*;

public class OverLay {
    OverLay() {
        //OverLay
        //Draws the overlay
    }

    public void draw(Graphics g2) {
        g2.setColor(new Color(0, 0, 0, 150));
        g2.fillRect(0, 0, Constant.WIDTH, Constant.HEIGHT);
    }
}
