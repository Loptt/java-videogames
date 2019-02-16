package videogame;


import java.awt.Graphics;
import java.awt.Rectangle;

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
    
    protected int width;
    protected int height;
    
    public Item(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }
    
    public void setLocation(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }
    
    public boolean intersects(Item item) {
        Rectangle rect = new Rectangle(x,y,width,height);
        return rect.intersects(new Rectangle(item.getX(), item.getY(), item.getWidth(), item.getHeight()));
    }
    
    public abstract void tick();
    public abstract void render(Graphics g);
}
