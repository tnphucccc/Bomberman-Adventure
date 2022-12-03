package GUI;

import Entity.Mob;
import Objects.OBJ_ExtraBomb;
import Objects.OBJ_SpeedIncrease;
import Variables.Constant;

public class AssetSetter {
    GameScene gameScene;

    public AssetSetter(GameScene gameScene) {
        this.gameScene = gameScene;
    }

    public void setMob() {
        gameScene.mob[0] = new Mob(18 * Constant.TILE_SIZE, 19 * Constant.TILE_SIZE);
        gameScene.mob[1] = new Mob(19 * Constant.TILE_SIZE, 20 * Constant.TILE_SIZE);
        gameScene.mob[2] = new Mob(26 * Constant.TILE_SIZE, 30 * Constant.TILE_SIZE);
    }

    public void setItems() {
        GameScene.Object[0] = new OBJ_ExtraBomb();
        GameScene.Object[0].x = 10 * Constant.TILE_SIZE;
        GameScene.Object[0].y = 6 * Constant.TILE_SIZE;

        GameScene.Object[1] = new OBJ_SpeedIncrease();
        GameScene.Object[1].x = 18 * Constant.TILE_SIZE;
        GameScene.Object[1].y = 9 * Constant.TILE_SIZE;
    }
}
