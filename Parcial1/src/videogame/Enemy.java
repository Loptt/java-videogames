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
public class Enemy extends Item {
    
    private int direction;
    private Game game;
    private int speed;

    public Enemy(int x, int y, int width, int height, Game game) {
        super(x, y, width, height);
        this.game = game;
        this.speed = (int) (Math.random() * 3 + 3);
        reset();
    }

    @Override
    public void tick() {
        
        //Set random speed betwwen 3 and 5 every frame
        speed = (int) (Math.random() * 3 + 3);
        
        //Check if enemy is still out of the screen
        if (getY() <= 0) {
            //Move the enemy vertically downwards
            setLocation((int) getX() , (int) (getY() + speed));
        } else {
            //If already on the screen, change movement to diagonal
            setLocation(((int) getX() + speed * (direction)), (int) getY());
            setLocation((int) getX(), (int) getY() + speed);
        }
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.enemy, (int) getX(), (int) getY(), (int) getWidth(), (int) getHeight(), null);
    }
    
    public void reset() {
        setLocation((int) (Math.random() * game.getWidth() -100) + 50, ((int) (Math.random() * 300 + getHeight()) * -1));
        
        /**
         * Choose a random direction 1 or -1
         * No need to check if there are always enemies in the two directions because that probability of 
         * that happening is 1/32768
         */
        if (((int) (Math.random() * 2)) == 1) {
            setDirection(1);
        } else {
            setDirection(-1);
        }
        
       
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setWidth(int width) {
        this.width = width;
    }
}
