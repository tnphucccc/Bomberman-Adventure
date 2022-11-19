package GUI;

import Controls.MouseHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class MenuScence extends Scence {
    public BufferedImage title, play, playPressed, exit, exitPressed;
    public BufferedImage playCurrentImage, exitCurrentImage;

    public Rect playRect, exitRect, titleRect;

    public MouseHandler mouseH;

    public MenuScence(MouseHandler mouseH) {
        this.mouseH = mouseH;
        try {
            BufferedImage spritesheet1 = ImageIO.read(new File("src/main/resources/Menu/Exit_Play.png"));
            play = spritesheet1.getSubimage(0, 0, 197, 57);
            playPressed = spritesheet1.getSubimage(208, 0, 197, 57);
            exit = spritesheet1.getSubimage(0, 70, 175, 53);
            exitPressed = spritesheet1.getSubimage(208, 69, 175, 53);

            BufferedImage spritesheet2 = ImageIO.read(new File("src/main/resources/Menu/Game_Title.png"));
            title = spritesheet2.getSubimage(0, 0, 463, 55);

        } catch (Exception e) {
            e.printStackTrace();
        }
        playCurrentImage = play;
        exitCurrentImage = exit;

        //Button & Logo coordinates
        titleRect = new Rect(129, 40, 464, 55);
        playRect = new Rect(261, 220, 197, 57);
        exitRect = new Rect(272, 300, 175, 53);
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
        g.fillRect(0, 0, 720, 432);

        g.drawImage(title, (int) titleRect.x, (int) titleRect.y, (int) titleRect.width, (int) titleRect.height, null);
        g.drawImage(playCurrentImage, (int) playRect.x, (int) playRect.y, (int) playRect.width, (int) playRect.height, null);
        g.drawImage(exitCurrentImage, (int) exitRect.x, (int) exitRect.y, (int) exitRect.width, (int) exitRect.height, null);
    }
}