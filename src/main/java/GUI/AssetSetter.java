package GUI;

import Entity.Mob;
import Objects.OBJ_BlastRadius;
import Objects.OBJ_Door;
import Objects.OBJ_ExtraBomb;
import Objects.OBJ_SpeedIncrease;
import Variables.Constant;

public class AssetSetter {
    GameScene gameScene;

    public AssetSetter(GameScene gameScene) {
        this.gameScene = gameScene;
        setItems();
        setMob();
    }

    public void setMob() {
        GameScene.mobList.removeAll(GameScene.mobList);//reset the creation of mobs
        if(Window.getWindow().getCurrentMapID() == 1) { // Mob Cord at Map 01
            GameScene.mobList.clear();
            GameScene.mobList.add(0, new Mob(4*Constant.TILE_SIZE, 5* Constant.TILE_SIZE));
            GameScene.mobList.add(1, new Mob(14*Constant.TILE_SIZE, 5*Constant.TILE_SIZE));
            GameScene.mobList.add(2, new Mob(13*Constant.TILE_SIZE, 8*Constant.TILE_SIZE));
            GameScene.mobList.add(3, new Mob(5*Constant.TILE_SIZE, 8*Constant.TILE_SIZE));
        } else if (Window.getWindow().getCurrentMapID() == 2) { // Mob Cord at Map 02
            GameScene.mobList.clear();
            GameScene.mobList.add(0, new Mob(16 * Constant.TILE_SIZE, 7 * Constant.TILE_SIZE));
            GameScene.mobList.add(1, new Mob(17 * Constant.TILE_SIZE, 8 * Constant.TILE_SIZE));
            GameScene.mobList.add(2, new Mob(26 * Constant.TILE_SIZE, 7 * Constant.TILE_SIZE));
            GameScene.mobList.add(3, new Mob(27 * Constant.TILE_SIZE, 8 * Constant.TILE_SIZE));
            GameScene.mobList.add(4, new Mob(26 * Constant.TILE_SIZE, 8 * Constant.TILE_SIZE));
            GameScene.mobList.add(5, new Mob(37 * Constant.TILE_SIZE, 7 * Constant.TILE_SIZE));
            GameScene.mobList.add(6, new Mob(38 * Constant.TILE_SIZE, 8 * Constant.TILE_SIZE));
            GameScene.mobList.add(7, new Mob(13 * Constant.TILE_SIZE, 14 * Constant.TILE_SIZE));
            GameScene.mobList.add(8, new Mob(32 * Constant.TILE_SIZE, 15 * Constant.TILE_SIZE));
            GameScene.mobList.add(9, new Mob(16 * Constant.TILE_SIZE, 22 * Constant.TILE_SIZE));
            GameScene.mobList.add(10, new Mob(35 * Constant.TILE_SIZE, 22 * Constant.TILE_SIZE));
            GameScene.mobList.add(11, new Mob(16 * Constant.TILE_SIZE, 15 * Constant.TILE_SIZE));
        }

    }

    public void setItems() {
        if (Window.getWindow().getCurrentMapID() == 1){
            GameScene.Object[0] = new OBJ_SpeedIncrease(9,4);
            GameScene.Object[1] = new OBJ_ExtraBomb(13,4);
            GameScene.Object[2] = new OBJ_Door(14,8);
            GameScene.Object[3] = new OBJ_ExtraBomb(10,6);
            //GameScene.Object[4] = new OBJ_Door(3,3);
            GameScene.Object[4] = new OBJ_BlastRadius(6,6);
        } else if (Window.getWindow().getCurrentMapID() == 2){
            //GameScene.Object[0] = new OBJ_Door(8,4);
            //GameScene.Object[1] = new OBJ_BlastRadius(10,8);
            GameScene.Object[2] = new OBJ_ExtraBomb(11,6);
            GameScene.Object[3] = new OBJ_ExtraBomb(21,7);
            GameScene.Object[4] = new OBJ_ExtraBomb(32,6);
            GameScene.Object[5] = new OBJ_SpeedIncrease(36,9);
            GameScene.Object[6] = new OBJ_ExtraBomb(39,16);
            GameScene.Object[7] = new OBJ_SpeedIncrease(40,17);
            GameScene.Object[8] = new OBJ_ExtraBomb(9,11);
            GameScene.Object[9] = new OBJ_ExtraBomb(11,18);
            GameScene.Object[10] = new OBJ_ExtraBomb(24,22);
            GameScene.Object[11] = new OBJ_BlastRadius(21,15);
            GameScene.Object[12] = new OBJ_BlastRadius(13,5);
            GameScene.Object[13] = new OBJ_ExtraBomb(8,24);
            GameScene.Object[14] = new OBJ_ExtraBomb(35,24);
            GameScene.Object[15] = new OBJ_SpeedIncrease(36,24);
        }
    }
}
