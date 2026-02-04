package game;

import game.entities.*;
import game.input.*;
import game.logic.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class GamePanel extends JPanel {

    public static final int WIDTH = 900;
    public static final int HEIGHT = 600;

    // Entities
    public Player player;
    public List<Enemy> enemies = new ArrayList<>();
    public List<Bullet> bullets = new ArrayList<>();
    public List<PowerUp> powerUps = new ArrayList<>();

    // Logic
    public Spawner spawner = new Spawner(this);
    public boolean paused = false;
    public boolean gameOver = false;
    public int score = 0;

    // Input handlers
    public KeyHandler keyHandler;
    public MouseHandler mouseHandler;

    // Game loop
    private GameLoop loop;

    public GamePanel() {
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setBackground(new Color(30, 30, 34));
        setFocusable(true);

        keyHandler = new KeyHandler(this);
        mouseHandler = new MouseHandler(this);

        addKeyListener(keyHandler);
        addMouseListener(mouseHandler);
        addMouseMotionListener(mouseHandler);

        player = new Player(this, WIDTH / 2, HEIGHT / 2);
    }

    public void start() {
        loop = new GameLoop(this);
        loop.start();
    }

    /** MAIN UPDATE */
    public void update(double dt) {
        if (paused || gameOver) return;

        player.update(dt, keyHandler);

        enemies.forEach(e -> e.update(dt, this));
        bullets.forEach(b -> b.update(dt));

        // Gestione raccolta power-up
        // Gestione raccolta power-up
    for (Iterator<PowerUp> it = powerUps.iterator(); it.hasNext();) {
        PowerUp p = it.next();
        double dx = player.x - p.x;
        double dy = player.y - p.y;
        double distance = Math.sqrt(dx*dx + dy*dy);

        if (distance < player.size/2 + p.radius) {
            // Applica effetto del bonus
            switch (p.type) {
                case "heal": 
                    player.health = Math.min(player.health + 1, 5); 
                    break;
                case "rapid": 
                    player.activateRapid(8000); // 8 secondi
                    break;
                case "shield": 
                    player.activateShield(5000); // 5 secondi
                    break;
                case "spread": 
                    player.activateSpread(8000); // 8 secondi
                    break;
            }
            it.remove();
        }
    }
        Collision.handleCollisions(this);
        spawner.update();
    }

    /** RENDERING */
    @Override
    protected void paintComponent(Graphics g0) {
        super.paintComponent(g0);
        Graphics2D g = (Graphics2D) g0;

        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Powerups
        for (PowerUp p : powerUps) p.draw(g);

        // Bullets
        for (Bullet b : bullets) b.draw(g);

        // Enemies
        for (Enemy e : enemies) e.draw(g);

        // Player
        player.draw(g);

        // HUD
        g.setColor(Color.WHITE);
        g.drawString("Punteggio: " + score, WIDTH - 150, 20);
        g.drawString("Vita: " + player.health, 20, 20);

        if (gameOver) {
            g.setFont(g.getFont().deriveFont(40f));
            g.setColor(Color.RED);
            g.drawString("GAME OVER", WIDTH/2-150, HEIGHT/2);
        }
    }
}
