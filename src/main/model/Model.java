package main.model;

import main.util.Direction;
import main.view.GamePanel;
import main.view.View;

import java.awt.*;
import java.util.Random;

public class Model {

    public static final int SIZE = 320;
    public static final int DOT_SIZE = 16;
    public static final int ALL_DOTS = 400;

    private int strawberryX;
    private int strawberryY;

    private int[] x;
    private int[] y;

    private int dots;
    private Direction direction;
    private GamePanel gamePanel;

    public Model() {
        x = new int[ALL_DOTS];
        y = new int[ALL_DOTS];

        View view = new View();
        gamePanel = view.getGamePanel();

        initGame();
        updatePanel();
    }

    private void updatePanel() {
        gamePanel.setStrawberryPoint(strawberryX, strawberryY);
        gamePanel.setDots(dots);
        gamePanel.setX(x);
        gamePanel.setY(y);
        gamePanel.updateView();
    }

    private void initGame() {
        dots = 3;

        for (int i = 0; i < dots; i++) {
            x[i] = 48 - i * DOT_SIZE;
            y[i] = 48;
        }

        createStrawberry();
    }

    public void update() {
        move();
        checkStraw();
        checkCollisions();
        updatePanel();
    }

    public void move() {
        for (int i = dots; i > 0; i--) {
            x[i] = x[i - 1];
            y[i] = y[i - 1];
        }

        if (direction == Direction.LEFT) {
            x[0] -= DOT_SIZE;
        }

        if (direction == Direction.RIGHT) {
            x[0] += DOT_SIZE;
        }

        if (direction == Direction.UP) {
            y[0] -= DOT_SIZE;
        }

        if (direction == Direction.DOWN) {
            y[0] += DOT_SIZE;
        }
    }

    public void checkStraw() {
        if (x[0] == strawberryX && y[0] == strawberryY) {
            dots++;
            createStrawberry();
        }
    }

    private void createStrawberry() {
        strawberryX = new Random().nextInt(20) * DOT_SIZE;
        strawberryY = new Random().nextInt(20) * DOT_SIZE;
    }

    public void checkCollisions() {
        for (int i = dots; i > 0; i--) {
            if (i > 1 && x[0] == x[i] && y[0] == y[i]) {
                gamePanel.setGameOver();
            }
        }

        if (x[0] > SIZE) {
            gamePanel.setGameOver();
        }

        if (x[0] < 0) {
            gamePanel.setGameOver();
        }

        if (y[0] > SIZE) {
            gamePanel.setGameOver();
        }

        if (y[0] < 0) {
            gamePanel.setGameOver();
        }
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public Point getStrawberry() {
        return new Point(strawberryX, strawberryY);
    }

    public void setStrawberry(int strawberryX, int strawberryY) {
        this.strawberryX = strawberryX;
        this.strawberryY = strawberryY;
    }

    public int[] getX() {
        return x;
    }

    public void setX(int[] x) {
        this.x = x;
    }

    public int[] getY() {
        return y;
    }

    public void setY(int[] y) {
        this.y = y;
    }

    public int getDots() {
        return dots;
    }

    public GamePanel getGamePanel() {
        return gamePanel;
    }
}
