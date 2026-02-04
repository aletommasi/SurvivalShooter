package game.logic;

import game.GamePanel;
import game.entities.*;

import java.util.Iterator;

public class Collision {

    public static void handleCollisions(GamePanel panel) {

        // Bullets
        for (Iterator<Bullet> it = panel.bullets.iterator(); it.hasNext(); ) {
            Bullet b = it.next();

            if (b.x < 0 || b.x > GamePanel.WIDTH || b.y < 0 || b.y > GamePanel.HEIGHT) {
                it.remove();
                continue;
            }

            if (b.fromPlayer) {
                for (Iterator<Enemy> ei = panel.enemies.iterator(); ei.hasNext(); ) {
                    Enemy e = ei.next();
                    if (dist(b.x,b.y,e.x,e.y) < e.radius) {
                        e.hp--;
                        it.remove();
                        if (e.hp <= 0) {
                            panel.score += 10;
                            ei.remove();
                        }
                        break;
                    }
                }
            } else {
                if (Math.abs(b.x - panel.player.x) < panel.player.size &&
                    Math.abs(b.y - panel.player.y) < panel.player.size) {
                    it.remove();
                    panel.player.takeDamage(1); // <-- usa il metodo takeDamage invece di decrementare direttamente
                    if (panel.player.health <= 0) panel.gameOver = true;
                }
            }
        }
    }

    private static double dist(double x1,double y1,double x2,double y2){
        return Math.hypot(x1-x2,y1-y2);
    }
}
