package main.view;

import javax.swing.*;

public class View extends JFrame {

    private GamePanel gamePanel;

    public View() {
        gamePanel = new GamePanel();

        setTitle("Snake");
        setSize(352, 375);
        setLocation(400, 400);
        add(gamePanel);
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public GamePanel getGamePanel() {
        return gamePanel;
    }
}

