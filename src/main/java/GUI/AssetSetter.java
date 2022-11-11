package GUI;

import Entity.Mob;

public class AssetSetter {
    gamePanel gamepanel;

    public AssetSetter(gamePanel gamepanel) {
        this.gamepanel=gamepanel;
    }
    public void setMob(){
        gamepanel.mob[0]=new Mob(gamepanel,144,224);
        gamepanel.mob[1]=new Mob(gamepanel,624,32);
    }
    public void setItems(){}
}
