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
    public static CollisionCheck cCheck;
    public static SuperObject[] Object = new SuperObject[10];
    Pause pause;
    GameOver gameOver;
    Overlay overLay;
    Mob[] mob;
    KeyHandler keyH;
    MouseHandler mouseH;
    Player player;
    Bomb bomb;
    static ArrayList<Bomb> bombList;
    AssetSetter aSetter = new AssetSetter(this);
    TileManager tileM;


    public GameScene(KeyHandler keyH, MouseHandler mouseH) {
        this.keyH = keyH;
        this.mouseH = mouseH;

        player = new Player(keyH, 1);
        cCheck = new CollisionCheck();
        tileM = new TileManager();

        mob = new Mob[3];
        aSetter.setMob();
        aSetter.setItems();

        bomb = new Bomb(keyH);
        bombList = bomb.getBombList();

        pause = new Pause(false, keyH);
        gameOver = new GameOver(mouseH);
        overLay = new Overlay();
    }

    @Override
    public void update(double dt) {
        pause.pauseGame();
        gameOver.checkAlive(player.state);

        if (!pause.isPaused) {
            //Game is running
            player.update(dt);

            for (Mob value : mob) {
                if (value != null) {
                    value.update(dt);
                    cCheck.checkMob(player, value);
                    if (player.state==0){
                        value.speed=0;
                    }

                }
            }

            bomb.update(player.x, player.y);
            bombList = bomb.getBombList();
            cCheck.checkBomb(GameScene.getBombList(), player);

        }  // Do nothing

        if (!gameOver.isAlive) {
            //Game over
            gameOver.update(dt);
        }
    }

    @Override
    public void draw(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        tileM.draw(g2);
        player.draw(g2);

        if (bombList != null) {
            for (Bomb b : bombList) {
                b.draw(g2);
            }
        }

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
        

        //Draw if the game is paused
        if (pause.isPaused) {
            overLay.draw(g2);
            pause.draw(g2);
        }
        if (!gameOver.isAlive) {
            overLay.draw(g2);
            gameOver.draw(g2);
        }
    }
    public static ArrayList<Bomb> getBombList() {
        return bombList;
    }
}
