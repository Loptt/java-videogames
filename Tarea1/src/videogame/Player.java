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
    
    private int direction;
    private int width;
    private int height;
    private Game game;
    private int acc;
    private int xVel;
    private int yVel;

    public Player(int x, int y, int direction, int width, int height, Game game) {
        super(x, y);
        this.direction = direction;
        this.width = width;
        this.height = height;
        this.game = game;
        this.acc = 1;
        this.xVel = 0;
        this.yVel = 0;
    }

    @Override
    public void tick() {
        if (game.getKeyManager().up) {
            setyVel(getyVel() - getAcc());
        }
        
        if (game.getKeyManager().down) {
            setyVel(getyVel() + getAcc());

        }
        
        if (game.getKeyManager().left) {
            setxVel(getxVel() - getAcc());
        }
        
        if (game.getKeyManager().right) {
            setxVel(getxVel() + getAcc());
        }
        
        if (getxVel() >= 15) {
            setxVel(15);
        }
        
        if (getxVel() <= -15) {
            setxVel(-15);
        }
        
        if (getyVel() >= 15) {
            setyVel(15);
        }
        
        if (getyVel() <= -15) {
            setyVel(-15);
        }
       
        setX(getX() + xVel);
        setY(getY() + yVel);
        
        if (getX() + 140 >= game.getWidth()) {
            setxVel(getxVel()*-1);
        }
        else if (getX() <= -30) {
            setxVel(getxVel()*-1);
        }
        
        if (getY() + 90 >= game.getHeight()) {
            setyVel(getyVel()*-1);
        }
        else if (getY() <= -10) {
            setyVel(getyVel()*-1);
        }
    }
    
    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.player, getX(), getY(), getWidth(), getHeight(), null);
    }
    
    /**
     * to get direction
     * @return direction
     */
    public int getDirection() {
        return direction;
    }
    
    /**
     * to get width
     * @return width
     */
    public int getWidth() {
        return width;
    }
    
    /**
     * to get height
     * @return height
     */
    public int getHeight() {
        return height;
    }
    
    /**
     * to get xVel
     * @return xVel
     */
    public int getxVel() {
        return xVel;
    }
    
    /**
     * to get yVel
     * @return yVel
     */
    public int getyVel() {
        return yVel;
    }
    
    /**
     * to get acc
     * @return acc
     */
    public int getAcc() {
        return acc;
    }
    
    /**
     * to set xVel
     * @param xVel to modify 
     */
    public void setxVel(int xVel) {
        this.xVel = xVel;
    }
    
    /**
     * to set yVel
     * @param yVel to modify 
     */
    public void setyVel(int yVel) {
        this.yVel = yVel;
    }
    
    /**
     * to set direction
     * @param direction to modify 
     */
    public void setDirection(int direction) {
        this.direction = direction;
    }
    
    /**
     * to set height
     * @param height to modify 
     */
    public void setHeight(int height) {
        this.height = height;
    }
    
    /**
     * to set width
     * @param width to modify 
     */
    public void setWidth(int width) {
        this.width = width;
    }
}
