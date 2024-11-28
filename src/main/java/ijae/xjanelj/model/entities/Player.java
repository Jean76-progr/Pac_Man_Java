package ijae.xjanelj.model.entities;

public abstract class Player extends Entity {
    protected boolean hasKey;

    public Player(int row, int col) {
        super(row, col);
        this.hasKey = false;
    }

    public boolean hasKey() {
        return hasKey;
    }

    public void setHasKey(boolean hasKey) {
        this.hasKey = hasKey;
    }
}