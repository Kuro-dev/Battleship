package org.kurodev.battleship;

public class Main {
    /**
     * Battleship Things to keep track of
     * - score
     * - the ships (and their state)
     * - whose turn it is
     * - misses
     * <p>
     * Battleship Rules
     * - Ships cannot overlap
     * - They cant be right next to each other
     * - if you hit a shot you get another turn
     * -  cant hit the same part of a ship twice
     * <p>
     * Single responsibility principle
     * - every method should have exactly 1 purpose
     * -- every method name should describe exactly what it does
     * - Every class should handle one responsibility
     */
    public static void main(String[] args) {
        BattleShipGame game = new BattleShipGame();
        Ship ship = new Ship("Battlecruiser", new Coordinate(5, 6), 3, ShipAlignment.VERTICAL);
    }
}
