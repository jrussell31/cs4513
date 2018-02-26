package model.Immoveable.Tile;

import controller.ImageFinder;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import model.Direction;
import model.GameObject;
import model.Moveable.MoveableObject;

public class Ice extends Floor {

    public BufferedImage[] image;

    public Ice(float x, float y) {
        super(x, y);
        super.direction = Direction.NONE;

        loadImages();
    }

    public Ice(float x, float y, Direction d) {
        super(x, y);
        super.direction = d;

        loadImages();
    }

    @Override
    public void collide(GameObject O) {
        if (O instanceof MoveableObject) {              
            switch (this.direction) {
                case NE:
                    switch (((MoveableObject) O).moving) {
                        case RIGHT:
                            ((MoveableObject) O).sliding(true);
                            ((MoveableObject) O).moving = Direction.DOWN;
                            break;
                        case UP:
                            ((MoveableObject) O).sliding(true);
                            ((MoveableObject) O).moving = Direction.LEFT;
                            break;
                        default:                            
                            ((MoveableObject) O).noMove();
                            ((MoveableObject) O).moving = ((MoveableObject) O).moving.getOppositeDirection();
                    }
                    break;
                case NW:
                    switch (((MoveableObject) O).moving) {
                        case LEFT:
                            ((MoveableObject) O).sliding(true);
                            ((MoveableObject) O).moving = Direction.DOWN;
                            break;
                        case UP:
                            ((MoveableObject) O).sliding(true);
                            ((MoveableObject) O).moving = Direction.RIGHT;
                            break;
                        default:
                            ((MoveableObject) O).noMove();
                            ((MoveableObject) O).moving = ((MoveableObject) O).moving.getOppositeDirection();
                    }
                    break;
                case SE:
                    switch (((MoveableObject) O).moving) {
                        case RIGHT:
                            ((MoveableObject) O).sliding(true);
                            ((MoveableObject) O).moving = Direction.UP;
                            break;
                        case DOWN:
                            ((MoveableObject) O).sliding(true);
                            ((MoveableObject) O).moving = Direction.LEFT;
                            break;
                        default:
                            ((MoveableObject) O).noMove();
                            ((MoveableObject) O).moving = ((MoveableObject) O).moving.getOppositeDirection();
                    }
                    break;
                case SW:
                    switch (((MoveableObject) O).moving) {
                        case LEFT:
                            ((MoveableObject) O).sliding(true);
                            ((MoveableObject) O).moving = Direction.UP;
                            break;
                        case DOWN:
                            ((MoveableObject) O).sliding(true);
                            ((MoveableObject) O).moving = Direction.RIGHT;
                            break;
                        default:
                            ((MoveableObject) O).noMove();
                            ((MoveableObject) O).moving = ((MoveableObject) O).moving.getOppositeDirection();
                    }
                    break;
                default:
                    ((MoveableObject) O).sliding(true);
            }
            
        }
    }

    @Override
    public void render(Graphics2D g) {
        switch (direction) {
            case NE:
                g.drawImage(image[1], (int) super.x, (int) super.y, (int) super.HEIGHT, (int) super.WIDTH, null);
                break;
            case NW:
                g.drawImage(image[2], (int) super.x, (int) super.y, (int) super.HEIGHT, (int) super.WIDTH, null);
                break;
            case SE:
                g.drawImage(image[3], (int) super.x, (int) super.y, (int) super.HEIGHT, (int) super.WIDTH, null);
                break;
            case SW:
                g.drawImage(image[4], (int) super.x, (int) super.y, (int) super.HEIGHT, (int) super.WIDTH, null);
                break;
            default:
                g.drawImage(image[0], (int) super.x, (int) super.y, (int) super.HEIGHT, (int) super.WIDTH, null);
                break;
        }
    }

    private void loadImages() {
        image = new BufferedImage[5];
        try {
            image[0] = (BufferedImage) ImageFinder.getImage("ImagesFolder", "Ice.png");
            image[1] = (BufferedImage) ImageFinder.getImage("ImagesFolder", "Ice_NE.png");
            image[2] = (BufferedImage) ImageFinder.getImage("ImagesFolder", "Ice_NW.png");
            image[3] = (BufferedImage) ImageFinder.getImage("ImagesFolder", "Ice_SE.png");
            image[4] = (BufferedImage) ImageFinder.getImage("ImagesFolder", "Ice_SW.png");
        } catch (Exception e) {
        }
    }

}
