package GUI;

import Controls.MouseHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class GameOver {
    boolean isAlive;

    BufferedImage gameOver, playAgain, playAgainPressed, exit, exitPressed;
    BufferedImage playAgainCurrentImage, exitCurrentImage;

    Rect gameOverRect, playAgainRect, exitRect;
    MouseHandler mouseH = Window.getMouseH();

    public static GameOver instance = null;

    public static GameOver getInstance(){
        if (GameOver.instance == null){
            GameOver.instance = new GameOver();
        }
        return GameOver.instance;
    }

    public GameOver() {

        try {
            BufferedImage spriteSheet1 = ImageIO.read(new File("src/main/resources/Menu/Game_Over.png"));
            gameOver = spriteSheet1.getSubimage(0, 0, 531, 69);

            BufferedImage spriteSheet2 = ImageIO.read(new File("src/main/resources/Menu/Exit_Play.png"));
            exit = spriteSheet2.getSubimage(0, 70, 175, 53);
            exitPressed = spriteSheet2.getSubimage(208, 69, 175, 53);

            BufferedImage spriteSheet3 = ImageIO.read(new File("src/main/resources/Menu/Play_Again.png"));
            playAgain = spriteSheet3.getSubimage(0, 68, 440, 55);
            playAgainPressed = spriteSheet3.getSubimage(462, 68, 440, 55);

        } catch (Exception e) {
            e.printStackTrace();
        }
        playAgainCurrentImage = playAgain;
        exitCurrentImage = exit;

        //Game Over Menu Location
        gameOverRect = new Rect(150, 100, 531, 69);
        playAgainRect = new Rect(195, 275, 440, 55);
        exitRect = new Rect(328, 348, 175, 53);

    }

    public void checkAlive(int state) {
        isAlive = state != 0;
    }

    public void update() {
        //Pressed Play Again
        if (mouseH.getX() >= playAgainRect.x && mouseH.getX() <= playAgainRect.x + playAgainRect.width &&
                mouseH.getY() >= playAgainRect.y && mouseH.getY() <= playAgainRect.y + playAgainRect.height) {
            playAgainCurrentImage = playAgainPressed;
            if (mouseH.isPressed) {
                Window.getWindow().changeState(1);
            }
        } else playAgainCurrentImage = playAgain;

        //Pressed Exit
        if (mouseH.getX() >= exitRect.x && mouseH.getX() <= exitRect.x + exitRect.width &&
                mouseH.getY() >= exitRect.y && mouseH.getY() <= exitRect.y + exitRect.height) {
            exitCurrentImage = exitPressed;
            if (mouseH.isPressed) {
                Window.getWindow().close();
            }
        } else exitCurrentImage = exit;
    }

    public void draw(Graphics g2) {
//        JLayeredPane grayLayer = new JLayeredPane();
//        grayLayer.setBounds(0, 0, Constant.WIDTH - 100, Constant.HEIGHT - 100);
//        grayLayer.setBackground(new Color(0, 0, 0, 50));
//
//        Window.getWindow().addLayer(grayLayer);

        getOption(g2, gameOver, gameOverRect, playAgainCurrentImage, playAgainRect, exitCurrentImage, exitRect);
    }

    static void getOption(Graphics g2, BufferedImage gameOver, Rect gameOverRect, BufferedImage playAgainCurrentImage, Rect playAgainRect, BufferedImage exitCurrentImage, Rect exitRect) {
        g2.drawImage(gameOver, (int) gameOverRect.x, (int) gameOverRect.y, (int) gameOverRect.width, (int) gameOverRect.height, null);
        g2.drawImage(playAgainCurrentImage, (int) playAgainRect.x, (int) playAgainRect.y, (int) playAgainRect.width, (int) playAgainRect.height, null);
        g2.drawImage(exitCurrentImage, (int) exitRect.x, (int) exitRect.y, (int) exitRect.width, (int) exitRect.height, null);
    }
}
