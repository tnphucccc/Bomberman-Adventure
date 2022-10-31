package GUI;

import javax.swing.*;

public class gameFrame extends JFrame {
    public gameFrame() {
        this.setTitle("Bomberman Project");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        gamePanel game = new gamePanel();
        this.add(game);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        game.start_gameThread();
    }
}
