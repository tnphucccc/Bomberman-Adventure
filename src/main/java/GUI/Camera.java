package GUI;

import Variables.Constant;

public class Camera {
    public int x, y;

    public static int getXCord(int x) {
        return x - GameScene.getPlayer().x + Constant.PLAYER_SCREEN_X;
    }

    public static int getYCord(int y) {
        return y - GameScene.getPlayer().y + Constant.PLAYER_SCREEN_Y;
    }

    public static boolean canDraw(int x, int y) {
        return x + Constant.TILE_SIZE > GameScene.getPlayer().x - Constant.PLAYER_SCREEN_X &&
                x - Constant.TILE_SIZE < GameScene.getPlayer().x + Constant.PLAYER_SCREEN_X &&
                y + Constant.TILE_SIZE > GameScene.getPlayer().y - Constant.PLAYER_SCREEN_Y &&
                y - Constant.TILE_SIZE < GameScene.getPlayer().y + Constant.PLAYER_SCREEN_Y;
    }
}