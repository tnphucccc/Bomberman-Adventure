package Entity;

import Controls.keyHandler;
import Variables.Constant;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class Bomb {
    private int x, y;
    private String key = "";
    keyHandler keyH;

    boolean spaceSpressed = false;
    ArrayList<Bomb> bombList = new ArrayList<>();
    int bombCounter;

    public Bomb(keyHandler keyH){
        this.keyH = keyH;
    }
    public void update(int x,int y){
        
        key = "space";
        this.x = x;
        this.y = y;

        if(keyH.spacePressed){
            spaceSpressed = true;
        }
        if(!keyH.spacePressed && spaceSpressed){
            spaceSpressed = false;
            bombList.add(bombCounter,new Bomb(keyH));

            bombList.get(bombCounter).update(this.x,this.y);

            bombCounter++;
            System.out.println("From update:"+bombCounter);
        }
        
    }

    public void draw(Graphics2D g2){
        if(bombList != null){
            BufferedImage img = null;

            if(key.equals("space")){
                try {
                    img = ImageIO.read((getClass().getResourceAsStream("/Bomb/bomb1.png")));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                g2.drawImage(img, this.x, this.y, Constant.original_tile_size * Constant.scale,
                        Constant.original_tile_size * Constant.scale, null);
            }
        }

    }
    public ArrayList<Bomb> getBombList(){
        return bombList;
    }
}


