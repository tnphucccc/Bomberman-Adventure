package GUI;

import Variables.Constant;

public class Camera {
    public int x, y;

    public static int getXCord(int x) {
        int playerX = GameScene.getPlayer().getX();
        return x - playerX + Constant.PLAYER_SCREEN_X;
    }

    public static int getYCord(int y) {
        int playerY = GameScene.getPlayer().getY();
        return y - playerY + Constant.PLAYER_SCREEN_Y;
    }

    public static boolean canDraw(int x, int y) {
        int playerX = GameScene.getPlayer().getX();
        int playerY = GameScene.getPlayer().getY();

        return x + Constant.TILE_SIZE > playerX - Constant.PLAYER_SCREEN_X &&
                x - Constant.TILE_SIZE < playerX + Constant.PLAYER_SCREEN_X &&
                y + Constant.TILE_SIZE > playerY - Constant.PLAYER_SCREEN_Y &&
                y - Constant.TILE_SIZE < playerY + Constant.PLAYER_SCREEN_Y;
    }
}