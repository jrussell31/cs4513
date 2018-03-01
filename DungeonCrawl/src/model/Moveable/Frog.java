package model.Moveable;

import controller.ImageFinder;
import controller.ObjectAnimator;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import model.GameData;
import model.GameObject;

public class Frog extends Monster {

    public BufferedImage[] frogSprites;
    private ObjectAnimator frogMoves;
    private int previousTime;

    public Frog(float x, float y) {
        super(x, y);

        frogMoves = new ObjectAnimator();
        previousTime = GameData.time;
        loadImages();
    }

    public void loadImages() {
        frogSprites = new BufferedImage[1];
        try {
            frogSprites[0] = (BufferedImage) ImageFinder.getImage("ImagesFolder", "Frog_N.png");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void render(Graphics2D g) {
        loadImages();
        g.drawImage(frogMoves.getImage(), (int) super.x, (int) super.y, (int) super.WIDTH, (int) super.HEIGHT,
                null);
        //Draw Collision Box
        //g.setColor(Color.blue);
        //g.draw(this.getCollisionBox());
    }

    @Override
    public void update() {
        this.frogMoves.setFrames(frogSprites);

        if (GameData.time < previousTime) {
            
            float xDistanceBetweenGamer = Math.abs(this.x - (GameData.gamer.x / 32));
            float yDistanceBetweenGamer = Math.abs(this.y - (GameData.gamer.y / 32));

            if (GameData.gamer.x == this.x && GameData.gamer.y != this.y) {
                if (GameData.gamer.y > this.y) {
                    this.y += 32;
                } else {
                    this.y -= 32;
                }
            } else if (GameData.gamer.y == this.y && GameData.gamer.x != this.x) {
                if (GameData.gamer.x > this.x) {
                    this.x += 32;
                } else {
                    this.x -= 32;
                }
            } else if (GameData.gamer.y != this.y && GameData.gamer.x != this.x) {
                if (xDistanceBetweenGamer < yDistanceBetweenGamer) {
                    if (GameData.gamer.x > this.x) {
                        this.x += 32;
                    } else {
                        this.x -= 32;
                    }
                } else if (xDistanceBetweenGamer > yDistanceBetweenGamer) {
                    if (GameData.gamer.y > this.y) {
                        this.y += 32;
                    } else {
                        this.y -= 32;
                    }
                } else {
                    if (GameData.gamer.x > this.x) {
                        this.x += 32;
                    } else {
                        this.x -= 32;
                    }
                }
            }
            
            previousTime = GameData.time;
        }
    }

    @Override
    public void collide(GameObject O) {
        super.collide(O);
    }
}
