package ijae.xjanelj.model.entities;

import ijae.xjanelj.model.Direction;

public class Ghost extends Entity {
    private Direction direction;

    public Ghost(int row, int col) {
        super(row, col);
        direction = Direction.values()[(int)(Math.random() * Direction.values().length)];
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }
}