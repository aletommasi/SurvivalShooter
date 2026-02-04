package game.entities;

import game.GamePanel;
import game.utils.MathUtils;

import java.awt.*;
import java.util.Random;

public class Enemy {

    public double x, y;
    public double speed;
    public int radius = 18;
    public int hp;
    private GamePanel panel;
    private Random rnd = new Random();

    private long lastShot = 0;
    private int shootInterval;

    public Enemy(GamePanel panel, double x, double y, int hp, double speed, int shootInt) {
        this.panel = panel;
        this.x = x; this.y = y;
        this.hp = hp;
        this.speed = speed;
        this.shootInterval = shootInt;
    }

    public void update(double dt, GamePanel panel) {
        double[] dir = MathUtils.unit(panel.player.x - x, panel.player.y - y);
        x += dir[0] * speed * dt;
        y += dir[1] * speed * dt;

        // Shooting
        long now = System.currentTimeMillis();
        if (now - lastShot > shootInterval) {
            lastShot = now;
            double[] u = MathUtils.unit(panel.player.x - x, panel.player.y - y);
            panel.bullets.add(new Bullet(x, y, u[0] * 300, u[1] * 300, false));
        }
    }

    public void draw(Graphics2D g) {
        g.setColor(new Color(160,50,50));
        g.fillOval((int)(x-radius), (int)(y-radius), radius*2, radius*2);
    }
}
