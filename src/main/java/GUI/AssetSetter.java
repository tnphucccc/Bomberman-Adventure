package GUI;

import Entity.Mob;

public class AssetSetter {
    GameScence gameScence;

    public AssetSetter(GameScence gameScence) {
        this.gameScence=gameScence;
    }
    public void setMob(){
        gameScence.mob[0]=new Mob(gameScence,144,224);
        gameScence.mob[1]=new Mob(gameScence,624,32);
    }
    public void setItems(){}
}
