package model;

import static java.lang.Math.abs;

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

    // EFFECTS: return true if direction is up, false otherwise
    public boolean isUp() {
        return this.dir.equals(Direction.UP);
    }

    // EFFECTS: return true if direction is down, false otherwise
    public boolean isDown() {
        return this.dir.equals(Direction.DOWN);
    }

    // EFFECTS: return true if direction is static, false otherwise
    public boolean isStatic() {
        return this.dir.equals(Direction.STATIC);
    }
}
