package test;

import main.model.Model;
import main.util.Direction;
import main.view.GamePanel;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static main.model.Model.DOT_SIZE;
import static main.model.Model.SIZE;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class Tests {

    private Model model;

    @Before
    public void init() {
        model = new Model();
    }

    @Test
    public void move() {
        int[] x = {1, 2, 3, 4};
        int[] y = {5, 6, 7, 8};

        model.setX(x);
        model.setY(y);
        model.setDirection(Direction.RIGHT);
        model.move();

        //right direction
        int[] expectedX = {1 + DOT_SIZE, 1, 2, 3};
        int[] expectedY = {5, 5, 6, 7};

        assertTrue(Arrays.equals(expectedX, model.getX()));
        assertTrue(Arrays.equals(expectedY, model.getY()));

        model.setDirection(Direction.LEFT);
        model.move();

        //left direction
        expectedX = new int[] {17 - DOT_SIZE, 17, 1, 2};
        expectedY = new int[] {5, 5, 5, 6};

        assertTrue(Arrays.equals(expectedX, model.getX()));
        assertTrue(Arrays.equals(expectedY, model.getY()));

        model.setDirection(Direction.UP);
        model.move();

        //Up direction
        expectedX = new int[] {1, 1, 17, 1};
        expectedY = new int[] {5 - DOT_SIZE, 5, 5, 5};

        assertTrue(Arrays.equals(expectedX, model.getX()));
        assertTrue(Arrays.equals(expectedY, model.getY()));

        model.setDirection(Direction.DOWN);
        model.move();

        //Down direction
        expectedX = new int[] {1, 1, 1, 17};
        expectedY = new int[] {-11 + DOT_SIZE, -11, 5, 5};

        assertTrue(Arrays.equals(expectedX, model.getX()));
        assertTrue(Arrays.equals(expectedY, model.getY()));
    }

    @Test
    public void checkStraw() {
        model.setStrawberry(0, 0);
        model.setX(new int[] {1, 2, 3});
        model.setY(new int[] {1, 2, 3});

        model.checkStraw();

        assertEquals(3, model.getDots());
        assertEquals(0, model.getStrawberry().x);
        assertEquals(0, model.getStrawberry().y);

        model.setX(new int[] {0, 2, 3});
        model.setY(new int[] {0, 2, 3});

        model.checkStraw();

        assertEquals(4, model.getDots());
        assertTrue(model.getStrawberry().x != 0 && model.getStrawberry().y != 0);
    }

    @Test
    public void checkCollision() {
        GamePanel gamePanel = model.getGamePanel();

        int[] collisionX = model.getX();
        collisionX[0] = SIZE + 1;
        model.setX(collisionX);

        model.checkCollisions();
        assertFalse(gamePanel.getInGame());

        gamePanel.setInGame(true);

        collisionX = model.getX();
        collisionX[0] = -1;
        model.setX(collisionX);

        model.checkCollisions();
        assertFalse(gamePanel.getInGame());

        gamePanel.setInGame(true);

        int[] collisionY = model.getY();
        collisionY[0] = SIZE + 1;
        model.setY(collisionY);

        model.checkCollisions();
        assertFalse(gamePanel.getInGame());

        gamePanel.setInGame(true);

        collisionY = model.getY();
        collisionY[0] = -1;
        model.setY(collisionY);

        model.checkCollisions();
        assertFalse(gamePanel.getInGame());

        int[] expectedX = new int[] {1, 2, 1, 2};
        int[] expectedY = new int[] {1, 2, 1, 2};

        model.setX(expectedX);
        model.setY(expectedY);

        model.checkCollisions();
        assertFalse(gamePanel.getInGame());
    }
}
