package Entity;

import Controls.KeyHandler;
import Variables.Constant;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;

public class Bomb {
    private int x, y;
    private String key = "";
    KeyHandler keyH;
    private int bombSize = 5;
    
    boolean spaceSpressed = false;
    ArrayList<Bomb> bombList = new ArrayList<>(bombSize);
    private int bombCounter = 0;
    //KeyHandler 
    public Bomb(KeyHandler keyH){
        this.keyH = keyH;
    }
    public void update(int x,int y){
        
        key = "space";
        this.x = x;
        this.y = y;
        if(bombCounter<bombSize){
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
        
        
    }

    public void draw(Graphics2D g2){
        if(bombList != null){
            //Image img = null;
            
            if(key.equals("space")){
                //load Bomb.gif from resources
                URL url = getClass().getResource("/Bomb/Bomb.gif");
                ImageIcon icon = new ImageIcon(url);
                Image img = icon.getImage();

                
                
                
                g2.drawImage(img, this.x, this.y, Constant.original_tile_size * Constant.scale,
                        Constant.original_tile_size * Constant.scale, null);
            }
        }

    }
    
    public ArrayList<Bomb> getBombList(){
        return bombList;
    }
    public int getBombCounter(){
        return bombCounter;
    }
    public void setBombCounter(int bombCounter){
        this.bombCounter = bombCounter;
    }
    
}


