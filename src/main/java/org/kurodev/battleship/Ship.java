package org.kurodev.battleship;

public class Ship {
    private final Coordinate anchorPoint; // 5 | 6
    private final int size;
    private final boolean[] hits;
    /**
     * 5 | 6  x
     * 5 | 7  x
     * 5 | 8  x
     */
    private final String name;
    private final ShipAlignment alignment;

    public Ship(String name, Coordinate anchorPoint, int size, ShipAlignment alignment) {
        this.anchorPoint = anchorPoint;
        this.size = size;
        this.hits = new boolean[size];
        this.name = name;
        this.alignment = alignment;
    }

    /**
     * point 5 | 7
     */
    public boolean isShipHit(Coordinate point) {
        //TODO find out if a ship has been hit at a given position
        return false;
    }

    /**
     * sets the hit array to true at the position that has been hit, if one has been hit
     *
     * @param point the point to shoot
     * @return true if the shot has hit the ship
     */
    public boolean shoot(Coordinate point) {
        //TODO implement this
        return false;
    }

    public boolean isDestroyed() {
        return false;
    }

    public Coordinate[] getShipCoordinates() {
        Coordinate[] result = new Coordinate[size];
        if (alignment == ShipAlignment.VERTICAL) {
            for (int i = 0; i < size; i++) {
                result[i] = new Coordinate(anchorPoint.getX(), anchorPoint.getY() + i);
            }
        } else {
            //TODO:  implement the above for horizontal growth
        }
        return result;
    }

    public Coordinate getAnchorPoint() {
        return anchorPoint;
    }

    public int getSize() {
        return size;
    }

    public boolean[] getHits() {
        return hits;
    }

    public String getName() {
        return name;
    }

    public ShipAlignment getAlignment() {
        return alignment;
    }


}
