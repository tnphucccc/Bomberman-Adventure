package GUI;

import Entity.Mob;
import Objects.OBJ_BlastRadius;
import Objects.OBJ_SpeedIncrease;
import Variables.Constant;

public class AssetSetter {
    GameScene gameScene;

    public AssetSetter(GameScene gameScene) {
        this.gameScene = gameScene;
    }
    public void setMob(){
        gameScene.mob[0]=new Mob(144 + 48,224 + 48);
        gameScene.mob[1]=new Mob(624 + 48,32 + 48);

    }
    public void setItems(){
        GameScene.Object[0] = new OBJ_BlastRadius();
        GameScene.Object[0].x = 6 * Constant.tileSize;
        GameScene.Object[0].y = 6 * Constant.tileSize;
        GameScene.Object[1] = new OBJ_SpeedIncrease();
        GameScene.Object[1].x = 8 * Constant.tileSize;
        GameScene.Object[1].y = 2 * Constant.tileSize;
        GameScene.Object[2] = new OBJ_SpeedIncrease();
        GameScene.Object[2].x = 2 * Constant.tileSize;
        GameScene.Object[2].y = 3 * Constant.tileSize;
    }
}
