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
        gameScene.mob[0]=new Mob(144 + 48,224+48);
        gameScene.mob[1]=new Mob(624 + 48,48 + 48);

    }
    public void setItems(){
        gameScene.Object[0] = new OBJ_BlastRadius();
        gameScene.Object[0].x = 6 * Constant.tileSize;
        gameScene.Object[0].y = 6 * Constant.tileSize;
        gameScene.Object[1] = new OBJ_SpeedIncrease();
        gameScene.Object[1].x = 8 * Constant.tileSize;
        gameScene.Object[1].y = 2 * Constant.tileSize;
    }
}
