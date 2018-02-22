package model.Immoveable.Collectible;

import controller.ImageFinder;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import model.GameData;
import model.GameObject;
import model.Moveable.Gamer;

public class Chip extends Collectible {

    public BufferedImage chip;

    public Chip(float x, float y) {
        super(x, y);

        try {
            BufferedImage image = (BufferedImage) ImageFinder.getImage("ImagesFolder", "Chip.png");
            chip = image;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    ;

    @Override
    public void render(Graphics2D g) {
        if (this.isAlive() && this.isDisplayed) {
            g.drawImage(chip, (int) super.x, (int) super.y, (int) super.WIDTH, (int) super.HEIGHT, null);
        }

        //Draw Collision Box
        //g.setColor(Color.blue);
        //g.draw(this.getCollisionBox());
    }

    @Override
    public void collide(GameObject O) {
        if(O instanceof Gamer){
            this.setAlive(false);
            GameData.collectChip();
        }
    }
}
