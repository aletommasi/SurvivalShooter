package game.entities;

import java.awt.*;

public class Bullet {

    public double x, y;
    public double vx, vy;
    public boolean fromPlayer;
    public int radius = 4;

    public Bullet(double x, double y, double vx, double vy, boolean fromPlayer) {
        this.x=x; this.y=y; this.vx=vx; this.vy=vy; this.fromPlayer = fromPlayer;
    }

    public void update(double dt) {
        x += vx * dt;
        y += vy * dt;
    }

    public void draw(Graphics2D g) {
        g.setColor(fromPlayer ? new Color(80,200,120) : new Color(220,50,50));
        g.fillOval((int)(x-radius),(int)(y-radius), radius*2,radius*2);
    }
}
