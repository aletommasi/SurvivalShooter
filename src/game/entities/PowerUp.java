package game.entities;

import java.awt.*;

public class PowerUp {
    public double x,y;
    public String type;
    public int radius = 12;

    public PowerUp(double x, double y, String type){
        this.x=x; this.y=y; this.type=type;
    }

    public void draw(Graphics2D g){
        switch(type){
            case "heal": g.setColor(new Color(80,200,120)); break;
            case "shield": g.setColor(new Color(70,140,240)); break;
            case "rapid": g.setColor(new Color(240,220,90)); break;
            case "spread": g.setColor(new Color(200,100,220)); break;
            default: g.setColor(new Color(200,120,200));
        }
        g.fillOval((int)(x-radius),(int)(y-radius), radius*2, radius*2);
    }
}
