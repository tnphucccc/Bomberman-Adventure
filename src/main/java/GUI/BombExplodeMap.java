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
        
        map = new int[Constant.MAX_WORLD_ROW][Constant.MAX_WORLD_COL];
        loadMap("/Maps/Map02.txt");
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

        System.out.println("Location "+this.x+" "+this.y);
        
        if(map[this.x][this.y-1]==0 || map[this.x][this.y-1]==3) {
            System.out.println(map[this.x][this.y-1]+" up");
            drawBombExplode(g2, up, this.x, this.y-1);
        }
        if(map[this.x][this.y+1]==0 || map[this.x][this.y+1]==3) {
            System.out.println(map[this.x][this.y+1]+" down");
            drawBombExplode(g2, up, this.x, this.y+1);
        }
        if(map[this.x-1][this.y]==0 || map[this.x-1][this.y]==3) {
            System.out.println(map[this.x-1][this.y]+" left");
            drawBombExplode(g2, up, this.x-1, this.y);
        }
        if(map[this.x+1][this.y]==0 || map[this.x+1][this.y]==3) {
            System.out.println(map[this.x+1][this.y]+" right");
            drawBombExplode(g2, up, this.x+1, this.y);
        }
      
    }
    //load map tp 2d array
    public void loadMap(String filePath) {
        try {
            InputStream is = Objects.requireNonNull(getClass().getResourceAsStream(filePath));
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col = 0, row = 0;
            while (col < Constant.MAX_WORLD_COL && row < Constant.MAX_WORLD_ROW) {
                String line = br.readLine();
                while (col < Constant.MAX_WORLD_COL) {
                    String[] numbers = line.split(" ");
                    int num = Integer.parseInt(numbers[col]);
                    map[row][col] = num;
                    col++;
                }
                if (col == Constant.MAX_WORLD_COL) {
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

        g2.drawImage(img, Camera.getXCord(x*48), Camera.getYCord(y*48), Constant.ORIGINAL_TILE_SIZE * Constant.SCALE,
                            Constant.ORIGINAL_TILE_SIZE * Constant.SCALE, null);
    }
}
