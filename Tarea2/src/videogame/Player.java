/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package videogame;

import java.awt.Graphics;
import java.awt.Point;

/**
 *
 * @author charles
 */
public class Player extends Item {

    private final Game game;
    private int lives;
    private boolean isPressed;

    public Player(int x, int y, int width, int height, Game game) {
        super(x, y, width, height);
        this.game = game;
        this.lives = 3;
        this.isPressed = false;
        
    }

    @Override
    public void tick() {
       if (game.getMouseManager().isIzquierdo()) {
           
           Point p = new Point(game.getMouseManager().getX(), game.getMouseManager().getY());
           
           if (contains(p) || isPressed) {
               setLocation(game.getMouseManager().getX() - (int) getWidth() / 2
                   , game.getMouseManager().getY() - (int) getHeight() / 2);
               isPressed = true;
           }
           
           if (getX() > game.getWidth()-100) {
               setLocation(game.getWidth()-100, (int) getY());
           }
           
           if (getX() < -30) {
               setLocation(-30, (int) getY());
           }
           
           if (getY() > game.getHeight()-100) {
               setLocation((int) getX(), game.getHeight() - 100);
           }
           
           if (getY() < -30) {
               setLocation((int) getX(), -30);
           }
       } else {
           isPressed = false;
       }
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.player, (int) getX(), (int) getY(),
                (int) getWidth(), (int) getHeight(), null);
    }

    public int getLives() {
        return lives;
    }

    public void setLives(int lives) {
        this.lives = lives;
    }
}
