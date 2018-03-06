package model.Moveable;

import controller.ImageFinder;
import controller.ObjectAnimator;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import model.Direction;
import model.GameData;
import model.GameObject;
import model.Immoveable.Tile.Wall;

public class Frog extends Monster {

    public BufferedImage[] frogSprites;
    private ObjectAnimator frogMoves;
    private int previousTime;

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
        g.drawImage(frogMoves.getImage(), (int) super.x, (int) super.y, (int) super.WIDTH, (int) super.HEIGHT,
                null);
        //Draw Collision Box
        //g.setColor(Color.blue);
        //g.draw(this.getCollisionBox());
    }

    @Override
    public void update() {
        super.update();
        
        if (null != this.direction) {
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
            float sign;

            // If x distance greater than y distance
            if (Math.abs(xDistanceBetweenGamer) > Math.abs(yDistanceBetweenGamer)) {
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
            else if (Math.abs(xDistanceBetweenGamer) < Math.abs(yDistanceBetweenGamer)) {
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
                if (xDistanceBetweenGamer != 0) {
                    // Add tile space in x direction based on whether gamer is in positive or negative direction
                    this.x += (Math.signum(xDistanceBetweenGamer) * Monster.MOVEMENT);
                    this.direction = (Math.signum(xDistanceBetweenGamer) > 0) ? Direction.LEFT : Direction.RIGHT;
                }
            }
        }

        previousTime = GameData.time;
    }

    @Override
    public void collide(GameObject O) {
        super.collide(O);

        if (O instanceof Block || O instanceof Wall) {
            noMove();
        }
    }
}
