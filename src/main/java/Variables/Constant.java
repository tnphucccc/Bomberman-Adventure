package Variables;

public class Constant {
    public static final int scale = 3;
    public static final int original_tile_size = 16;
    public static final int tileSize = original_tile_size * scale;
    public static final int maxScreenCol = 15+2 ;
    public static final int maxScreenRow = 9 +2;
    public static final int WIDTH = tileSize * maxScreenCol;
    public static final int HEIGHT = tileSize * maxScreenRow;

    public static final String title = "Bomberman Adventure";
    public static final int FPS = 60;
}