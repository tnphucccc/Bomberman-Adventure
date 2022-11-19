package GUI;

import Entity.Mob;
import Objects.OBJ_BlastRadius;
import Objects.OBJ_SpeedIncrease;
import Variables.Constant;

public class AssetSetter {
    GameScence gameScence;

    public AssetSetter(GameScence gameScence) {
        this.gameScence = gameScence;
    }

    public void setMob() {
        gameScence.mob[0] = new Mob(144, 224);
        gameScence.mob[1] = new Mob(624, 32);
        gameScence.Object[0] = new OBJ_BlastRadius();
        gameScence.Object[0].x = 5 * Constant.tileSize;
        gameScence.Object[0].y = 5 * Constant.tileSize;
        gameScence.Object[1] = new OBJ_SpeedIncrease();
        gameScence.Object[1].x = 7 * Constant.tileSize;
        gameScence.Object[1].y = 1 * Constant.tileSize;
    }

    public void setItems() {
    }
}
