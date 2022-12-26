package GUI;
import Entity.Bomb;
public class CheckAvailable {
    //private static int x, y;
    public static boolean checkAvailable(int x, int y) {
        x = ((x + 16) / 48) * 48;
        y = ((y + 16) / 48) * 48;
        for (Bomb bomb : GameScene.getBombList()) {
            if (bomb.getX() == x && bomb.getY() == y) {
                return false;
            }
        }
        return true;
    }
}
