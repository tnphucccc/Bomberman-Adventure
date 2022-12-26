package GUI;

import Controls.CollisionCheck;
import Controls.KeyHandler;
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

    KeyHandler keyH = Window.getKeyH();

    public static Player player;

    public static int bombSize = 100;
    public static int bombCounter = 0;

    public static int mapID;

    boolean spacePressed = false;
    static ArrayList<Bomb> bombList;
    static ArrayList<Mob> mobList = new ArrayList<>(3);

    AssetSetter aSetter = new AssetSetter(this);
    TileManager tileM;

    public GameScene(int mapID) {
        GameScene.mapID = mapID;

        cCheck = new CollisionCheck();
        tileM = new TileManager();
        player = new Player(1);

        aSetter.setMob();
        aSetter.setItems();

        bombList = new ArrayList<>();
        bombSize = 1000;
        bombCounter = 0;
        
        pause = new Pause(false);
        gameOver = new GameOver();
    }

//    public static GameScene instance = null;
//    public static GameScene getInstance(){
//        if(GameScene.instance == null){
//            GameScene.instance = new GameScene(1);
//        }
//        return GameScene.instance;
//    }

    @Override
    public void update() {
        pause.pauseGame();
        gameOver.checkAlive(player.state);

        //update when not pause
        if (!pause.isPaused) {
            player.update();

            tileM.update();
            if(mobList != null){
                for (Mob mob : mobList) {
                    mob.update();
                }
            }
            System.out.println("Bomb: "+(bombSize-bombCounter));
            // bomb.update(player.x, player.y);
            // bombList = bomb.getBombList();
            if (bombCounter < bombSize) {
                if (keyH.spacePressed) {
                    spacePressed = true;
                }
                if (!keyH.spacePressed && spacePressed ) {
                    spacePressed = false;
                    if (CheckAvailable.checkAvailable(player.x, player.y)) {
                        bombList.add(bombCounter, new Bomb());
                        bombList.get(bombCounter).update(player.x, player.y);
                        bombCounter++;
                    }
                }
            }
        }
        
        //Game over
        if (!gameOver.isAlive) {
            gameOver.update();
            bombList.clear();
            bombCounter = 0;
            bombSize = 10;
        }

        if (MapTransitionMenu.getInstance().isTransitioning) {
            MapTransitionMenu.getInstance().update();
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

        //Draw pause menu
        if (pause.isPaused) {
            Overlay.getInstance().draw(g2);
            pause.draw(g2);
        }
        if (!gameOver.isAlive) {
            Overlay.getInstance().draw(g2);
            gameOver.draw(g2);
        }

        if(MapTransitionMenu.getInstance().getisTransitioning()){
            Overlay.getInstance().draw(g2);
            MapTransitionMenu.getInstance().draw(g2);
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

    public static int getMapID(){
        return mapID;
    }

}