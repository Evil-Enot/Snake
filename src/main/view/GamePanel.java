package main.view;

import javax.swing.*;
import java.awt.*;

import static main.model.Model.SIZE;

public class GamePanel extends JPanel {

    private Image dot;
    private Image strawberry;
    private boolean inGame;
    private int dots;
    private Point strawberryPoint;

    private int[] x;
    private int[] y;
    private ViewListener viewListener;

    public GamePanel() {
        inGame = true;
        setBackground(Color.black);
        loadImages();
        setFocusable(true);
        viewListener = new ViewListener();
        addKeyListener(viewListener);
    }

    public void updateView() {
        repaint();
    }

    public void loadImages(){
        ImageIcon iis = new ImageIcon("Strawberry.png");
        strawberry = iis.getImage();
        ImageIcon iid = new ImageIcon("dot.png");
        dot = iid.getImage();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
         if (inGame) {
                g.drawImage(strawberry, strawberryPoint.x, strawberryPoint.y, this);
                for (int i = 0; i < dots; i++) {
                    g.drawImage(dot, x[i], y[i], this);
                }
            } else {
                String str = "Game Over";
                g.setColor(Color.white);
                g.drawString(str, 125, SIZE / 2);
            }
    }

    public void setGameOver() {
        inGame = false;
        viewListener.setGameOver();
    }

    public void setStrawberryPoint(int strawberryX, int strawberryY) {
        this.strawberryPoint = new Point(strawberryX, strawberryY);
    }

    public void setDots(int dots) {
        this.dots = dots;
    }

    public void setX(int[] x) {
        this.x = x;
    }

    public void setY(int[] y) {
        this.y = y;
    }

    public boolean getInGame() {
        return inGame;
    }

    public void setInGame(boolean inGame) {
        this.inGame = inGame;
    }
}
