package Main;

import GUI.Window;

public class Main {
    public static void main(String[] args) {
        Window window = Window.getWindow();
        Thread thread = new Thread(window);
        thread.start();
    }
}