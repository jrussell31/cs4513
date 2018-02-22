/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.Immoveable.Tile;

import controller.ImageFinder;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import model.GameData;
import model.GameObject;
import model.Moveable.Ball;
import model.Moveable.Gamer;
import model.Moveable.Monster;
import model.Moveable.Tank;

/**
 *
 * @author russe_000
 */
public class Wall extends Tile {

    public BufferedImage image;

    public Wall(float x, float y) {
        super(x, y);

        try {
            image = (BufferedImage) ImageFinder.getImage("ImagesFolder", "Wall.png");
        } catch (Exception e) {
        }
    }

    @Override
    public void collide(GameObject O) {
        if (O instanceof Gamer) {
            GameData.gamer.noMove();
        }else if (O instanceof Ball){
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
