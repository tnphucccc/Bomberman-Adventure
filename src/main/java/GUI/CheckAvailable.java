package GUI;
import Entity.Bomb;
import java.util.ArrayList;
public class CheckAvailable {
    public static boolean checkAvailable(int x, int y) {
        ArrayList<Bomb> bombList = GameScene.getBombList();
        for (Bomb bomb : bombList) {
            if (bomb.getX() == x && bomb.getY() == y) {
                return false;
            }
        }
        return true;
    }
}
