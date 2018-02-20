/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.Immoveable.Tile;

import DungeonCrawl.DungeonCrawl;
import controller.ImageFinder;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import model.GameData;
import model.GameObject;
import model.Moveable.Gamer;

/**
 *
 * @author russe_000
 */
public class Fire extends Tile {

    public BufferedImage image;
    
    public Fire(float x, float y) {
        super(x, y);
        
        try{
            image = (BufferedImage) ImageFinder.getImage("ImagesFolder", "Fire.png");
        }
        catch(Exception e){}
    }

    @Override
    public void collide(GameObject O) {
        if(O instanceof Gamer){
            image = (BufferedImage) ImageFinder.getImage("ImagesFolder", "Chip_In_Fire.png");
            DungeonCrawl.bannerPanel.setBannerText("You burned on Level  " + GameData.currentLevel.getLevelValue());
            GameData.levelInProgress = false;
        }
    }

    @Override
    public void render(Graphics2D g) {
        g.drawImage(image, (int) super.x, (int) super.y, (int) super.width, (int) super.height, null);

        //Draw Collision Box
        //g.setColor(Color.blue);
        //g.draw(this.getCollisionBox());
    }

}
