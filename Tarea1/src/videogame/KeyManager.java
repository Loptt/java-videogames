/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package videogame;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 *
 * @author charles
 */
public class KeyManager implements KeyListener {
    
    public boolean up;
    public boolean down;
    public boolean left;
    public boolean right;
    
    private boolean keys[];
    private boolean arrowKeys[];
    
    public KeyManager() {
        keys = new boolean[256];
        arrowKeys = new boolean[4];
    }

    @Override
    public void keyTyped(KeyEvent e) {
        //  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyPressed(KeyEvent e) {
        keys[e.getKeyCode()] = true;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        keys[e.getKeyCode()] = false;
    }
    
    public void tick() {
        if (keys[KeyEvent.VK_UP]) {
            if (!arrowKeys[0]) {
                up = true;
                arrowKeys[0] = true;
            }
            else {
                up = false;
            }
        } else
            arrowKeys[0] = false;
        
        if (keys[KeyEvent.VK_DOWN]) {
            if (!arrowKeys[1]) {
                down = true;
                arrowKeys[1] = true;
            }
            else {
                down = false;
            }
        } else
            arrowKeys[1] = false;
        
        if (keys[KeyEvent.VK_LEFT]) {
            if (!arrowKeys[2]) {
                left = true;
                arrowKeys[2] = true;
            }
            else {
                left = false;
            }
        } else
            arrowKeys[2] = false;
        
        if (keys[KeyEvent.VK_RIGHT]) {
            if (!arrowKeys[3]) {
                right = true;
                arrowKeys[3] = true;
            }
            else {
                right = false;
            }
        } else
            arrowKeys[3] = false;
    }
}
