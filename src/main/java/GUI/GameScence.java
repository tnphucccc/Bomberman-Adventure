package GUI;

import Controls.*;
import Entity.*;

import java.awt.*;
import java.util.ArrayList;

public class GameScence extends Scence {
    keyHandler keyH;
    mouseHandler mouseH;

    Player player;
    Mob[] mob;
    Bomb bomb;

    public collisionCheck cCheck;
    public AssetSetter aSetter = new AssetSetter(this);
    tileManager tileM;
    ArrayList<Bomb> bombList;

    public GameScence(keyHandler keyH, mouseHandler mouseH) {
        this.keyH = keyH;
        this.mouseH = mouseH;

        player = new Player(keyH);
        cCheck= new collisionCheck();
        mob = new Mob[3];
        aSetter.setMob();
        tileM = new tileManager();
        bombList = new ArrayList<>();
        bomb = new Bomb(keyH);
    }

    @Override
    public void update(double dt) {
        player.update(dt);
        for(int i=0;i<mob.length;i++){
            if(mob[i]!=null){
                mob[i].update(dt);
            }
        }
        bomb.update(player.x, player.y);
    }

    @Override
    public void draw(Graphics g) {
        Graphics2D g2 = (Graphics2D)g;

        tileM.draw(g2);
        player.draw(g2);
        for(int i=0;i< mob.length;i++){
            if(mob[i]!=null){
                mob[i].draw(g2);
            }
        }
        for(Bomb b : bombList){
            b.draw(g2);
        }
    }
}

