package GUI;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class MenuScence extends Scence {
    public BufferedImage title, play, playPressed, exit, exitPressed;

    public MenuScence() {
        try {
            BufferedImage spritesheet1 = ImageIO.read(new File("src/main/resources/Menu/Exit_Play.png"));
            play = spritesheet1.getSubimage(0, 121, 261, 121);
            playPressed = spritesheet1.getSubimage(264, 121, 261, 121);
            exit = spritesheet1.getSubimage(0, 0, 233, 93);
            exitPressed = spritesheet1.getSubimage(264, 0, 233, 93);

            BufferedImage spritesheet2 = ImageIO.read(new File("src/main/resources/Menu/Game_Title.png"));
            title = spritesheet2.getSubimage(0, 0, 381, 57);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public void update() {

    }

    @Override
    public void draw(Graphics g) {
        g.setColor(new Color(240, 235, 227));
        g.fillRect(0, 0, 960, 640);

        g.drawImage(title, 240, 40, 300, 100, null);
        g.drawImage(play, 310, 280, 150, 70, null);
        g.drawImage(exit, 318, 355, 130, 55, null);
    }
}