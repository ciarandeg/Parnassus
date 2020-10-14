package model;

// Represents the type of motion between two notes: up, down, or static
public class Motion {
    enum Direction {
        UP,
        DOWN,
        STATIC
    }

    Direction dir;

    public Motion(Note n0, Note n1) {
        int p0 = n0.getPitch();
        int p1 = n1.getPitch();
        this.dir = p0 > p1 ? Direction.DOWN : p0 < p1 ? Direction.UP : Direction.STATIC;
    }

    public boolean isUp() {
        return this.dir.equals(Direction.UP);
    }

    public boolean isDown() {
        return this.dir.equals(Direction.DOWN);
    }
}
