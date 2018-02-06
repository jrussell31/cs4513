/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.Moveable;

import controller.ImageFinder;
import controller.ObjectAnimator;
import java.awt.Graphics;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

/**
 *
 * @author russe_000
 */
public class Fireball extends Monster {
    
    private final int width = 16;
    private final int height = 24;
    
    public BufferedImage[] fireballSprites;
    
    public int facing = 2; //0 = North, 1 = East, 2 = South, 3 = West,
    public boolean turnRight, turnUp, turnDown = false; 
    public boolean turnLeft = true; 
    
    private final ObjectAnimator fireballMoves;
    
    public Fireball(float x, float y) {
        super(x, y);
        fireballMoves = new ObjectAnimator();
        
        fireballSprites = new BufferedImage[1];
        try{
            BufferedImage image = (BufferedImage)ImageFinder.getImage("ImagesFolder", "Fireball.png");           
            fireballSprites[0] = image;                       
        } catch(Exception e){
            e.printStackTrace();
        }
    }
    
    @Override
    public void render(Graphics g) {
        g.drawImage(fireballMoves.getImage(), (int)super.x, (int)super.y, 60, 60, 
            null);
    }

    //@Override
    //public Rectangle2D.Double getCollisionBox() {
        // May need to change to adjust to correct height and width
        //return new Rectangle2D.Double(super.x, super.y, width*3.5, height*2.5);
    //}
    
    
    @Override
    public void update() {
            fireballMoves.setFrames(fireballSprites);
            if (turnLeft){
                for(int i = 0; i < 4; i++){
                    super.x -= 50;
                }
                //facing = 3;
                fireballMoves.setFrames(fireballSprites);
                turnLeft = false; 
                turnRight = false; 
                turnUp = false; 
                turnDown = true; 
            } 
            else if(turnDown)
            {
                for(int i = 0; i < 4; i++){
                    super.y += 50;
                }
                //facing = 1;
                fireballMoves.setFrames(fireballSprites);
                turnLeft = false; 
                turnRight = true; 
                turnUp = false; 
                turnDown = false;
            }
            else if(turnRight)
            {
                for(int i = 0; i < 4; i++){
                    super.x += 50;
                }
                //facing = 2;
                fireballMoves.setFrames(fireballSprites);
                turnLeft = false; 
                turnRight = false; 
                turnUp = true; 
                turnDown = false;
            } 
            else if(turnUp)
            {
                for(int i = 0; i < 4; i++){
                    super.y -= 50;
                }
                //facing = 0;
                fireballMoves.setFrames(fireballSprites);
                turnLeft = true; 
                turnRight = false; 
                turnUp = false; 
                turnDown = false;
            }
            fireballMoves.update(); 
    }

    @Override
    public Rectangle2D.Double getCollisionBox() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void findCollision() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    }
