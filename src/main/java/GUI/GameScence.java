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
    ArrayList<Bomb> bombList;

    collisionCheck cCheck;
    AssetSetter aSetter = new AssetSetter(this);
    tileManager tileM;

    public GameScence(keyHandler keyH, mouseHandler mouseH) {
        this.keyH = keyH;
        this.mouseH = mouseH;

        player = new Player(keyH);
        cCheck = new collisionCheck();
        tileM = new tileManager();

        mob = new Mob[3];
        aSetter.setMob();

        bomb = new Bomb(keyH);
        bombList = bomb.getBombList();
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
        bombList = bomb.getBombList();
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
        if(bombList != null){
            for(Bomb b : bombList){
                b.draw(g2);
            }
        }
    }
}