/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.Immoveable.Tile;

import DungeonCrawl.DungeonCrawl;
import controller.ImageFinder;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import model.BootType;
import model.GameData;
import model.GameObject;
import model.Immoveable.Collectible.Boot;
import model.Moveable.Ball;
import model.Moveable.Gamer;

/**
 *
 * @author russe_000
 */
public class Fire extends Tile {

    public BufferedImage image;

    public Fire(float x, float y) {
        super(x, y);

        try {
            image = (BufferedImage) ImageFinder.getImage("ImagesFolder", "Fire.png");
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
                    if (((Boot) item).type == BootType.FIRE) {
                        gameOver = false;
                    }
                }
            }
            //Trigger Game End
            if (gameOver) {
                image = (BufferedImage) ImageFinder.getImage("ImagesFolder", "Chip_In_Fire.png");
                //TODO Remove the Gamer so That you can see the burned image. JL 2/19
                DungeonCrawl.bannerPanel.setBannerText("You burned on Level  " + GameData.currentLevel.getLevelValue());
                GameData.levelInProgress = false;
            }
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
