package GUI;

import Controls.MouseHandler;
import Variables.Constant;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Objects;

public class FinishMenu {
    BufferedImage finishMenu, finishMenuPressed, currentFinishMenu;
    Rectangle nextButtonRect;
    MouseHandler mouseH = Window.getMouseH();
    public static FinishMenu instance = null;

    public static FinishMenu getInstance(){
        if (FinishMenu.instance == null){
            FinishMenu.instance = new FinishMenu();
        }
        return FinishMenu.instance;
    }

    FinishMenu() {
        try {
            finishMenu = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Menu/FinishMenu.png")));
            finishMenuPressed = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Menu/FinishMenuPressed.png")));
            currentFinishMenu = finishMenu;
        } catch (Exception e) {
            e.printStackTrace();
        }
        nextButtonRect = new Rectangle(149, 440, (686 - 149), (462 - 440));
    }

    public void update() {
        if (mouseH.checkInteractWithRect(mouseH, nextButtonRect)) {
            currentFinishMenu = finishMenuPressed;
            if (mouseH.isPressed) {
                Window.getWindow().changeState(3); //to Game Done
            }
        } else {
            currentFinishMenu = finishMenu;
        }
    }


    public void draw(Graphics g) {
        g.setColor(new Color(0, 0, 0, 150));
        g.fillRect(0, 0, Constant.WIDTH, Constant.HEIGHT);
        g.drawImage(currentFinishMenu, 0, 0, Constant.WIDTH, Constant.HEIGHT, null);
    }
}
