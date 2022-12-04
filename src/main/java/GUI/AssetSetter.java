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
        GameScene.mobList.removeAll(GameScene.mobList);//reset the creation of mobs
        GameScene.mobList.add(0, new Mob(192,272));
        GameScene.mobList.add(1, new Mob(672, 70));
    }

    public void setItems() {
        GameScene.Object[0] = new OBJ_ExtraBomb();
        GameScene.Object[0].x = 8 * Constant.TILE_SIZE;
        GameScene.Object[0].y = 2 * Constant.TILE_SIZE;
        GameScene.Object[1] = new OBJ_SpeedIncrease();
        GameScene.Object[1].x = 6 * Constant.TILE_SIZE;
        GameScene.Object[1].y = 6 * Constant.TILE_SIZE;
    }
}
