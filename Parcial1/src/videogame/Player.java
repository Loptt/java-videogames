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

    private Game game;
    private int speed;
    private int lives;
    private int score;

    public Player(int x, int y, int width, int height, Game game) {
        super(x, y, width, height);
        this.game = game;
        this.speed = 3;
        this.lives = (int) (Math.random() * 3 + 3);
        this.score = 0;
    }

    @Override
    public void tick() {
        /**
         * Handle keyboard input
         */
        if (game.getKeyManager().upright) {
            setLocation((int) getX(), (int) getY() - speed);
            setLocation((int) (getX() + speed), (int) getY());
        }
        
        else if (game.getKeyManager().downright) {
            setLocation((int) getX(), (int) getY() + speed);
            setLocation((int) (getX() + speed), (int) getY());
        }
        
        else if (game.getKeyManager().upleft) {
            setLocation((int) getX(), (int) getY() - speed);
            setLocation((int) (getX() - speed), (int) getY());
        }
        
        else if (game.getKeyManager().downleft) {
            setLocation((int) getX(), (int) getY() + speed);
            setLocation((int) (getX() - speed), (int) getY());
        }
        
        
        /**
         * Prevent player from going out of bounds
         */
        if (getX() + 150 >= game.getWidth()) {
            setLocation((int) game.getWidth() - 150, (int) getY());
        }
        else if (getX() <= 0) {
            setLocation(0, (int) getY());
        }
        
        if (getY() + 150 >= game.getHeight()) {
            setLocation((int) getX(), (int) game.getHeight() - 150);
        }
        else if (getY() <= 0) {
            setLocation((int) getX(), 0);
        }
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.player, (int) getX(), (int) getY(), (int) getWidth(), (int) getHeight(), null);
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getLives() {
        return lives;
    }

    public void setLives(int lives) {
        this.lives = lives;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
