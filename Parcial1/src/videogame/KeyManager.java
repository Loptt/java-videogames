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
    
    public boolean upright;
    public boolean downright;
    public boolean upleft;
    public boolean downleft;
    
    private boolean keys[];
    
    public KeyManager() {
        keys = new boolean[256];
    }

    @Override
    public void keyTyped(KeyEvent e) {
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
        upright = keys[KeyEvent.VK_E];
        downright = keys[KeyEvent.VK_D];
        upleft = keys[KeyEvent.VK_Q];
        downleft = keys[KeyEvent.VK_A];
    }
}
