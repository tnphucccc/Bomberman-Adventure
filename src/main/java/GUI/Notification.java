package GUI;

public class Notification {
    public static int bombRadius=1;
    public Notification(int radius){
        setBombRadius(radius);
    }
    public static void setBombRadius(int radius) {
        bombRadius = radius;
    }
    public static int getBombRadius() {
        return bombRadius;
    }
}
