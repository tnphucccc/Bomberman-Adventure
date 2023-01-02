package GUI;

import Controls.CheckAvailable;
import Entity.*;
import Objects.SuperObject;

import java.awt.*;
import java.util.ArrayList;


public class GameScene extends Scene {
    boolean isPaused; //true = paused, false = not paused
    Pause pause;
    TileManager tileM;
    AssetSetter assetSetter;

    static SuperObject[] Object = new SuperObject[100];
    static Player player;

    static ArrayList<Mob> mobList = new ArrayList<>();
    static int mobCounter;

    static Boss boss;

    static ArrayList<Bomb> bombList;
    static int bombSize;
    static int bombCounter;
    static int bombRadius;
    BombExplodeMap bombExplodeMap;


    public GameScene() {
        player = new Player(Window.getWindow().getCurrentMapID());
        boss = new Boss();

        tileM = TileManager.getInstance();
        assetSetter = new AssetSetter(this);
        pause = new Pause(this);

        bombList = new ArrayList<>();
        bombExplodeMap = new BombExplodeMap();
        bombCounter = 0;
        bombSize = 2;
        bombRadius = 1;
    }

    @Override
    public void update() {
        pause.pauseGame(); //Pause game
        if (!isPaused) {
            player.update(); //update Player

            tileM.update(); //update Tile

            if(mobList != null){
                for (Mob mob : mobList) {
                    mob.update(); //update Mob
                }
            }

            if(Window.getWindow().getCurrentMapID() == 2){
                if(boss.state == 1){
                    boss.update(); //update Boss
                }
            }
            if (CheckAvailable.plantBomb(player.getX(), player.getY())) { //update Bomb
                bombList.add(new Bomb(player.getX(), player.getY(), bombRadius, bombExplodeMap));
                bombCounter++;
            }
        }

        if (player.state == 0) { //Game Over
            GameOver.getInstance().update();
            bombList.clear();
            bombCounter = 0;
            bombSize = 2;
        }
        if (isGameDone()){
            FinishMenu.getInstance().update();
            bombList.clear();
            bombCounter = 0;
            bombSize = 2;
        }
    }

    @Override
    public void draw(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;

        tileM.draw(g2); //draw Tile

        player.draw(g2); //draw Player

        if (Window.getWindow().currentMapID == 2) {
            if (boss != null) {
                boss.draw(g2); //draw Boss
            }
        }

        for (SuperObject superObject : Object) {
            if (superObject != null) {
                superObject.draw(g2); //Draw Items
            }
        }

        bombList.removeIf(b -> b.getState() == 2);
        if (bombList != null) {
            for (Bomb b : bombList) {
                b.draw(g2); //Draw Bomb
            }
        }

        for (Mob value : mobList) {
            value.draw(g2); //Draw Mob
        }

        if (isPaused) {
            pause.draw(g2); //Draw Pause Menu
        }
        if (player.state == 0) {
            GameOver.getInstance().draw(g2); //Draw Game Over Menu
        }
        if (isGameDone()) {
            FinishMenu.getInstance().draw(g2); //Draw Game Done Menu
        }
    }

    public static boolean mobClear(int mobSize) { // Check if all mobs are dead
        mobCounter = mobSize;
        for (Mob mob : mobList) {
            if (mob.state == 0) {
                mobCounter--;
            }
        }
        return mobCounter==0;
    }

    public boolean isGameDone() { //Check if Game is Finished
        return Window.getWindow().getCurrentMapID() == 2 && mobClear(mobList.size()) && boss.state == 0;
    }

    //getter and setter
    public static ArrayList<Bomb> getBombList() {
        return bombList;
    }

    public static Player getPlayer(){
        return player;
    }
    public static ArrayList<Mob> getMobList(){
        return mobList;
    }

    public static int getBombCounter() {
        return bombCounter;
    }
    public static int getBombSize() {
        return bombSize;
    }
    public static void setBombSize(int bombSize) {
        GameScene.bombSize = bombSize;
    }
    public static int getBombRadius() {
        return bombRadius;
    }
    public static void setBombRadius(int bombRadius) {
        GameScene.bombRadius = bombRadius;
    }
    public static void setBombCounter(int bombCounter) {
        GameScene.bombCounter = bombCounter;
    }
    public static void setMobCounter(int mobCounter) {
        GameScene.mobCounter = mobCounter;
    }
    public static SuperObject[] getObject() {
        return Object;
    }
}