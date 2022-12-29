package GUI;

import Variables.Constant;

import javax.swing.*;
import java.awt.*;

import java.net.URL;
import java.util.Objects;

public class GameDone extends Scene {
    Image gameDone;

    GameDone() {
        URL url = Objects.requireNonNull(getClass().getResource("/Menu/GameDone.gif"));
        ImageIcon icon = new ImageIcon(url);
        gameDone = icon.getImage();
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(gameDone, 0, 0, Constant.WIDTH, Constant.HEIGHT, null);
    }

    @Override
    public void update() {

    }
}
