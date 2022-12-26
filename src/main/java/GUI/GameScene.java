package GUI;

import Controls.CollisionCheck;
import Controls.KeyHandler;
import Entity.Bomb;
import Entity.Mob;
import Entity.Player;
import Objects.SuperObject;

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
public class GameScene extends Scene {
    KeyHandler keyH = Window.getKeyH();
    boolean isPaused; //true = paused, false = not paused
    static int mapID;
    Player player;
    TileManager tileM;
    AssetSetter aSetter;

    public static CollisionCheck cCheck;
    public static SuperObject[] Object = new SuperObject[10];

    public static int bombSize = 2;
    public static int bombCounter = 0;

    boolean spacePressed = false;
    static ArrayList<Bomb> bombList;
    static ArrayList<Mob> mobList = new ArrayList<>(3);


    public static GameScene instance = null;
    public static GameScene getInstance(){
        if(GameScene.instance == null){
            GameScene.instance = new GameScene(mapID);
        }
        return GameScene.instance;
    }

    public GameScene(int mapID) {
        GameScene.mapID = mapID;
        player = Player.getInstance();
        tileM = TileManager.getInstance();
        aSetter = new AssetSetter(this);

        cCheck = new CollisionCheck();
        tileM = new TileManager();

        aSetter.setMob();
        aSetter.setItems();

        bombList = new ArrayList<>();
        bombSize = 2;
        bombCounter = 0;
    }

    @Override
    public void update() {
        Pause.getInstance(this).pauseGame();
        //update when not pause
        if (!isPaused) {
            player.update();
            tileM.update();
            if(mobList != null){
                for (Mob mob : mobList) {
                    mob.update();
                }
            }
            System.out.println("Bomb: "+(bombCounter));
            // bomb.update(player.x, player.y);
            // bombList = bomb.getBombList();
            if (bombCounter < bombSize) {
                if (keyH.spacePressed) {
                    spacePressed = true;
                }
                if (!keyH.spacePressed && spacePressed ) {
                    spacePressed = false;
                    if (CheckAvailable.checkAvailable(player.x, player.y)) {
                        bombList.add(new Bomb(player.x, player.y,1));
                        //bombList.get(bombCounter).update(player.x, player.y);
                        bombCounter++;

                    }
                }
            }
        }
        
        //Game over
        if (player.state == 0) {
            GameOver.getInstance().update();
            bombList.clear();
            bombCounter = 0;
            bombSize = 2;
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
        Iterator<Bomb> itr = bombList.iterator();
        while (itr.hasNext()) {
            Bomb b = itr.next();
            if(b.getState()==2){
                itr.remove();
            }
        }
        if(bombList != null){
            for (Bomb b : bombList) {
                b.draw(g2);
            }
        }
        System.out.println(bombList);

        //Draw mob
        for (Mob value : mobList) {
            value.draw(g2);
        }

        //Draw pause menu
        if (isPaused) {
            Pause.getInstance(this).draw(g2);
        }
        if (player.state == 0) {
            GameOver.getInstance().draw(g2);
        }
    }
    public static ArrayList<Bomb> getBombList() {
        return bombList;
    }
    public Player getPlayer(){
        return player;
    }
    public static ArrayList<Mob> getMobList(){
        return mobList;
    }

    public static int getMapID(){
        return mapID;
    }
}