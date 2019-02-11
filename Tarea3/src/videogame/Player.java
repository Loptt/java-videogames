/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package videogame;

import java.awt.Graphics;

/**
 *
 * @author charles
 */
public class Player extends Item {

    private final Game game;
    private int lives;
    private final int speed;
    private int score;
    
    private int prevY;

    public Player(int x, int y, int width, int height, Game game) {
        super(x, y, width, height);
        this.game = game;
        lives = 5;
        speed = 7;
        prevY = 0;
    }

    @Override
    public void tick() {
        
        prevY = (int) getY();
        
        /**
         * Check keyboard inputs
         */
        if (game.getKeyManager().up) {
            setLocation((int) getX(), (int) (getY() - speed));
        }
        
        if (game.getKeyManager().down) {
            setLocation((int) getX(), (int) getY() + speed);
        }
        
        if (game.getKeyManager().left) {
            setLocation( (int) getX() - speed, (int) getY());
        }
        
        if (game.getKeyManager().right) {
            setLocation((int) getX() + speed, (int) getY());
        }
           
        /**
         * Check wall collisions
         */
        if (getX() > game.getWidth()-100) {
            setLocation(game.getWidth()-100, (int) getY());
        }

        if (getX() < -10) {
            setLocation(-10, (int) getY());
        }

        if (getY() > game.getHeight()-100) {
            setLocation((int) getX(), game.getHeight() - 100);
        }

        if (getY() < -10) {
            setLocation((int) getX(), -10);
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

    public int getPrevY() {
        return prevY;
    }

    public void setPrevY(int prevY) {
        this.prevY = prevY;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
