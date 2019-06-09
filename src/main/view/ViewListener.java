package main.view;

import main.controller.Controller;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class ViewListener extends KeyAdapter {

    private Controller controller;

    public ViewListener() {
        controller = new Controller();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        super.keyPressed(e);
        controller.directionInput(e);
    }
    public void setGameOver() {
        controller.setGameOver();
    }
}
