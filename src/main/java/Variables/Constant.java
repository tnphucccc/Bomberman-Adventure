package Variables;

public class Constant {
    public static final int scale = 3;
    public static final int original_tile_size = 16;
    public static final int tileSize = original_tile_size * scale;
    public static final int maxScreenCol = 15;
    public static final int maxScreenRow = 9;
    public static final int WIDTH = (original_tile_size * maxScreenCol) * scale;
    public static final int HEIGHT = (original_tile_size * maxScreenRow) * scale;
    public static final String title = "Bomberman Adventure";
    public static final int FPS = 60;
}
