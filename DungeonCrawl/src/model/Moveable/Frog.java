package model.Moveable;

import controller.ImageFinder;
import controller.ObjectAnimator;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.Random;
import model.Direction;
import model.GameData;
import model.GameObject;
import model.Immoveable.Tile.Wall;

public class Frog extends Monster {

    public BufferedImage[] frogSprites;
    private ObjectAnimator frogMoves;
    private double previousTime;

    public Frog(float x, float y) {
        super(x, y);

        frogMoves = new ObjectAnimator();
        previousTime = GameData.time;
        this.direction = Direction.UP;
        loadImages();
    }

    public void loadImages() {
        frogSprites = new BufferedImage[4];
        try {
            frogSprites[0] = (BufferedImage) ImageFinder.getImage("ImagesFolder", "Frog_N.png");
            frogSprites[1] = (BufferedImage) ImageFinder.getImage("ImagesFolder", "Frog_S.png");
            frogSprites[2] = (BufferedImage) ImageFinder.getImage("ImagesFolder", "Frog_E.png");
            frogSprites[3] = (BufferedImage) ImageFinder.getImage("ImagesFolder", "Frog_W.png");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void render(Graphics2D g) {
        loadImages();
        g.drawImage(frogMoves.getImage(), (int) super.x, (int) super.y, (int) Monster.WIDTH, (int) Monster.HEIGHT,
                null);
        //Draw Collision Box
        //g.setColor(Color.blue);
        //g.draw(this.getCollisionBox());
    }

    @Override
    public void update() {
        super.update();

        if (this.direction != null) {
            switch (this.direction) {
                case UP:
                    this.frogMoves.setFrames(new BufferedImage[]{frogSprites[0]});
                    break;
                case DOWN:
                    this.frogMoves.setFrames(new BufferedImage[]{frogSprites[1]});
                    break;
                case LEFT:
                    this.frogMoves.setFrames(new BufferedImage[]{frogSprites[2]});
                    break;
                case RIGHT:
                    this.frogMoves.setFrames(new BufferedImage[]{frogSprites[3]});
                    break;
                default:
                    break;
            }
        }

        if (GameData.time < previousTime) {

            float xDistanceBetweenGamer = GameData.gamer.x - this.x;
            float yDistanceBetweenGamer = GameData.gamer.y - this.y;
            float absXDistance = Math.abs(xDistanceBetweenGamer);
            float absYDistance = Math.abs(yDistanceBetweenGamer);

            // If x distance greater than y distance
            if (absXDistance > absYDistance) {
                if (yDistanceBetweenGamer == 0) {
                    // Add tile space in x direction based on whether gamer is in positive or negative direction
                    this.x += (Math.signum(xDistanceBetweenGamer) * Monster.MOVEMENT);
                    this.direction = (Math.signum(xDistanceBetweenGamer) > 0) ? Direction.LEFT : Direction.RIGHT;
                } else {
                    // Add tile space in y direction based on whether gamer is in positive or negative direction
                    this.y += (Math.signum(yDistanceBetweenGamer) * Monster.MOVEMENT);
                    this.direction = (Math.signum(yDistanceBetweenGamer) > 0) ? Direction.DOWN : Direction.UP;
                }
            } // If y distance greater than x distance
            else if (absXDistance < absYDistance) {
                if (xDistanceBetweenGamer == 0) {
                    // Add tile space in y direction based on whether gamer is in positive or negative direction
                    this.y += (Math.signum(yDistanceBetweenGamer) * Monster.MOVEMENT);
                    this.direction = (Math.signum(yDistanceBetweenGamer) > 0) ? Direction.DOWN : Direction.UP;
                } else {
                    // Add tile space in x direction based on whether gamer is in positive or negative direction
                    this.x += (Math.signum(xDistanceBetweenGamer) * Monster.MOVEMENT);
                    this.direction = (Math.signum(xDistanceBetweenGamer) > 0) ? Direction.LEFT : Direction.RIGHT;
                }
            } // If x and y distance are equal
            else {
                Random rand = new Random();
                boolean chooseX = (rand.nextFloat() < 0.5);
                
                // Jump tile space in x direction based on whether gamer is in positive or negative direction
                if(chooseX){
                    this.x += (Math.signum(xDistanceBetweenGamer) * Monster.MOVEMENT);
                    this.direction = (Math.signum(xDistanceBetweenGamer) > 0) ? Direction.LEFT : Direction.RIGHT;
                }
                // Jump tile space in y direction based on whether gamer is in positive or negative direction
                else{
                    this.y += (Math.signum(yDistanceBetweenGamer) * Monster.MOVEMENT);
                    this.direction = (Math.signum(yDistanceBetweenGamer) > 0) ? Direction.DOWN : Direction.UP;
                }
            }
        }

        previousTime = GameData.time;
    }

    @Override
    public void collide(GameObject O) {
        super.collide(O);

        if (O instanceof Block || O instanceof Wall || O instanceof Monster) {
            noMove();
        }
    }
}
