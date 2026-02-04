package game.entities;

import game.GamePanel;
import game.input.KeyHandler;
import game.utils.MathUtils;

import java.awt.*;

public class Player {

    public double x, y;
    public double speed = 220;
    public int size = 28;

    public int health = 5;
    private GamePanel panel;

    public boolean shooting = false;
    private long lastShot = 0;
    private int cooldown = 200;

    public int mouseX = 0, mouseY = 0;

    // Power-up state
    private boolean shieldActive = false;
    private long shieldEndTime = 0;

    private boolean rapidActive = false;
    private long rapidEndTime = 0;

    private boolean spreadActive = false;
    private long spreadEndTime = 0;

    public Player(GamePanel panel, double x, double y) {
        this.panel = panel;
        this.x = x; this.y = y;
    }

    public void update(double dt, KeyHandler keyHandler) {
        // Movement
        double dx = 0, dy = 0;
        if (keyHandler.keys.contains(java.awt.event.KeyEvent.VK_W)) dy -= 1;
        if (keyHandler.keys.contains(java.awt.event.KeyEvent.VK_S)) dy += 1;
        if (keyHandler.keys.contains(java.awt.event.KeyEvent.VK_A)) dx -= 1;
        if (keyHandler.keys.contains(java.awt.event.KeyEvent.VK_D)) dx += 1;

        double[] u = MathUtils.unit(dx, dy);
        x += u[0] * speed * dt;
        y += u[1] * speed * dt;

        x = Math.max(size, Math.min(GamePanel.WIDTH - size, x));
        y = Math.max(size, Math.min(GamePanel.HEIGHT - size, y));

        // Shooting
        if (shooting) tryShoot();

        // Gestione timer power-up
        long now = System.currentTimeMillis();
        if (shieldActive && now > shieldEndTime) shieldActive = false;
        if (rapidActive && now > rapidEndTime) {
            rapidActive = false;
            cooldown = 200;
        }
        if (spreadActive && now > spreadEndTime) spreadActive = false;
    }

    private void tryShoot() {
        long now = System.currentTimeMillis();
        if (now - lastShot < cooldown) return;
        lastShot = now;

        double[] dir = MathUtils.unit(mouseX - x, mouseY - y);

        if (spreadActive) {
            // Sparo multiplo: tre proiettili con leggero offset
            double angleOffset = Math.PI / 12; // 15 gradi
            panel.bullets.add(new Bullet(x, y, rotateX(dir[0], dir[1], -angleOffset)*420, 
                                                 rotateY(dir[0], dir[1], -angleOffset)*420, true));
            panel.bullets.add(new Bullet(x, y, dir[0]*420, dir[1]*420, true));
            panel.bullets.add(new Bullet(x, y, rotateX(dir[0], dir[1], angleOffset)*420, 
                                                 rotateY(dir[0], dir[1], angleOffset)*420, true));
        } else {
            panel.bullets.add(new Bullet(x, y, dir[0]*420, dir[1]*420, true));
        }
    }

    // Rotazione 2D per lo spread
    private double rotateX(double dx, double dy, double angle) {
        return dx*Math.cos(angle) - dy*Math.sin(angle);
    }
    private double rotateY(double dx, double dy, double angle) {
        return dx*Math.sin(angle) + dy*Math.cos(angle);
    }

    public void draw(Graphics2D g) {
        if (shieldActive) {
            g.setColor(new Color(50,200,250,120));
            g.fillOval((int)(x-size), (int)(y-size), size*2, size*2);
        }

        g.setColor(Color.WHITE);
        g.fillRect((int)(x - size/2), (int)(y - size/2), size, size);

        g.setColor(Color.LIGHT_GRAY);
        g.drawLine((int)x, (int)y, mouseX, mouseY);
    }

    /** SETTER POWER-UP */
    public void activateShield(long durationMs) {
        shieldActive = true;
        shieldEndTime = System.currentTimeMillis() + durationMs;
    }

    public void activateRapid(long durationMs) {
        rapidActive = true;
        cooldown = 100;
        rapidEndTime = System.currentTimeMillis() + durationMs;
    }

    public void activateSpread(long durationMs) {
        spreadActive = true;
        spreadEndTime = System.currentTimeMillis() + durationMs;
    }

    /** Gestione danni considerando lo scudo */
    public void takeDamage(int dmg) {
        if (shieldActive) return;
        health -= dmg;
        if (health < 0) health = 0;
    }
}
