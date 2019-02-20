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
    private int speed;
    
    private Animation animationUp;
    private Animation animationDown;
    private Animation animationRight;
    private Animation animationLeft;
    
    private enum Direction { UP, DOWN, LEFT, RIGHT };
    
    private Direction dir;

    public Player(int x, int y, int width, int height, Game game) {
        super(x, y);
        this.direction = direction;
        this.width = width;
        this.height = height;
        this.game = game;
        this.speed = 5;
        
        this.animationUp = new Animation(Assets.playerUp, 100);
        this.animationDown = new Animation(Assets.playerDown, 100);
        this.animationLeft = new Animation(Assets.playerLeft, 100);
        this.animationRight = new Animation(Assets.playerRight, 100);
        
        dir = Direction.RIGHT;
    }

    @Override
    public void tick() {
       
        if (game.getKeyManager().up) {
            dir = Direction.UP;
            setY(getY() - speed);
        }
        
        if (game.getKeyManager().down) {
            dir = Direction.DOWN;
            setY(getY() + speed);
        }
        
        if (game.getKeyManager().left) {
            dir = Direction.LEFT;
            setX(getX() - speed);
        }
        
        if (game.getKeyManager().right) {
            dir = Direction.RIGHT;
            setX(getX() + speed);
        }
        
        if (getX() + 60 >= game.getWidth()) {
            setX(game.getWidth() - 60);
        }
        else if (getX() <= -30) {
            setX(-30);
        }
        
        if (getY() + 80 >= game.getHeight()) {
            setY(game.getHeight() - 80);
        }
        else if (getY() <= -20) {
            setY(-20);
        }
        
        this.animationUp.tick();
        this.animationDown.tick();
        this.animationLeft.tick();
        this.animationRight.tick();
    }

    @Override
    public void render(Graphics g) {
        switch(dir) {
            case UP:
                g.drawImage(animationUp.getCurrentFrame(), getX(), getY(), getWidth(), getHeight(), null);
                break;
            case DOWN:
                g.drawImage(animationDown.getCurrentFrame(), getX(), getY(), getWidth(), getHeight(), null);
                break;
            case LEFT:
                g.drawImage(animationLeft.getCurrentFrame(), getX(), getY(), getWidth(), getHeight(), null);
                break;
            case RIGHT:
                g.drawImage(animationRight.getCurrentFrame(), getX(), getY(), getWidth(), getHeight(), null);
  
                
        }
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
