package GUI;
import Variables.Constant;
import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Objects;

public class BombExplodeMap {
    private int x, y;
    private int[][] map;
    public BombExplodeMap(int x,int y) {
        this.x = x;
        this.y = y;
        map = new int[Constant.MAX_SCREEN_ROW][Constant.MAX_SCREEN_COL];
        loadMap("/Maps/Map01.txt");
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
}
