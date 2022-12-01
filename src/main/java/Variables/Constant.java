package Variables;

public class Constant {
    public static final int SCALE = 3;
    public static final int ORIGINAL_TILE_SIZE = 16;
    public static final int TILE_SIZE = ORIGINAL_TILE_SIZE * SCALE;
    
    public static final int MAX_SCREEN_COL = 17;
    public static final int MAX_SCREEN_ROW = 11;
    
    public static final int WIDTH = TILE_SIZE * MAX_SCREEN_COL;
    public static final int HEIGHT = TILE_SIZE * MAX_SCREEN_ROW;
    
    public static final String TITLE = "Bomberman Adventure";
    public static final int FPS = 60;
}