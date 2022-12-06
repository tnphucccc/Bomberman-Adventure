package GUI;
import Variables.Constant;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Objects;

public class BombExplodeMap {
    private int x, y;
    private static int[][] map;
    BufferedImage up;
    public BombExplodeMap() {
        
        map = new int[Constant.MAX_SCREEN_ROW][Constant.MAX_SCREEN_COL];
        loadMap("/Maps/Map01.txt");    
        try {
            up = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Bomb/start4.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
     
    }
    //draw 
    public void draw(int x,int y,Graphics2D g2) {
        this.x = x/48;
        this.y = y/48;
        System.out.println(this.x+"fuck "+this.y);
        System.out.println(this.x+" "+this.y);
        if(map[this.x][this.y-1]==0){
            drawBombExplode(g2, up, this.x, this.y-1);
        }
        if(map[this.x][this.y+1]==0){
            drawBombExplode(g2, up, this.x, this.y+1);
        }
        if(map[this.x-1][this.y]==0){
            drawBombExplode(g2, up, this.x-1, this.y);
        }
        if(map[this.x][this.y]==0){
            drawBombExplode(g2, up, this.x+1, this.y);
        }
    }
    //load map tp 2d array
    public void loadMap(String filePath) {
        try {
            InputStream is = Objects.requireNonNull(getClass().getResourceAsStream(filePath));
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col = 0, row = 0;
            while (col < Constant.MAX_SCREEN_COL && row < Constant.MAX_SCREEN_ROW) {
                String line = br.readLine();
                while (col < Constant.MAX_SCREEN_COL) {
                    String[] numbers = line.split(" ");
                    int num = Integer.parseInt(numbers[col]);
                    map[row][col] = num;
                    col++;
                }
                if (col == Constant.MAX_SCREEN_COL) {
                    col = 0;
                    row++;
                }
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //get map
    public int[][] getMap() {
        return map;
    }
    //set map
    public void setMap(int x,int y,int value) {
        map[x][y] = value;
    }
    //draw bomb explosion
    public void drawBombExplode(Graphics2D g2,BufferedImage img,int x,int y) {
        
        g2.drawImage(up, Camera.getXCord(this.x*48), Camera.getYCord(this.y*48), Constant.ORIGINAL_TILE_SIZE * Constant.SCALE,
                            Constant.ORIGINAL_TILE_SIZE * Constant.SCALE, null);
    }
}
