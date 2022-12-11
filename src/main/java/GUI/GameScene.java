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

    public static Player player;
    Bomb bomb;
    public static int bombSize = 100;
    public static int bombCounter = 0;
    KeyHandler keyH = Window.getKeyH();
    boolean spacePressed = false;
    static ArrayList<Bomb> bombList;
    static ArrayList<Mob> mobList = new ArrayList<>(3);

    AssetSetter aSetter = new AssetSetter(this);
    TileManager tileM;

    public GameScene() {
        cCheck = new CollisionCheck();
        tileM = new TileManager();
        player = new Player(1);

        aSetter.setMob();
        aSetter.setItems();

        bomb = new Bomb();
        bombList = bomb.getBombList();

        pause = new Pause(false);
        gameOver = new GameOver();
    }

    public static GameScene instance = null;
    public static GameScene getInstance(){
        if(GameScene.instance == null){
            GameScene.instance = new GameScene();
        }
        return GameScene.instance;
    }

    @Override
    public void update() {
        pause.pauseGame();
        gameOver.checkAlive(player.state);

        if (!pause.isPaused) {
            //Game is running
            player.update();
            for (Mob mob : mobList) {
                mob.update();
            }

            tileM.update();

            if (bombCounter < bombSize) {
                if (keyH.spacePressed) {
                    spacePressed = true;
                }
                if (!keyH.spacePressed && spacePressed) {
                    spacePressed = false;
                    if (CheckAvailable.checkAvailable(player.x, player.y)) {
                        bombList.add(bombCounter, new Bomb());
                        bombList.get(bombCounter).update(player.x, player.y);
                        bombCounter++;
                    }
                }
            }
        }  // Do nothing

        //Game over
        if (!gameOver.isAlive) {
            gameOver.update();
        }
    }

    @Override
    public void draw(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;

        //Draw Map
        tileM.draw(g2);

        //Draw player
        player.draw(g2);

        //Draw Items
        for (SuperObject superObject : Object) {
            if (superObject != null) {
                superObject.draw(g2);
            }
        }

        //Draw Bomb
        if (bombList != null) {
            for (Bomb b : bombList) {
                b.draw(g2);
            }
        }

        //Draw mob
        for (Mob value : mobList) {
                value.draw(g2);
        }

        //Draw if the game is paused
        if (pause.isPaused) {
            Overlay.getInstance().draw(g2);
            pause.draw(g2);
        }
        if (!gameOver.isAlive) {
            Overlay.getInstance().draw(g2);
            gameOver.draw(g2);
        }
    }
    public static ArrayList<Bomb> getBombList() {
        return bombList;
    }
    public static Player getPlayer(){
        return player;
    }
    public static ArrayList<Mob> getMobList(){
        return mobList;
    }

}