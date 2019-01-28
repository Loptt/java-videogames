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
    
    protected Rectangle body;
    
    public Item(int x, int y, int width, int height) {
        body = new Rectangle(x, y, width, height);
    }

    public Rectangle getBody() {
        return body;
    }

    public void setBody(Rectangle body) {
        this.body = body;
    }
    
    public abstract void tick();
    public abstract void render(Graphics g);
}
