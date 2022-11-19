package Objects;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_BlastRadius extends SuperObject {
    public OBJ_BlastRadius() {
        name = "BlastRadius";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/Items/ItemBlastRadius.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
