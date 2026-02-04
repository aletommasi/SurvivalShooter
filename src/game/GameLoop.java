package game;

public class GameLoop extends Thread {

    private GamePanel panel;
    private final int TARGET_FPS = 60;
    private final double FRAME_TIME = 1.0 / TARGET_FPS;

    public GameLoop(GamePanel panel) {
        this.panel = panel;
    }

    @Override
    public void run() {
        long last = System.nanoTime();

        while (true) {
            long now = System.nanoTime();
            double dt = (now - last) / 1e9;
            last = now;

            panel.update(dt);
            panel.repaint();

            try {
                Thread.sleep((long) (FRAME_TIME * 1000));
            } catch (Exception ignored) {}
        }
    }
}
