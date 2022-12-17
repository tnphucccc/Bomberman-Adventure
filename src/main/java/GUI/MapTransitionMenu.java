package GUI;

import Controls.MouseHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class MapTransitionMenu {
    MouseHandler mouseH = Window.getMouseH();
    BufferedImage mapTransitionMenu, mapTransitionMenuPressed, mapTransitionMenuCurrentImage;
    Rect mapTransitionMenuRect;

    boolean isTransitioning = false;
    public static MapTransitionMenu instance;

    public static MapTransitionMenu getInstance(){
        if(MapTransitionMenu.instance == null){
            MapTransitionMenu.instance = new MapTransitionMenu();
        }
        return MapTransitionMenu.instance;
    }

    public MapTransitionMenu(){
        try{
            BufferedImage spriteSheet = ImageIO.read(new File("src/main/resources/Menu/Map_Transition_Menu.png"));
            mapTransitionMenu = spriteSheet.getSubimage(0, 0, 390, 30);
            mapTransitionMenuPressed = spriteSheet.getSubimage(0, 39, 390, 30);

        } catch (Exception e){
            e.printStackTrace();
        }
        mapTransitionMenuCurrentImage = mapTransitionMenu;
        mapTransitionMenuRect = new Rect(220, 275, 390, 30);
    }

    public void changeMap(){
        isTransitioning = true;
    }

    public boolean getisTransitioning(){
        return isTransitioning;
    }
    public void setisTransitioning(boolean isTransitioning){
        this.isTransitioning = isTransitioning;
    }

    public void update(){
        if (mouseH.getX() >= mapTransitionMenuRect.x && mouseH.getX() <= mapTransitionMenuRect.x + mapTransitionMenuRect.width &&
                mouseH.getY() >= mapTransitionMenuRect.y && mouseH.getY() <= mapTransitionMenuRect.y + mapTransitionMenuRect.height) {
            mapTransitionMenuCurrentImage = mapTransitionMenuPressed;
            if (mouseH.isPressed) {
                Window.getWindow().changeState(2); //transition from map 01 to map 02
                TileManager.getInstance().clearMap();
                isTransitioning = false;
            }
        } else mapTransitionMenuCurrentImage = mapTransitionMenu;
    }

    public void draw(Graphics g){
        g.drawImage(mapTransitionMenuCurrentImage, (int) mapTransitionMenuRect.x, (int) mapTransitionMenuRect.y, (int) mapTransitionMenuRect.width, (int) mapTransitionMenuRect.height, null);
    }
}
