package GUI;

import javax.swing.*;


public class gameFrame extends JFrame {
    public gameFrame() {
        this.setTitle("Bomberman Adventure");
        this.setIconImage(new ImageIcon("src\\main\\resources\\Logo-1.png").getImage());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        gamePanel game = new gamePanel();
        this.add(game);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        //Set up map attributes
        game.setupMap();
        game.start_gameThread();
    }
}