package Objects;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

public class OBJ_ExtraBomb extends SuperObject {
    public OBJ_ExtraBomb() {
        name = "ExtraBomb";
        try {
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Items/ItemExtraBomb.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        collision = true;
    }
}
