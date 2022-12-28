package Variables;

public class Constant {
    public static final int SCALE = 3;
    public static final int ORIGINAL_TILE_SIZE = 16;
    public static final int TILE_SIZE = ORIGINAL_TILE_SIZE * SCALE; // 48

    public static final int MAX_SCREEN_COL = 17;
    public static final int MAX_SCREEN_ROW = 11;

    public static final int WIDTH = TILE_SIZE * MAX_SCREEN_COL; // 816
    public static final int HEIGHT = TILE_SIZE * MAX_SCREEN_ROW ; // 528

    //WORLD SIZE
    public static int MAX_WORLD_COL = 50;
    public static int MAX_WORLD_ROW = 50;
    public static final int WORLD_WIDTH = TILE_SIZE * MAX_WORLD_COL;
    public static final int WORLD_HEIGHT = TILE_SIZE * MAX_WORLD_ROW;

    //Player position in main screen
    public static final int PLAYER_SCREEN_X = WIDTH/2 - TILE_SIZE/2;
    public static final int PLAYER_SCREEN_Y = HEIGHT/2 - TILE_SIZE/2;

    public static final String TITLE = "Bomberman Adventure";
    public static final int FPS = 60;
    public static final int Tera= 1_000_000_000;
}