package GUI;

import Controls.CollisionCheck;
import Controls.KeyHandler;
import Entity.Bomb;
import Entity.Boss;
import Entity.Mob;
import Entity.Player;
import Objects.SuperObject;

import java.awt.*;
import java.util.ArrayList;
import java.util.PropertyPermission;

public class GameScene extends Scene {
    boolean isPaused; //true = paused, false = not paused
    Pause pause;
    static int mapID;

    TileManager tileM;
    AssetSetter aSetter;

    public static CollisionCheck cCheck;
    public static SuperObject[] Object = new SuperObject[100];

    static Player player;
    static ArrayList<Mob> mobList = new ArrayList<>();
    public static int mobCounter;

    public Boss boss;

    public static ArrayList<Bomb> bombList;
    public static int bombSize;
    public static int bombCounter;
    public static int bombRadius;
    public BombExplodeMap bombExplodeMap;

    public static GameScene instance = null;
    public static GameScene getInstance(){
        if(GameScene.instance == null){
            GameScene.instance = new GameScene(mapID);
        }
        return GameScene.instance;
    }

    public GameScene(int mapID) {
        GameScene.mapID = mapID;
        player = new Player();
        boss = new Boss();

        tileM = TileManager.getInstance();
        aSetter = new AssetSetter(this);
        pause = Pause.getInstance(this);

        cCheck = new CollisionCheck();

        aSetter.setMob();
        aSetter.setItems();

        bombList = new ArrayList<>();
        bombExplodeMap = new BombExplodeMap();
        bombCounter = 0;
        bombSize = 2;
        bombRadius = 1;
    }

    @Override
    public void update() {
        pause.pauseGame();
        //update when not pause
        if (!isPaused) {
            player.update();

            tileM.update();

            if(mobList != null){
                for (Mob mob : mobList) {
                    mob.update();
                }
            }

            if(mapID == 2){
                if(boss.state == 1){
                    boss.update();
                }
            }
            if (CheckAvailable.plantBomb(player.getX(), player.getY())) {
                bombList.add(new Bomb(player.getX(), player.getY(), bombRadius, bombExplodeMap));
                bombCounter++;
            }
        }
        
        //Game over
        if (player.state == 0) {
            GameOver.getInstance().update();
            bombList.clear();
            bombCounter = 0;
            bombSize = 2;
        }
//        if (isGameDone()){
//            // Do Nothing
//        }
    }

    @Override
    public void draw(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        GameDone.getInstance().draw(g2);

        //Draw Map
        tileM.draw(g2);

        //Draw player
        player.draw(g2);

        if (mapID == 2){
            if(boss != null){
                boss.draw(g2);
            }
        }

        //Draw Items
        for (SuperObject superObject : Object) {
            if (superObject != null) {
                superObject.draw(g2);
            }
        }

        //Draw Bomb
        bombList.removeIf(b -> b.getState() == 2);
        if(bombList != null){
            for (Bomb b : bombList) {
                b.draw(g2);
            }
        }

        //Draw mob
        for (Mob value : mobList) {
            value.draw(g2);
        }

        //Draw pause menu
        if (isPaused) {
            pause.draw(g2);
        }

        //Draw Game Over
        if (player.state == 0) {
            GameOver.getInstance().draw(g2);
        }

//        Draw Game Done
        if (isGameDone()){
            GameDone.getInstance().draw(g2);
        }
    }
    public boolean isGameDone(){
        return GameScene.mobCounter == GameScene.getMobList().size();
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