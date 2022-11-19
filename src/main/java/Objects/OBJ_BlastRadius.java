package Objects;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

public class OBJ_BlastRadius extends SuperObject {
    public OBJ_BlastRadius() {
        name = "BlastRadius";
        try {
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Items/ItemBlastRadius.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
