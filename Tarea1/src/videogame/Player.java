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
    private int xVel;
    private int yVel;
    int imageCycle;
    
    private final int acc;
    private final int maxVel;
    private final int maxCollisionFrames;
    private int currCollisionFrames;
    
    private boolean isColliding;

    public Player(int x, int y, int direction, int width, int height, Game game) {
        super(x, y);
        this.direction = direction;
        this.width = width;
        this.height = height;
        this.game = game;
        this.acc = 1;
        this.xVel = 0;
        this.yVel = 0;
        this.imageCycle = 0;
        this.maxVel = 20;
        this.maxCollisionFrames = 15;
        this.currCollisionFrames = 0;
        this.isColliding = false;
    }

    @Override
    public void tick() {
        if (game.getKeyManager().up) {
            if (getyVel() - getAcc() == 0) {
                setyVel(-1);

            } else {
                setyVel(getyVel() - getAcc());
            }
        }
        
        if (game.getKeyManager().down) {
            if (getyVel() + getAcc() == 0) {
                setyVel(1);

            } else {
                setyVel(getyVel() + getAcc());
            }
        }
        
        if (game.getKeyManager().left) {
            if (getxVel() - getAcc() == 0) {
                setxVel(-1);

            } else {
                setxVel(getxVel() - getAcc());
            }
        }
        
        if (game.getKeyManager().right) {
            if (getxVel() + getAcc() == 0) {
                setxVel(1);

            } else {
                setxVel(getxVel() + getAcc());
            }
        }
        
        if (getxVel() >= maxVel) {
            setxVel(maxVel);
        }
        
        if (getxVel() <= -maxVel) {
            setxVel(-maxVel);
        }
        
        if (getyVel() >= maxVel) {
            setyVel(maxVel);
        }
        
        if (getyVel() <= -maxVel) {
            setyVel(-maxVel);
        }
        
        setX(getX() + getxVel());
        setY(getY() + getyVel());
        
        if (getX() + 140 >= game.getWidth()) {
            setxVel(getxVel()*-1);
            isColliding = true;
            currCollisionFrames = 0;
            imageCycle++;
        }
        else if (getX() <= -30) {
            setxVel(getxVel()*-1);
            isColliding = true;
            currCollisionFrames = 0;
            imageCycle++;
        }
        
        if (getY() + 90 >= game.getHeight()) {
            setyVel(getyVel()*-1);
            isColliding = true;
            currCollisionFrames = 0;
            imageCycle++;
        }
        else if (getY() <= -10) {
            setyVel(getyVel()*-1);
            isColliding = true;
            currCollisionFrames = 0;
            imageCycle++;
        }
        
        if (isColliding) {
            currCollisionFrames++;
            
            if (currCollisionFrames >= maxCollisionFrames) {
                isColliding = false;
            }
            
        }
        
        if (imageCycle > 4) {
            imageCycle = 0;
        }
    }
    
    @Override
    public void render(Graphics g) {
        if (isColliding) {
            g.drawImage(Assets.colliding, getX(), getY(), getWidth(), getHeight(), null);
        } else {
            g.drawImage(Assets.playerSkins[imageCycle], getX(), getY(), getWidth(), getHeight(), null);
        }

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
