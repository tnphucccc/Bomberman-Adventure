package GUI;

import Controls.MouseHandler;
import Variables.Constant;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Objects;

public class MenuScene extends Scene {
    public Rectangle playRect, exitRect;

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
        playRect = new Rectangle(345,365,142,32);
        exitRect = new Rectangle(351, 417, 128, 30);

        MenuCurrentImage = Menu;
    }

    @Override
    public void update() {
        //Pressed Play
        if (mouseH.checkInteractWithRect(mouseH, playRect)) {
            MenuCurrentImage = MenuPlayPressed;
            if (mouseH.isPressed) {
                Window.getWindow().changeState(1);
            }
        } else if (mouseH.checkInteractWithRect(mouseH, exitRect)) {
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