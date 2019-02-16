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
    public static BufferedImage gameover;
    public static BufferedImage exploding;
    
    public static SoundClip crashSound;
    public static SoundClip eliminateSound;
    public static SoundClip coinSound;
    
    public static void init() {
        background = ImageLoader.loadImage("/images/space_background.jpg");
        player = ImageLoader.loadImage("/images/player_super.png");
        life = ImageLoader.loadImage("/images/heart.png");
        enemy = ImageLoader.loadImage("/images/asteroid_enemy.png");
        exploding = ImageLoader.loadImage("/images/explosion.png");
        gameover = ImageLoader.loadImage("/images/game_over.png");
        
        crashSound = new SoundClip("/sounds/crash.wav");
        eliminateSound = new SoundClip("/sounds/crash.wav");
        coinSound = new SoundClip("/sounds/coin.wav");
    }
}
