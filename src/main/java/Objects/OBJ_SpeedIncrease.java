package Objects;

import Variables.Constant;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

public class OBJ_SpeedIncrease extends SuperObject {
    public OBJ_SpeedIncrease(int x, int y) {
        this.x = x*Constant.TILE_SIZE;
        this.y = y*Constant.TILE_SIZE;
        name = "SpeedIncrease";
        try {
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Items/ItemSpeedIncrease.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        collision = true;
    }
}
