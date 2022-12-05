package GUI;

import Variables.Constant;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Objects;

public class TileManager {
    public Tile[] tiles;
    public int[][] mapTileNum;

    public static TileManager instance = null;

    //Singleton
    public static TileManager getInstance(){
        if (instance == null){
            instance = new TileManager();
        }
        return instance;
    }

    public TileManager() {
        tiles = new Tile[10];
        mapTileNum = new int[Constant.MAX_WORLD_ROW][Constant.MAX_WORLD_COL];

        if (Window.getCurrentScence().mapID != 0) {
            loadMap("/Maps/Map0" + Window.getCurrentScence().mapID + ".txt");
        }
        getTileImage();
    }

    public void getTileImage() {
        try {
            tiles[0] = new Tile();
            tiles[0].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Tiles/Ground.png")));

            tiles[1] = new Tile();
            tiles[1].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Tiles/Block.png")));
            tiles[1].collision = true;

            tiles[2] = new Tile();
            tiles[2].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Tiles/Brick.png")));
            tiles[2].collision = true;

            tiles[3] = new Tile();
            tiles[3].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Tiles/GroundShadow.png")));

            tiles[4] = new Tile();//Seriously ? Sand vs sand typo?
            tiles[4].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Tiles/Sand.png")));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadMap(String filePath) {
        try {
            InputStream is = Objects.requireNonNull(getClass().getResourceAsStream(filePath));

            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col = 0, row = 0;

            while (col < Constant.MAX_WORLD_COL && row < Constant.MAX_WORLD_ROW){

                String line = br.readLine();

                while (col < Constant.MAX_WORLD_COL){
                    String[] numbers = line.split(" ");

                    int num = Integer.parseInt(numbers[col]);

                    mapTileNum[row][col] = num;
                    col++;
                }

                if (col == Constant.MAX_WORLD_COL){
                    col = 0;
                    row++;
                }
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2) {
        int worldCol = 0;
        int worldRow = 0;

        while (worldCol < Constant.MAX_WORLD_COL && worldRow < Constant.MAX_WORLD_ROW){
            int tileNum = mapTileNum[worldRow][worldCol];

            int x = worldCol * Constant.TILE_SIZE;
            int y = worldRow * Constant.TILE_SIZE;

            //Create a boundary for the screen
            if (Camera.canDraw(x, y))
            {
                g2.drawImage(tiles[tileNum].image, Camera.getXCord(x), Camera.getYCord(y), Constant.TILE_SIZE, Constant.TILE_SIZE, null);
            }
            worldCol++;

            if (worldCol == Constant.MAX_WORLD_COL){
                worldCol =  0;
                worldRow++;
            }
        }
    }
}