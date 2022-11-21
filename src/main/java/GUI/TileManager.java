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

    public TileManager() {
        tiles = new Tile[10];
        mapTileNum = new int[Constant.maxScreenRow][Constant.maxScreenCol];
        getTileImage();
        loadMap("/Maps/Map01.txt");
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

            tiles[4] = new Tile();
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
            while (col < Constant.maxScreenCol && row < Constant.maxScreenRow) {
                String line = br.readLine();
                while (col < Constant.maxScreenCol) {
                    String[] numbers = line.split(" ");
                    int num = Integer.parseInt(numbers[col]);
                    mapTileNum[row][col] = num;
                    col++;
                }
                if (col == Constant.maxScreenCol) {
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
        int col = 0, row = 0, x = 0, y = 0;

        while (col < Constant.maxScreenCol && row < Constant.maxScreenRow) {
            int tileNum = mapTileNum[row][col];
            g2.drawImage(tiles[tileNum].image, x, y,
                    Constant.tileSize, Constant.tileSize, null);
            col++;
            x += Constant.tileSize;

            if (col == Constant.maxScreenCol) {
                col = 0;
                row++;
                x = 0;
                y += Constant.tileSize;
            }
        }
    }
}