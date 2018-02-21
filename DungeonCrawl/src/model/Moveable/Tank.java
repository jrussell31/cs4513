/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author russe_000
 */
package model.Moveable;

import DungeonCrawl.DungeonCrawl;
import static model.GameData.MAP_WIDTH;
import static model.GameData.MAP_HEIGHT;
import java.awt.Color;
import model.GameData;
import java.awt.Graphics2D;
import controller.ImageFinder;
import controller.ObjectAnimator;
import java.awt.image.BufferedImage;
import model.GameObject;
import model.Immoveable.Tile.Wall;

public class Tank extends Monster{
        
    public BufferedImage[] tank_S;
    public BufferedImage[] tank_N;
    public BufferedImage[] tank_W;
    public BufferedImage[] tank_E;
    
    int counter = 0;
    
    public float dx;
    public float dy;
    
    public int facing = 1; //0 = North, 1 = East, 2 = South, 3 = West,
    public boolean down, up, left = false; 
    public boolean right = true; 
    
    private final ObjectAnimator tankMoves;
    
    public Tank(float x, float y) {
        super(x, y);        
        tankMoves = new ObjectAnimator();
        
        tank_S = new BufferedImage[1];
        tank_N = new BufferedImage[1];
        tank_W = new BufferedImage[1];
        tank_E = new BufferedImage[1];
        
        try {
            BufferedImage image = (BufferedImage)ImageFinder.getImage("ImagesFolder", "Tank_S.png");           
            tank_S[0] = image; 
            image = (BufferedImage)ImageFinder.getImage("ImagesFolder", "Tank_N.png");           
            tank_N[0] = image; 
            image = (BufferedImage)ImageFinder.getImage("ImagesFolder", "Tank_W.png");           
            tank_W[0] = image; 
            image = (BufferedImage)ImageFinder.getImage("ImagesFolder", "Tank_E.png");           
            tank_E[0] = image; 
        } catch(Exception e){
            e.printStackTrace();
        }
        
    }

    @Override
    public void render(Graphics2D g) {
        g.drawImage(tankMoves.getImage(), (int)super.x, (int)super.y, (int)super.height, (int)super.width, 
            null);
        //Draw Collision Box
        g.setColor(Color.blue);
        g.draw(this.getCollisionBox());
    }
    
    @Override
    public void update() {
        dx = super.x;
        dy = super.y;
        
        if (GameData.currentLevel.getLevelTime() > 0) {  
            
            if (right) {
                tankMoves.setFrames(tank_E);
                if (counter == 1000) {
                    counter = 0;
                    for (int i = 0; i < 1; i++) {
                        super.x += super.MOVEMENT;
                    }
                    if (super.x >= 33 * MAP_WIDTH) {
                        facing = 1;
                        tankMoves.setFrames(tank_E);
                        left = true; 
                        right = false; 
                        up = false; 
                        down = false; 
                    }
                    tankMoves.setFrames(tank_E);
                } else {
                    counter += 100;
                }
            }
            else if (left) {
                tankMoves.setFrames(tank_W);
                if (counter == 1000) {
                    counter = 0;
                    
                    for (int i = 0; i < 1; i++) {      
                        super.x -= super.MOVEMENT;
                        //tankMoves.setFrames(tank_W);
                    }
                    
                    if (super.x <= 23 * MAP_WIDTH) {
                        facing = 3;
                        left = false; 
                        right = true; 
                        up = false; 
                        down = false; 
                    } 
                    tankMoves.setFrames(tank_W);
                } else {
                    counter += 100;
                }
            }
        }tankMoves.update();
    }
    
    public void noMove() {
        super.x = dx;
        super.y = dy;
    }
    
    
    @Override
    public void collide(GameObject O){       
        if(O instanceof Wall) {
            noMove();
            /*
            if(blue button pressed) {
                if(left) {
                    left = false;
                    right = true;
                } else if (right) {
                    right = false;
                    left = true;
                }
            }
            */
        }
        
        if(O instanceof Gamer){
            DungeonCrawl.bannerPanel.setBannerText("You colided with the tank on Level  " + GameData.currentLevel.getLevelValue());
            GameData.levelInProgress = false;
        }
        
    }
}