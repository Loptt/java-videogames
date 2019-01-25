package videogame;


import java.awt.Graphics;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author charles
 */
public abstract class Item {
    
    protected int x;
    protected int y;
    
    /**
     * Set the initial values to create the item
     * @param x <b>x</b> position of the object
     * @param y <b>y</b> position of the object
     */
    public Item(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    /**
     * Get the x value
     * @return x
     */
    public int getX() {
        return x;
    }
    
    /**
     * Get the y value
     * @return y
     */
    public int getY() {
        return y;
    }
    
    /**
     * Set the x value
     * @param x to modify
     */
    public void setX(int x) {
        this.x = x;
    }
    
    /**
     * Set the y value
     * @param y to modify
     */
    public void setY(int y) {
        this.y = y;
    }
    
    /**
     * To update the position of the item every tick
     */
    public abstract void tick();
    
    /**
     * To paint the item
     * @param g <b>Graphics</b> object to paint the item
     */
    public abstract void render(Graphics g);
}
