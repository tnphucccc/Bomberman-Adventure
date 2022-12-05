package Controls;

import javax.sound.sampled.*;
import javax.swing.*;
import java.io.IOException;
import java.net.URL;

public class SoundManager extends JFrame {
    private String soundPath;
    private Clip just_died, put_bomb, bomb_explode, get_item;

    public SoundManager(String name, String sound) {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        try {
            URL url = this.getClass().getClassLoader().getResource(name);
            assert url != null;
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(url);
            switch (sound) {
                case "just_died" -> {
                    just_died = AudioSystem.getClip();
                    just_died.open(audioInputStream);
                }
                case "put_bomb" -> {
                    put_bomb = AudioSystem.getClip();
                    put_bomb.open(audioInputStream);
                }
                case "bomb_explode" -> {
                    bomb_explode = AudioSystem.getClip();
                    bomb_explode.open(audioInputStream);
                }
                case "get_item" -> {
                    get_item = AudioSystem.getClip();
                    get_item.open(audioInputStream);
                }
            }
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }
}
