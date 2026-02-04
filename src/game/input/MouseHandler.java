package game.input;

import game.GamePanel;

import java.awt.event.*;

public class MouseHandler implements MouseListener, MouseMotionListener {

    private GamePanel panel;

    public MouseHandler(GamePanel panel) { this.panel = panel; }

    @Override public void mousePressed(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) panel.player.shooting = true;
    }

    @Override public void mouseReleased(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) panel.player.shooting = false;
    }

    @Override public void mouseMoved(MouseEvent e) {
        panel.player.mouseX = e.getX();
        panel.player.mouseY = e.getY();
    }

    @Override public void mouseDragged(MouseEvent e) {
        panel.player.mouseX = e.getX();
        panel.player.mouseY = e.getY();
    }

    @Override public void mouseClicked(MouseEvent e) {}
    @Override public void mouseEntered(MouseEvent e) {}
    @Override public void mouseExited(MouseEvent e) {}
}
