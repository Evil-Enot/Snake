package main.controller;

import main.model.Model;
import main.util.Direction;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class Controller implements ActionListener {

    private boolean buttonPressed;
    private boolean inGame;

    private static final Model model = new Model();
    private Direction direction;
    private Timer timer;


    public Controller() {
        inGame = true;

        timer = new Timer(250, this);
        timer.start();
    }


    public static void main(String[] args) {
        model.update();
        model.setDirection(Direction.RIGHT);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (inGame) {
            model.update();
            buttonPressed = false;
        }
    }

    public void directionInput(KeyEvent key) {
        int keyCode = key.getKeyCode();

        switch (keyCode) {
            case KeyEvent.VK_UP:
                if (direction != Direction.DOWN && !buttonPressed) {
                    direction = Direction.UP;
                    buttonPressed = true;
                }
                break;
            case KeyEvent.VK_DOWN:
                if (direction != Direction.UP && !buttonPressed) {
                    direction = Direction.DOWN;
                    buttonPressed = true;
                }
                break;
            case KeyEvent.VK_LEFT:
                if (direction != Direction.RIGHT && !buttonPressed) {
                    direction = Direction.LEFT;
                    buttonPressed = true;
                }
                break;
            case KeyEvent.VK_RIGHT:
                if (direction != Direction.LEFT && !buttonPressed) {
                    direction = Direction.RIGHT;
                    buttonPressed = true;
                }
                break;
        }

        if (direction != null) {
            model.setDirection(direction);
        }
    }

    public void setGameOver() {
        timer.stop();
        inGame = false;
    }
}
