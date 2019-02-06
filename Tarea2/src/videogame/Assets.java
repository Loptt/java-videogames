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
    public static BufferedImage nums[];
    public static BufferedImage gameover;
    
    public static SoundClip crashSound;
    
    public static void init() {
        background = ImageLoader.loadImage("/images/space_background.png");
        player = ImageLoader.loadImage("/images/earth_player.png");
        enemy = ImageLoader.loadImage("/images/asteroid_enemy.png");
        life = ImageLoader.loadImage("/images/heart.png");
        gameover = ImageLoader.loadImage("/images/game_over.png");
        
        nums = new BufferedImage[3];
        
        nums[2] = ImageLoader.loadImage("/images/1_white.png");
        nums[1] = ImageLoader.loadImage("/images/2_white.png");
        nums[0] = ImageLoader.loadImage("/images/3_white.png");
        
        crashSound = new SoundClip("/sounds/crash.wav");
    }
}
