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
        
        if (getxVel() >= 10) {
            setxVel(10);
        }
        
        if (getyVel() >= 10) {
            setyVel(10);
        }
       
        setX(getX() + xVel);
        setY(getY() + yVel);
        
        if (getX() + 60 >= game.getWidth()) {
            setxVel(getxVel()*-1);
        }
        else if (getX() <= -30) {
            setxVel(getxVel()*-1);
        }
        
        if (getY() + 80 >= game.getHeight()) {
            setyVel(getyVel()*-1);
        }
        else if (getY() <= -20) {
            setyVel(getyVel()*-1);
        }
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.player, getX(), getY(), getWidth(), getHeight(), null);
    }

    public int getDirection() {
        return direction;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getxVel() {
        return xVel;
    }

    public int getyVel() {
        return yVel;
    }

    public int getAcc() {
        return acc;
    }

    public void setxVel(int xVel) {
        this.xVel = xVel;
    }

    public void setyVel(int yVel) {
        this.yVel = yVel;
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
