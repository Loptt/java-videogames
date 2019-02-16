/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package videogame;

import java.awt.image.BufferedImage;

/**
 *
 * @author charles
 */
public class Assets {

    public static BufferedImage background;
    public static BufferedImage player;
    public static BufferedImage enemy;
    public static BufferedImage life;
    
    public static BufferedImage over;
    
    public static SoundClip eliminateSound;
    public static SoundClip coinSound;
    
    public static void init() {
        background = ImageLoader.loadImage("/images/background_mario.jpg");
        player = ImageLoader.loadImage("/images/mario.png");
        enemy = ImageLoader.loadImage("/images/waluigi.png");
        life = ImageLoader.loadImage("/images/heart.png");
        over = ImageLoader.loadImage("/images/game_over.png");
        
        eliminateSound = new SoundClip("/sounds/crash.wav");
        coinSound = new SoundClip("/sounds/coin.wav");
    }
}
