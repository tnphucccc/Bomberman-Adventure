package GUI;

import Controls.MouseHandler;
import Variables.Constant;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class MenuScene extends Scene {
    public BufferedImage TITLE, play, playPressed, exit, exitPressed;
    public BufferedImage playCurrentImage, exitCurrentImage;

    public Rect playRect, exitRect, titleRect;

    public MouseHandler mouseH;

    public MenuScene(MouseHandler mouseH) {
        this.mouseH = mouseH;
        try {
            BufferedImage spriteSheet1 = ImageIO.read(new File("src/main/resources/Menu/Exit_Play.png"));
            play = spriteSheet1.getSubimage(0, 0, 197, 57);
            playPressed = spriteSheet1.getSubimage(208, 0, 197, 57);
            exit = spriteSheet1.getSubimage(0, 70, 175, 53);
            exitPressed = spriteSheet1.getSubimage(208, 69, 175, 53);

            BufferedImage spriteSheet2 = ImageIO.read(new File("src/main/resources/Menu/Game_Title.png"));
            TITLE = spriteSheet2.getSubimage(0, 0, 463, 55);

        } catch (Exception e) {
            e.printStackTrace();
        }
        playCurrentImage = play;
        exitCurrentImage = exit;

        //Button & Logo coordinates
        titleRect = new Rect(129 + 48, 40 + 48, 464, 55);
        playRect = new Rect(261 + 48, 220 + 48, 197, 57);
        exitRect = new Rect(272 + 48, 300 + 48, 175, 53);
    }

    @Override
    public void update(double dt) {
        if (mouseH.getX() >= playRect.x && mouseH.getX() <= playRect.x + playRect.width &&
                mouseH.getY() >= playRect.y && mouseH.getY() <= playRect.y + playRect.height) {
            playCurrentImage = playPressed;
            if (mouseH.isPressed) {
                Window.getWindow().changeState(1);
            }
        } else playCurrentImage = play;

        //Pressed exit
        if (mouseH.getX() >= exitRect.x && mouseH.getX() <= exitRect.x + exitRect.width &&
                mouseH.getY() >= exitRect.y && mouseH.getY() <= exitRect.y + exitRect.height) {
            exitCurrentImage = exitPressed;
            if (mouseH.isPressed) {
                Window.getWindow().close();
            }
        } else exitCurrentImage = exit;
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(new Color(240, 235, 227));
        g.fillRect(0, 0, Constant.WIDTH, Constant.HEIGHT);

        GameOver.getOption(g, TITLE, titleRect, playCurrentImage, playRect, exitCurrentImage, exitRect);
    }
}