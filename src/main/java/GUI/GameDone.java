package GUI;

import Variables.Constant;

import javax.swing.*;
import java.awt.*;

import java.net.URL;
import java.util.Objects;

public class GameDone {
    Image gameDone;
    static GameDone instance;

    public static GameDone getInstance(){
        if(GameDone.instance == null){
            GameDone.instance = new GameDone();
        }
        return GameDone.instance;
    }

    GameDone() {
        URL url = Objects.requireNonNull(getClass().getResource("/Menu/GameDone.gif"));
        ImageIcon icon = new ImageIcon(url);
        gameDone = icon.getImage();
    }
    public void draw(Graphics2D g2) {
        g2.drawImage(gameDone, 0, 0, Constant.WIDTH, Constant.HEIGHT, null);
    }
}
