package GUI;

import Controls.*;
import Entity.*;

import java.awt.*;

public class GameScence extends Scence {
    keyHandler keyH;
    mouseHandler mouseH;

    Player player;
    collisionCheck cCheck;
    tileManager tileM;

    public GameScence(keyHandler keyH, mouseHandler mouseH) {
        this.keyH = keyH;
        this.mouseH = mouseH;

        player = new Player(keyH);
        cCheck = new collisionCheck();
        tileM = new tileManager();
    }

    @Override
    public void update(double dt) {
        player.update(dt);
    }

    @Override
    public void draw(Graphics g) {
        Graphics2D g2 = (Graphics2D)g;

        tileM.draw(g2);
        player.draw(g2);
    }
}
