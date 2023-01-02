package GUI;

import Controls.MouseHandler;
import Variables.Constant;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;

import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.Objects;

public class GameDone extends Scene {
    Image gameDone;
    BufferedImage nextButton, nextButtonPressed, currentNextButton, finishMenu, finishMenuPressed, currentFinishMenu;
    Rectangle nextButtonRect;
    MouseHandler mouseH = Window.getMouseH();

    public static GameDone instance = null;

    public static GameDone getInstance() {
        if (GameDone.instance == null) {
            GameDone.instance = new GameDone();
        }
        return GameDone.instance;
    }

    GameDone() {

        URL url = Objects.requireNonNull(getClass().getResource("/Menu/GameDone.gif"));
        ImageIcon icon = new ImageIcon(url);
        gameDone = icon.getImage();

        try {
            nextButton = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Menu/Next.png")));
            nextButtonPressed = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Menu/NextPressed.png")));
            currentNextButton = nextButton;

        } catch (Exception e) {
            e.printStackTrace();
        }
        nextButtonRect = new Rectangle(337, 434, 141, 30);
    }

    @Override
    public void update() {
            if (mouseH.checkInteractWithRect(mouseH, nextButtonRect)) {
                currentNextButton = nextButtonPressed;
                if (mouseH.isPressed) {
                    Window.getWindow().changeState(4); //From Congratulation to Credit
                }
            } else {
                    currentNextButton = nextButton;
            }
        }


    @Override
    public void draw(Graphics g) {
        g.drawImage(gameDone, 0, 0, Constant.WIDTH, Constant.HEIGHT, null);
        g.drawImage(currentNextButton, 0, 0, Constant.WIDTH, Constant.HEIGHT, null);
    }
}
