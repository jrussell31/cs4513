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
import model.BootType;
import model.GameData;
import model.GameObject;
import model.Immoveable.Collectible.Boot;
import model.Moveable.Ball;
import model.Moveable.Block;
import model.Moveable.Gamer;

/**
 *
 * @author russe_000
 */
public class Water extends Tile {

    public BufferedImage image;

    public Water(float x, float y) {
        super(x, y);

        try {
            image = (BufferedImage) ImageFinder.getImage("ImagesFolder", "Water.png");
        } catch (Exception e) {
        }
    }

    @Override
    public void collide(GameObject O) {
        if (O instanceof Gamer) {
            //Check for boot
            boolean gameOver = true;
            for (Object item : GameData.gamerInventory) {
                if (item instanceof Boot) {
                    if (((Boot) item).type == BootType.WATER) {
                        gameOver = false;
                        break;
                    }
                }
            }
            //Trigger Game End
            if (gameOver) {
                image = (BufferedImage) ImageFinder.getImage("ImagesFolder", "Chip_Drowned.png");
                //TODO Remove the Gamer so That you can see the drowned image. JL 2/19
                DungeonCrawl.bannerPanel.setBannerText("You drowned on Level  " + GameData.currentLevel.getLevelValue());
                ((Gamer)O).setAlive(false);
            }

        }
        //Collide with Block
        if(O instanceof Block)
        {
            this.setAlive(false);
            ((Block)O).setAlive(false);
        }
        //Collide with Ball
        if(O instanceof Ball){
            ((Ball) O).noMove();
        }
    }

    @Override
    public void render(Graphics2D g) {
        g.drawImage(image, (int) super.x, (int) super.y, (int) super.WIDTH, (int) super.HEIGHT, null);

        //Draw Collision Box
        //g.setColor(Color.blue);
        //g.draw(this.getCollisionBox());
    }

}
