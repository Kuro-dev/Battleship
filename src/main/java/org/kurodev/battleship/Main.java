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
        Player player1 = new Player("Player#1", Main.class.getResourceAsStream("/playermovement/player1Sim.txt"));
        Player player2 = new Player("Player#2", Main.class.getResourceAsStream("/playermovement/player2Sim.txt"));
        BattleShipGame game = new BattleShipGame(player1, player2);
        game.play();
    }
}
