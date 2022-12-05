package GUI;

import java.awt.*;

public abstract class Scene {
    public abstract void update(double dt);

    public int mapID;
    public abstract void draw(Graphics g);
}
