package Controls;

import javax.swing.*;
import javax.sound.sampled.*;

import java.io.IOException;
import java.net.URL;

public class SoundManager extends JFrame {
    private String soundPath;
    private Clip just_died;
    private Clip put_bomb;
    private Clip bomb_explode;
    private Clip get_item;

    public SoundManager(String name, String sound) {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        try {
            URL url = this.getClass().getClassLoader().getResource(name);
            assert url != null;
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(url);
            if (sound.equals("just_died")) {
                just_died = AudioSystem.getClip();
                just_died.open(audioInputStream);
            } else if (sound.equals("put_bomb")) {
                put_bomb = AudioSystem.getClip();
                put_bomb.open(audioInputStream);
            } else if (sound.equals("bomb_explode")) {
                bomb_explode = AudioSystem.getClip();
                bomb_explode.open(audioInputStream);
            } else if (sound.equals("get_item")) {
                get_item = AudioSystem.getClip();
                get_item.open(audioInputStream);
            }
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }
}
