package game.input;

import game.GamePanel;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashSet;

public class KeyHandler implements KeyListener {

    public HashSet<Integer> keys = new HashSet<>();
    private GamePanel panel;

    public KeyHandler(GamePanel panel) { this.panel = panel; }

    @Override
    public void keyPressed(KeyEvent e) {
        keys.add(e.getKeyCode());
        if (e.getKeyCode() == KeyEvent.VK_P) panel.paused = !panel.paused;
        if (panel.gameOver && e.getKeyCode() == KeyEvent.VK_R) {
            // Restart
            panel.player.health = 5;
            panel.player.x = GamePanel.WIDTH/2;
            panel.player.y = GamePanel.HEIGHT/2;
            panel.score = 0;
            panel.enemies.clear();
            panel.bullets.clear();
            panel.powerUps.clear();
            panel.gameOver = false;
        }
    }

    @Override public void keyReleased(KeyEvent e) { keys.remove(e.getKeyCode()); }
    @Override public void keyTyped(KeyEvent e) {}
}
