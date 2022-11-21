package GUI;

import Controls.CollisionCheck;
import Controls.KeyHandler;
import Controls.MouseHandler;

import Entity.Bomb;
import Entity.Mob;
import Entity.Player;
import Objects.SuperObject;

import java.awt.*;
import java.util.ArrayList;

public class GameScene extends Scene {
    Pause pause;

    Mob[] mob;
    KeyHandler keyH;
    MouseHandler mouseH;
    Player player;
    Bomb bomb;
    ArrayList<Bomb> bombList;

    CollisionCheck cCheck;
    AssetSetter aSetter = new AssetSetter(this);
    TileManager tileM;
    SuperObject[] Object = new SuperObject[10];


    public GameScene(KeyHandler keyH, MouseHandler mouseH) {
        this.keyH = keyH;
        this.mouseH = mouseH;

        player = new Player(keyH);
        cCheck = new CollisionCheck();
        tileM = new TileManager();

        mob = new Mob[3];
        aSetter.setMob();
        aSetter.setItems();

        bomb = new Bomb(keyH);
        bombList = bomb.getBombList();

        pause = new Pause(false, keyH);
    }

    @Override
    public void update(double dt) {
        pause.pauseGame();

        if (!pause.isPaused){
            player.update(dt);

            for (Mob value : mob) {
                if (value != null) {
                    value.update(dt);
                    cCheck.checkMob(player, value);
                }
            }

            bomb.update(player.x, player.y);
            bombList = bomb.getBombList();

        } else {
            // Do nothing
        }
    }

    @Override
    public void draw(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;

        tileM.draw(g2);
        player.draw(g2);

        for (SuperObject superObject : Object) {
            if (superObject != null) {
                superObject.draw(g2);
            }
        }
        for (Mob value : mob) {
            if (value != null) {
                value.draw(g2);
            }
        }
        if (bombList != null) {
            for (Bomb b : bombList) {
                b.draw(g2);
            }
        }

        if(pause.isPaused){
            g2.drawImage(pause.pause, 254, 235,308, 57, null);
        }
    }





}
