package org.kurodev.battleship;

public class BattleShipGame {
   private final Player player1;
    private final Player player2;
    private int score;

    public BattleShipGame(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
    }

    public void play() {
        player1.prepare(); //query the positioning of the ships for player#1
        player2.prepare(); //query the positioning of the ships for player#2
        boolean player1Turn = true;
        while (!player1.hasLost() && !player2.hasLost()) {
            if (player1Turn) {
                player1.makeTurn(player2.getMap());
            } else {
                player2.makeTurn(player1.getMap());
            }
            player1Turn = !player1Turn;
        }
    }
}
