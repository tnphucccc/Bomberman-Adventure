package GUI;

import Controls.collisionCheck;
import Controls.keyHandler;
import Entity.Entity;
import Entity.Mob;
import Entity.Player;

import javax.swing.*;
import java.awt.*;
import Entity.Bomb;
public class gamePanel extends JPanel implements Runnable {
    public static final int scale = 3;
    public final int original_tile_size = 16,
            maxScreenCol = 15,
            maxScreenRow = 9,
            WIDTH = (original_tile_size * maxScreenCol),
            HEIGHT = (original_tile_size * maxScreenRow),
            FPS = 60;

    public tileManager tileM = new tileManager(this);
    keyHandler keyH = new keyHandler();
    Thread gameThread;
    public collisionCheck cCheck = new collisionCheck(this);
    public AssetSetter aSetter = new AssetSetter(this);
    Player player = new Player(this, keyH);

    Bomb bomb = new Bomb(this, keyH);

    Entity[] mob = new Entity[3];


    public gamePanel() {
        this.setPreferredSize(new Dimension(WIDTH*scale, HEIGHT*scale));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }
    public void setupMap(){
        aSetter.setMob();
    }
    public void start_gameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }
    @Override
    public void run() {
        double drawInterval = 1000000000 / FPS, delta = 0;
        long lastTime = System.nanoTime(), currentTime, timer = 0;
        int Count = 0;

        while (gameThread != null) {
            currentTime = System.nanoTime();

            delta += (currentTime - lastTime) / drawInterval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;

            if (delta >= 1) {
                update();
                repaint();
                delta--;
                Count++;
            }

            if (timer >= 1000000000) {
                System.out.println("FPS: " + Count);
                Count = 0;
                timer = 0;
            }
        }
    }
    public void update() {
        player.update();

        bomb.update(player.getX(),player.getY());

        //MOB
        for(int i=0;i<mob.length;i++){
            if(mob[i]!=null){
                mob[i].update();
            }
        }

    }
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        //TILE
        tileM.draw(g2);
        //MOB
        for(int i=0;i< mob.length;i++){
            if(mob[i]!=null){
                mob[i].draw(g2);
            }
        }
        //PLAYER
        player.draw(g2);
        bomb.draw(g2);
        g2.dispose();
    }
}
