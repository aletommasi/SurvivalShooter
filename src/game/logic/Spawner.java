package game.logic;

import game.GamePanel;
import game.entities.Enemy;
import game.entities.PowerUp;

import java.util.Random;

public class Spawner {

    private GamePanel panel;
    private long lastEnemy = 0;
    private long lastPower = 0;
    private Random rnd = new Random();

    public Spawner(GamePanel panel) { this.panel = panel; }

    public void update() {
        long now = System.currentTimeMillis();

        if (now - lastEnemy > 2500) {
            spawnEnemy();
            lastEnemy = now;
        }

        if (now - lastPower > 15000) {
            spawnPowerUp();
            lastPower = now;
        }
    }

    private void spawnEnemy() {
        double x, y;
        switch(rnd.nextInt(4)) {
            case 0: x=rnd.nextInt(GamePanel.WIDTH); y=-20; break;
            case 1: x=rnd.nextInt(GamePanel.WIDTH); y=GamePanel.HEIGHT+20; break;
            case 2: x=-20; y=rnd.nextInt(GamePanel.HEIGHT); break;
            default: x=GamePanel.WIDTH+20; y=rnd.nextInt(GamePanel.HEIGHT);
        }

        int hp = 1;
        double speed = 60 + rnd.nextDouble()*40;
        int shoot = 1000 + rnd.nextInt(500);

        panel.enemies.add(new Enemy(panel,x,y,hp,speed,shoot));
    }

    private void spawnPowerUp() {
        String[] types = {"heal","shield","rapid","spread"};
        String type = types[rnd.nextInt(types.length)];

        double x = 60 + rnd.nextInt(GamePanel.WIDTH-120);
        double y = 60 + rnd.nextInt(GamePanel.HEIGHT-120);

        panel.powerUps.add(new PowerUp(x,y,type));
    }
}
