package GUI;

import Controls.KeyHandler;
import Controls.MouseHandler;
import Variables.Constant;

import javax.swing.*;
import java.awt.*;

public class Window extends JFrame implements Runnable {
    public static Window window = null;
    public boolean isRunning;

    public int currentState;
    public Scene currentScene;

    static KeyHandler keyH = new KeyHandler();
    static MouseHandler mouseH = new MouseHandler();

    public Window(int width, int height, String TITLE) {
        //Window handler
        setSize(width, height);
        setTitle(TITLE);
        setIconImage(new ImageIcon("src\\main\\resources\\Logo-1.png").getImage());

        setResizable(false);
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        addKeyListener(keyH);
        addMouseListener(mouseH);
        addMouseMotionListener(mouseH);

        //0 is original, 1 is game,
        changeState(0);

        isRunning = true;
    }


    public static Window getWindow() {
        if (Window.window == null) {
            Window.window = new Window(Constant.WIDTH, Constant.HEIGHT, Constant.TITLE);
        }
        return Window.window;
    }

    public void changeState(int newState) {
        currentState = newState;
        switch (currentState) {
            case 0 -> currentScene = new MenuScene();
            case 1 -> currentScene = new GameScene(1);
            case 2 -> currentScene = new GameScene(2);

            default -> {
                System.out.println("Error: Invalid state");
                currentScene = null;
            }
        }
    }

    public void close() {
        isRunning = false;
    }


    public void update() {
        Image dbImage = createImage(getWidth(), getHeight());
        Graphics dbg = dbImage.getGraphics();
        this.draw(dbg);
        getGraphics().drawImage(dbImage, 0, 0, this);

        currentScene.update();
    }

    public void draw(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;

        currentScene.draw(g2);
    }

    public void run() {
        double drawInterval = 1000000000.0 / Constant.FPS, delta = 0;
        long lastTime = System.nanoTime(), currentTime, timer = 0;
        int Count = 0;

        while (isRunning) {
            currentTime = System.nanoTime();

            delta += (currentTime - lastTime) / drawInterval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;

            if (delta >= 1) {
                update();
                delta--;
                Count++;
            }

            if (timer >= 1000000000) {
//                System.out.println("FPS: " + Count);
                Count = 0;
                timer = 0;
            }
        }
        this.dispose();
    }
    public static KeyHandler getKeyH() {
        return keyH;
    }
    public static MouseHandler getMouseH() {
        return mouseH;
    }
}
