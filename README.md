# Survival Shooter (Java / Swing)

A simple 2D top-down survival shooter built in **Java (Swing/AWT)**.  
Move your player, aim with the mouse, shoot enemies, collect power-ups, and survive as long as possible.

This project focuses on real-time game programming fundamentals in Java:
game loop timing, input handling, entity updates, collisions, spawning logic, and simple power-up systems.

---

## Gameplay Rules

### Goal
Survive and score points by eliminating enemies. The game ends when your health reaches **0**.

### Controls
- **Move:** `W A S D`
- **Shoot:** **Left Mouse Button** (hold to keep firing)
- **Aim:** move the mouse (player shoots toward the cursor)
- **Pause / Resume:** `P`
- **Restart (after Game Over):** `R`

### Player
- Starts with **5 HP**
- Movement is constrained inside the window boundaries
- Shoots a projectile toward the mouse cursor
- Default fire rate: one shot every **200 ms**

### Enemies
- Spawn every **~2.5 seconds**
- Spawn outside the screen edges (top / bottom / left / right)
- Move toward the player continuously
- Shoot projectiles toward the player every **~1.0–1.5 seconds**
- Each enemy has **1 HP**
- Killing an enemy gives **+10 score**

### Damage & Game Over
- Enemy bullets deal **1 damage**
- If the player’s health reaches **0**, the game shows **GAME OVER**
- Press **R** to reset:
  - health back to 5
  - score reset to 0
  - enemies/bullets/powerups cleared

### Power-Ups
Power-ups spawn every **~15 seconds** at random positions.
Pick them up by touching them.

Available types:
- **heal**: +1 HP (max 5)
- **shield**: invulnerability for **5 seconds** (bullets do no damage)
- **rapid**: faster fire rate for **8 seconds** (cooldown reduced to 100 ms)
- **spread**: triple-shot spread fire for **8 seconds** (3 bullets with slight angle offsets)

---

## Tech Stack

- Java
- Swing / AWT (rendering + window)
- Custom 60 FPS game loop thread
- Basic entity system (Player, Enemy, Bullet, PowerUp)
- Collision handling + spawners

---

## Project Structure

SurvivalShooter/
├─ src/
│ └─ game/
│ ├─ Main.java # Creates the JFrame and starts the game
│ ├─ GamePanel.java # Main canvas: update + render + game state
│ ├─ GameLoop.java # 60 FPS loop (update + repaint)
│ ├─ entities/
│ │ ├─ Player.java # Player movement, shooting, power-up states
│ │ ├─ Enemy.java # Enemy chase + enemy shooting
│ │ ├─ Bullet.java # Projectile movement and drawing
│ │ └─ PowerUp.java # Power-up entity + rendering
│ ├─ input/
│ │ ├─ KeyHandler.java # WASD + pause + restart logic
│ │ └─ MouseHandler.java # Aim + shooting on mouse press
│ ├─ logic/
│ │ ├─ Collision.java # Bullet collisions (enemy/player)
│ │ └─ Spawner.java # Enemy and power-up spawning
│ └─ utils/
│ └─ MathUtils.java # Vector normalization utilities
├─ README.md
└─ .gitignore


---

## How to Run

### Option A — Using an IDE (recommended)
1. Open the project in IDE
2. Run `src/game/Main.java`

### Option B — From the command line
From the repository root:

```bash
javac -d out src/game/Main.java src/game/GamePanel.java src/game/GameLoop.java src/game/entities/*.java src/game/input/*.java src/game/logic/*.java src/game/utils/*.java
java -cp out game.Main
```

If your terminal shell doesn’t expand *.java, compile folder-by-folder or use an IDE

---

## Notes / Known Limitations

- The game uses a straightforward Swing rendering approach (good for learning).

- Difficulty does not scale over time (enemy stats are mostly constant).

- There is no saved progress system (score resets on restart).

---

## Disclaimer

This project is for educational and portfolio purposes.