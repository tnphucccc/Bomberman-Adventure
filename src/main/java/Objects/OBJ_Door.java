package Objects;

import GUI.GameScene;
import Variables.Constant;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

public class OBJ_Door extends SuperObject {
    public OBJ_Door(int x, int y) {
        this.x = x* Constant.TILE_SIZE;
        this.y = y* Constant.TILE_SIZE;
        name = "Door";
        try {
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Items/ItemDoor.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        collision = true;
    }
}
