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
public abstract class Item extends Rectangle {
    
    
    public Item(int x, int y, int width, int height) {
        super(x, y, width, height);
    }
    
    public abstract void tick();
    public abstract void render(Graphics g);
}
