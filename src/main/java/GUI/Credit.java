package GUI;

import Variables.Constant;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Objects;

public class Credit extends Scene {

    BufferedImage credit, exitButton, exitButtonPressed, currentExitButton;
    Rectangle exitButtonRect;

    Credit(){
        try{
            credit = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Menu/Credit.png")));
            exitButton = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Menu/ExitButton.png")));
            exitButtonPressed = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Menu/ExitPressed.png")));
            currentExitButton = exitButton;
        } catch (Exception e) {
            e.printStackTrace();
        }
        exitButtonRect = new Rectangle(339, 456, 137, 32);
    }


    @Override
    public void update() {
        if (Window.getMouseH().checkInteractWithRect(Window.getMouseH(), exitButtonRect)) {
            currentExitButton = exitButtonPressed;
            if (Window.getMouseH().isPressed) {
                Window.getWindow().close();
            }
        } else {
            currentExitButton = exitButton;
        }
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(credit, 0, 0, Constant.WIDTH, Constant.HEIGHT, null);
        g.drawImage(currentExitButton, 0, 0, Constant.WIDTH, Constant.HEIGHT, null);
    }
}
