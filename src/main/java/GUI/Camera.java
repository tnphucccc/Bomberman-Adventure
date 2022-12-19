package GUI;

import Variables.Constant;

public class Camera {
    public int x, y;

    public static int setXCord(int x) {
        int playerX = GameScene.getPlayer().getX();
        if (GameScene.getMapID() == 2) {
            return x - playerX + Constant.PLAYER_SCREEN_X;
        } else if (GameScene.getMapID() == 1) {
            return x;
        }
        return x;
    }

    public static int setYCord(int y) {
        int playerY = GameScene.getPlayer().getY();
        if (GameScene.getMapID() == 2) {
            return y - playerY + Constant.PLAYER_SCREEN_Y;
        } else if (GameScene.getMapID() == 1) {
            return y;
        }
        return y;
    }

    public static int setXPlayerCord(int x){
        if (GameScene.getMapID() == 2){
            return Constant.PLAYER_SCREEN_X;
        } else if (GameScene.getMapID() == 1){
            return x;
        }
        return x;
    }

    public static int setYPlayerCord(int y){
        if (GameScene.getMapID() == 2){
            return Constant.PLAYER_SCREEN_Y;
        } else if (GameScene.getMapID() == 1){
            return y;
        }
        return y;
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