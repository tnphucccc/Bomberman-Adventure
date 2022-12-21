package GUI;

import Entity.Mob;
import Objects.OBJ_Door;
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
        if(GameScene.getMapID() == 1) { // Mob Cord at Map 01
            GameScene.mobList.add(0, new Mob(4*Constant.TILE_SIZE, 5* Constant.TILE_SIZE));
            GameScene.mobList.add(1, new Mob(14*Constant.TILE_SIZE, 5*Constant.TILE_SIZE));
            GameScene.mobList.add(2, new Mob(14*Constant.TILE_SIZE, 8*Constant.TILE_SIZE));
            GameScene.mobList.add(3, new Mob(5*Constant.TILE_SIZE, 8*Constant.TILE_SIZE));
        } else if (GameScene.getMapID() == 2) { // Mob Cord at Map 02
            GameScene.mobList.add(0, new Mob(10 * Constant.TILE_SIZE, 11 * Constant.TILE_SIZE));
        }
    }

    public void setItems() {
        if (GameScene.getMapID() == 1){
            GameScene.Object[1] = new OBJ_SpeedIncrease(9,4);
            GameScene.Object[2] = new OBJ_ExtraBomb(13,4);
            GameScene.Object[3] = new OBJ_Door(15,8);
            GameScene.Object[4] = new OBJ_ExtraBomb(10,6);
        }


    }
}
