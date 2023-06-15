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
        Coordinate[] coordinates = getShipCoordinates();
        for (int i = 0; i < coordinates.length; i++) {
            if (coordinates[i].equals(point)) {
                return hits[i];
            }
        }
        return false;
    }

    /**
     * sets the hit array to true at the position that has been hit, if one has been hit
     *
     * @param point the point to shoot
     * @return true if the shot has hit the ship
     */
    public boolean shoot(Coordinate point) {
        Coordinate[] coordinates = getShipCoordinates();
        for (int i = 0; i < coordinates.length; i++) {
            if (coordinates[i].equals(point)) {
                if (hits[i]) {
                    return false;
                } else {
                    hits[i] = true;
                    return true;
                }
            }
        }
        return false;
    }

    public boolean isDestroyed() {
        for (boolean hit : hits) {
            if (!hit) {
                return false;
            }
        }
        return true;
    }

    public Coordinate[] getShipCoordinates() {
        Coordinate[] result = new Coordinate[size];
        if (alignment == ShipAlignment.VERTICAL) {
            for (int i = 0; i < size; i++) {
                //since the ship is growing Vertically we only manipulate the Y value, X does not change
                result[i] = new Coordinate(anchorPoint.getX(), anchorPoint.getY() + i);
            }
        } else {
            for (int i = 0; i < size; i++) {
                //since the ship is growing Horizontally we only manipulate the X value, Y does not change
                result[i] = new Coordinate(anchorPoint.getX() + i, anchorPoint.getY());
            }
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

    public boolean containsCoordinate(Coordinate point) {
        for (Coordinate shipCoordinate : getShipCoordinates()) {
            if (shipCoordinate.equals(point)) {
                return true;
            }
        }
        return false;
    }
}
