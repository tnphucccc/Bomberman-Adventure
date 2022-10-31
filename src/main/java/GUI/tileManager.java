package GUI;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class tileManager {
    gamePanel gamepanel;
    public Tile[] tiles;
    public int[][] mapTileNum;

    public tileManager(gamePanel gamepanel) {
        this.gamepanel = gamepanel;
        tiles = new Tile[10];
        mapTileNum = new int[gamepanel.maxScreenRow][gamepanel.maxScreenCol];
        getTileImage();
        loadMap("/Maps/Map01.txt");
    }

    public void getTileImage() {
        try {
            tiles[0] = new Tile();
            tiles[0].image = ImageIO.read(getClass().getResourceAsStream("/Tiles/Ground.png"));

            tiles[1] = new Tile();
            tiles[1].image = ImageIO.read(getClass().getResourceAsStream("/Tiles/Block.png"));
            tiles[1].collision = true;

            tiles[2] = new Tile();
            tiles[2].image = ImageIO.read(getClass().getResourceAsStream("/Tiles/Brick.png"));
            tiles[2].collision = true;

            tiles[3] = new Tile();
            tiles[3].image = ImageIO.read(getClass().getResourceAsStream("/Tiles/GroundShadow.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadMap(String filePath) {
        try {
            InputStream is = getClass().getResourceAsStream(filePath);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col = 0, row = 0;
            while (col < gamepanel.maxScreenCol && row < gamepanel.maxScreenRow) {
                String line = br.readLine();
                while (col < gamepanel.maxScreenCol) {
                    String[] numbers = line.split(" ");
                    int num = Integer.parseInt(numbers[col]);
                    mapTileNum[row][col] = num;
                    col++;
                }
                if (col == gamepanel.maxScreenCol) {
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
        while (col < gamepanel.maxScreenCol && row < gamepanel.maxScreenRow) {
            int tileNum = mapTileNum[row][col];
            g2.drawImage(tiles[tileNum].image, x, y,
                    gamepanel.original_tile_size * gamePanel.scale,
                    gamepanel.original_tile_size * gamePanel.scale, null);
            col++;
            x += gamepanel.original_tile_size * gamePanel.scale;

            if (col == gamepanel.maxScreenCol) {
                col = 0;
                row++;
                x = 0;
                y += gamepanel.original_tile_size * gamePanel.scale;
            }
        }
    }
}
