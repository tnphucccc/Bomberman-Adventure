package GUI;

import Controls.MouseHandler;
import Variables.Constant;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Objects;

public class MenuScene extends Scene {
    public Rect playRect, exitRect;

    public BufferedImage Menu, MenuPlayPressed, MenuExitPressed;
    public BufferedImage MenuCurrentImage;

    public MouseHandler mouseH = Window.getMouseH();

    public MenuScene() {
        try{
            Menu = ImageIO.read(new File("src/main/resources/Menu/Menu.png"));
            MenuPlayPressed = ImageIO.read(new File("src/main/resources/Menu/MenuPlayPressed.png"));
            MenuExitPressed = ImageIO.read(new File("src/main/resources/Menu/MenuExitPressed.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        playRect = new Rect(359,346,107,26);
        exitRect = new Rect(359, 405, 105, 26);

        MenuCurrentImage = Menu;
    }

    @Override
    public void update() {
        //Pressed Play
        if (mouseH.getX() >= playRect.x && mouseH.getX() <= playRect.x + playRect.width &&
                mouseH.getY() >= playRect.y && mouseH.getY() <= playRect.y + playRect.height) {
            MenuCurrentImage = MenuPlayPressed;
            if (mouseH.isPressed) {
                Window.getWindow().changeState(1);
            }
        } else if (mouseH.getX() >= exitRect.x && mouseH.getX() <= exitRect.x + exitRect.width &&
                mouseH.getY() >= exitRect.y && mouseH.getY() <= exitRect.y + exitRect.height) {
            MenuCurrentImage = MenuExitPressed;
            if (mouseH.isPressed) {
                Window.getWindow().close();
            }
        } else {
            MenuCurrentImage = Menu;
        }
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(MenuCurrentImage, 0, 0, Constant.WIDTH, Constant.HEIGHT, null);
    }
}