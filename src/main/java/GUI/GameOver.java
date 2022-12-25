package GUI;

import Controls.MouseHandler;
import Variables.Constant;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class GameOver {
    boolean isAlive;

    BufferedImage gameOver, playAgainPressed, exitPressed;
    BufferedImage currentGameOver;

    Rect playAgainRect, exitRect;

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
            gameOver = ImageIO.read(new File("src/main/resources/Menu/GameOver.png"));
            playAgainPressed = ImageIO.read(new File("src/main/resources/Menu/GameOverPlayAgainPressed.png"));
            exitPressed = ImageIO.read(new File("src/main/resources/Menu/GameOverExitPressed.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        currentGameOver = gameOver;
        playAgainRect = new Rect(258, 292, 332, 31);
        exitRect = new Rect(359, 344, 131, 29);
    }

    public void checkAlive(int state) {
        isAlive = state != 0;
    }

    public void update() {
        if (mouseH.getX() >= playAgainRect.x && mouseH.getX() <= playAgainRect.x + playAgainRect.width &&
                mouseH.getY() >= playAgainRect.y && mouseH.getY() <= playAgainRect.y + playAgainRect.height) {
            currentGameOver = playAgainPressed;
            if (mouseH.isPressed) {
                Window.getWindow().changeState(2); // For debugging purposes
               // Window.getWindow().changeState(GameScene.getMapID());
                TileManager.getInstance().clearMap(); //Return map to default map
            }
        } else if (mouseH.getX() >= exitRect.x && mouseH.getX() <= exitRect.x + exitRect.width &&
                mouseH.getY() >= exitRect.y && mouseH.getY() <= exitRect.y + exitRect.height) {
            currentGameOver = exitPressed;
            if (mouseH.isPressed) {
                Window.getWindow().close();
            }
        } else {
            currentGameOver = gameOver;
        }
    }

    public void draw(Graphics g2) {
        g2.drawImage(currentGameOver, 0, 0, Constant.WIDTH, Constant.HEIGHT, null);
    }
}
