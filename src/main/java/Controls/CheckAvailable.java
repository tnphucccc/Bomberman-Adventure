package Controls;
import Entity.Bomb;
import GUI.GameScene;
import GUI.Window;

public class CheckAvailable {
    static KeyHandler keyH = Window.getKeyH();
    static boolean spacePressed;
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
    public static boolean plantBomb(int x, int y){
        if (GameScene.getBombCounter() < GameScene.getBombSize()) {
            if (keyH.spacePressed) {
                spacePressed = true;
            }
            if (!keyH.spacePressed && spacePressed) {
                spacePressed = false;
                return CheckAvailable.checkAvailable(x, y);
            }
        }
        return false;
    }
}
