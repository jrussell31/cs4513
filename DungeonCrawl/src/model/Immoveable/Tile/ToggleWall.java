/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.Immoveable.Tile;

import controller.ImageFinder;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.HashSet;
import model.GameData;
import model.GameObject;

/**
 *
 * @author russe_000
 */
public class ToggleWall extends Wall {
    
    BufferedImage toggleWallImg;
    
    private boolean open;
    
    public ToggleWall(float x, float y, boolean open){
        super(x, y);
        super.setAlive(true);
        this.open = open;
        
        loadImages();
    }
    
    public boolean isOpen(){
        return open;
    }
    
    public void setOpen(boolean open){
        this.open = open;
    }
    
    public void loadImages(){
        try
        {            
            if(open){
                BufferedImage image = (BufferedImage)ImageFinder.getImage("ImagesFolder", "Toggle_Wall_Open.png");
                toggleWallImg = image;
            }
            else{
                BufferedImage image = (BufferedImage)ImageFinder.getImage("ImagesFolder", "Toggle_Wall_Closed.png");
                toggleWallImg = image;
            }
        } 
        catch(Exception e){
            e.printStackTrace();
        }
    }
    
    @Override
    public void render(Graphics2D g) {
        loadImages();
        
        g.drawImage(toggleWallImg, (int)super.x, (int)super.y, (int)super.WIDTH, (int)super.HEIGHT, null);
    }

    @Override
    public void collide(GameObject O) {
        if(!open){
            GameData.gamer.noMove();
        }
    }
}
