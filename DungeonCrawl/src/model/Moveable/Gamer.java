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

import controller.ImageFinder;
import controller.ObjectAnimator;
import java.awt.Graphics;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import model.GameData;
import model.GameObject;
import model.Immoveable.Collectible.Boot;
import model.Immoveable.Collectible.Chip;
import model.Immoveable.Collectible.Collectible;
import model.Immoveable.Collectible.Key;

public class Gamer extends MoveableObject {
    private final int width = 32;
    private final int height = 32;
       
    public BufferedImage[] leftIdle;
    public BufferedImage[] rightIdle;
    public BufferedImage[] downIdle;
    public BufferedImage[] upIdle;
    
    private boolean left = false;
    private boolean right = false;
    private boolean up = false;
    private boolean down = false;
    
    public int facing = 2; //0 = North, 1 = East, 2 = South, 3 = West,
    
    private final ObjectAnimator gamerMoves;
    
    public Gamer(float x, float y) {
        super(x, y);
        
        gamerMoves = new ObjectAnimator();              
        upIdle = new BufferedImage[1];
        rightIdle = new BufferedImage[1];
        downIdle = new BufferedImage[1];
        leftIdle = new BufferedImage[1];     
        setImages();
    }
    
    public void setImages(){
        try{
            BufferedImage image = (BufferedImage)ImageFinder.getImage("ImagesFolder", "Chip_S.png");
            
            downIdle[0] = image;
            
            image = (BufferedImage)ImageFinder.getImage("ImagesFolder", "Chip_N.png");
            
            upIdle[0] = image;
            
            image = (BufferedImage)ImageFinder.getImage("ImagesFolder", "Chip_W.png");
            
            leftIdle[0] = image;
            
            image = (BufferedImage)ImageFinder.getImage("ImagesFolder", "Chip_E.png");
            
            rightIdle[0] = image;
            
            image = (BufferedImage)ImageFinder.getImage("ImagesFolder", "Chip_S.png");      
        } catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public void setLeft(boolean b)
    {
        left = b;
    }
    
    public void setRight(boolean b)
    {
        right = b;
    }
    
    public void setUp(boolean b)
    {
        up = b;
    }
    
    public void setDown(boolean b)
    {
        down = b;
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(gamerMoves.getImage(), (int)super.x, (int)super.y, 32, 32, 
            null);
    }

    @Override
    public Rectangle2D.Double getCollisionBox() {
        // May need to change to adjust to correct height and width
        return new Rectangle2D.Double(super.x, super.y, width*3.5, height*2.5);
    }
    
    @Override
    public void update() {
        if(left)
        {
            super.x -= 10;
            
            facing = 3;
            gamerMoves.setFrames(leftIdle);
        } 
        else if(right)
        {
            super.x += 10;
            
            facing = 1;
            gamerMoves.setFrames(rightIdle);
        }
        
        if(down)
        {
            super.y += 10;
            
            facing = 2;
            gamerMoves.setFrames(downIdle);
        } 
        else if(up)
        {
            super.y -= 10;
            
            facing = 0;
            gamerMoves.setFrames(upIdle);
        }
        
        if (!(right && down && left && up))
        {
            switch(facing){
                case 0:
                    gamerMoves.setFrames(upIdle);
                    break;
                case 1:
                    gamerMoves.setFrames(rightIdle);
                    break;
                case 2:
                    gamerMoves.setFrames(downIdle);
                    break;
                case 3:
                    gamerMoves.setFrames(leftIdle);
                    break;
            }
            gamerMoves.setDelay(-1);
        }
        else
        {
            gamerMoves.setDelay(100);
        }
        
        gamerMoves.update();
        
        //Gamer ghostGamer = new Gamer(super.x + dx, super.y - dy);
        
        // Need to check for collisions between ghostGamer and collidibles
    }

    @Override
    public void findCollision() {
        for(GameObject object: GameData.gameObjects)
        {
            if(object instanceof Monster)
            {
                if(this.getCollisionBox().intersects(
                    object.getCollisionBox()))
                {
                    
                }
            }
            else if(object instanceof Collectible)
            {
                if(object instanceof Key)
                {
                    if(this.getCollisionBox().intersects(
                        object.getCollisionBox()))
                    {
                        
                    }
                }
                else if(object instanceof Boot)
                {
                    if(this.getCollisionBox().intersects(
                        object.getCollisionBox()))
                    {
                    }
                }
                else if(object instanceof Chip)
                {
                    if(this.getCollisionBox().intersects(
                        object.getCollisionBox()))
                    {
                        
                    }
                }
            }
        }
    }
}