package Variables;

public class Time {
    public static double timestarted = System.nanoTime();

    public static double getTime() { return (System.nanoTime() - timestarted) * 1E-9; }

}
