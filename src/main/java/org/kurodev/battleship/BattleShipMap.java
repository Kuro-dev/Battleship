package org.kurodev.battleship;

import java.util.ArrayList;
import java.util.List;

public class BattleShipMap {

    private final int width, height;
    private final List<Coordinate> misses = new ArrayList<>();
    private final List<Ship> ships = new ArrayList<>();

    public BattleShipMap(int width, int height) {
        this.width = width;
        this.height = height;
    }

    @Override
    public String toString() {
        StringBuilder mapString = new StringBuilder();
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                Coordinate point = new Coordinate(x, y);
                boolean shipFound = false;
                for (Ship ship : ships) {
                    if (ship.containsCoordinate(point)) {
                        shipFound = true;
                        if (ship.isShipHit(point)) {
                            mapString.append("X ");
                        } else {
                            mapString.append("O ");
                        }
                    }
                }
                if (!shipFound) {
                    if (misses.contains(point)) {
                        mapString.append("- ");
                    }else{
                        mapString.append("~ ");
                    }
                }
            }
            mapString.append("\n");
        }
        return mapString.toString();
    }

    public List<Coordinate> getMisses() {
        return misses;
    }

    public List<Ship> getShips() {
        return ships;
    }

    public boolean shoot(Coordinate point) {
        for (Ship ship : ships) {
            if (ship.shoot(point)) {
                return true;
            }
        }
        misses.add(point);
        return false;
    }

    public boolean isGameOver() {
        for (Ship ship : ships) {
            if (!ship.isDestroyed()) {
                return false;
            }
        }
        return true;
    }
}
