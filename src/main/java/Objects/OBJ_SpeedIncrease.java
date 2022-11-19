package Objects;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

public class OBJ_SpeedIncrease extends SuperObject {
    public OBJ_SpeedIncrease() {
        name = "SpeedIncrease";
        try {
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Items/ItemSpeedIncrease.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
