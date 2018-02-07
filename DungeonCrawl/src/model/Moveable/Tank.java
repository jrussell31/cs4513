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

import java.awt.Color;
import model.GameData;
import java.awt.Graphics2D;
import controller.ImageFinder;
import controller.ObjectAnimator;
import java.awt.image.BufferedImage;

public class Tank extends Monster{
        
    public BufferedImage[] tank_S;
    public BufferedImage[] tank_N;
    public BufferedImage[] tank_W;
    public BufferedImage[] tank_E;
    
    
    public int facing = 2; //0 = North, 1 = East, 2 = South, 3 = West,
    public boolean right, up, left = false; 
    public boolean down = true; 
    
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
        g.drawImage(tankMoves.getImage(), (int)super.x, (int)super.y, 50, 50, 
            null);
        //Draw Collision Box
        //g.setColor(Color.blue);
        //g.draw(this.getCollisionBox());
    }
    
    @Override
    public void update() {
        //tankMoves.setFrames(tank_S);
        if (GameData.time > 0) {     
            if (down) {
                super.y += 5;
                tankMoves.setFrames(tank_S);
                
                if (super.y == 500) {
                    facing = 2;
                    //tankMoves.setFrames(tank_S);
                    left = false; 
                    right = true; 
                    up = false; 
                    down = false; 
                }
            }
            else if (right) {            
                super.x += 5;
                tankMoves.setFrames(tank_E);

                if (super.x == 800) {
                    facing = 1;
                    //tankMoves.setFrames(tank_E);
                    left = false; 
                    right = false; 
                    up = true; 
                    down = false; 
                }
            }
            else if (up) {
                super.y -= 5;
                tankMoves.setFrames(tank_N);

                if (super.y == 300) {
                    facing = 0;
                    //tankMoves.setFrames(tank_N);
                    left = true; 
                    right = false; 
                    up = false; 
                    down = false; 
                }
            }
            else if (left) {
                super.x -= 5;
                tankMoves.setFrames(tank_W);

                if (super.x == 700) {
                    facing = 3;
                    //tankMoves.setFrames(tank_W);
                    left = false; 
                    right = false; 
                    up = false; 
                    down = true; 
                }
            }
        }
        tankMoves.update();
    }
}
