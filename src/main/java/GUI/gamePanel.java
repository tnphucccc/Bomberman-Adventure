package GUI;

import Controls.collisionCheck;
import Controls.keyHandler;
import Entity.Mob;
import Entity.Player;

import javax.swing.*;
import java.awt.*;

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
    Player player = new Player(this, keyH);
    Mob mob1 = new Mob(this,240,224);
    Mob mob2 = new Mob(this,624,32);

    public gamePanel() {
        this.setPreferredSize(new Dimension(WIDTH*scale, HEIGHT*scale));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
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
        mob1.update();
        mob2.update();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        tileM.draw(g2);
        player.draw(g2);
        mob1.draw(g2);
        mob2.draw(g2);
        g2.dispose();
    }
}
