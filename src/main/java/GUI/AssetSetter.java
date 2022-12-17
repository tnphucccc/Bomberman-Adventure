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
        if(GameScene.getMapID() == 1) {
            GameScene.mobList.add(0, new Mob(192, 272));
        } else if (GameScene.getMapID() == 2) {
            GameScene.mobList.add(0, new Mob(10 * Constant.TILE_SIZE, 11 * Constant.TILE_SIZE));
        }
//        GameScene.mobList.add(1, new Mob(10 * Constant.TILE_SIZE, 24 * Constant.TILE_SIZE));
//        GameScene.mobList.add(2, new Mob(8 * Constant.TILE_SIZE, 9 * Constant.TILE_SIZE));
//        GameScene.mobList.add(3, new Mob(19 * Constant.TILE_SIZE, 13 * Constant.TILE_SIZE));
    }

    public void setItems() {
        GameScene.Object[1] = new OBJ_SpeedIncrease();
        GameScene.Object[1].x = 8 * Constant.TILE_SIZE;
        GameScene.Object[1].y = 2 * Constant.TILE_SIZE;
        GameScene.Object[2] = new OBJ_SpeedIncrease();
        GameScene.Object[2].x = 2 * Constant.TILE_SIZE;
        GameScene.Object[2].y = 3 * Constant.TILE_SIZE;
//
//        GameScene.Object[1] = new OBJ_SpeedIncrease();
//        GameScene.Object[1].x = 22 * Constant.TILE_SIZE;
//        GameScene.Object[1].y = 30 * Constant.TILE_SIZE;
    }
}
