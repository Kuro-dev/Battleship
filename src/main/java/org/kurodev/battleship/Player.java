package org.kurodev.battleship;

import java.io.InputStream;
import java.util.Scanner;

public class Player {
    private final BattleShipMap map = new BattleShipMap(10, 10);
    private final String name;
    private final InputStream inputStream;

    public Player(String name, InputStream in) {
        this.name = name;
        this.inputStream = in;
    }

    public BattleShipMap getMap() {
        return map;
    }

    public String getName() {
        return name;
    }

    public void prepare() {
        Scanner in = new Scanner(inputStream);
        //two 4-size ships      -> Destroyer
        //three 3-size ships    -> Cruiser
        //four 2-size ships     -> Rowboat
        for (int i = 0; i < 2; i++) {
            //we need: x y orientation
            Ship ship = getShipFromUser("Destroyer#" + (i + 1), 4, in);
            map.getShips().add(ship);
        }
        for (int i = 0; i < 3; i++) {
            //we need: x y orientation
            Ship ship = getShipFromUser("Cruiser#" + (i + 1), 3, in);
            map.getShips().add(ship);
        }

        for (int i = 0; i < 4; i++) {
            //we need: x y orientation
            Ship ship = getShipFromUser("Rowboat#" + (i + 1), 2, in);
            map.getShips().add(ship);
        }
    }

    private Ship getShipFromUser(String name, int size, Scanner in) {
        System.out.println(map);
        System.out.println("Enter the coordinates and alignment for a " + name);
        int x = in.nextInt();
        int y = in.nextInt();
        Coordinate point = new Coordinate(x - 1, y - 1);
        ShipAlignment shipAlignment = null;
        while (shipAlignment == null) {
            System.out.println("Please enter the ships alignment: horizontal or vertical");
            String alignment = in.nextLine();
            if (ShipAlignment.HORIZONTAL.name().equalsIgnoreCase(alignment.trim())) {
                shipAlignment = ShipAlignment.HORIZONTAL;
            } else if (ShipAlignment.VERTICAL.name().equalsIgnoreCase(alignment.trim())) {
                shipAlignment = ShipAlignment.VERTICAL;
            }
        }
        //The user has entered a valid alignment for the ship
        return new Ship(name, point, size, shipAlignment);
    }

    public boolean hasLost() {
        return map.isGameOver();
    }

    public void makeTurn(BattleShipMap enemyMap) {
        Scanner in = new Scanner(System.in);
        System.out.println(enemyMap.toString());
        System.out.println(name + ", where do you want to shoot?");
        int x = in.nextInt();
        int y = in.nextInt();
        Coordinate point = new Coordinate(x, y);
        if (enemyMap.shoot(point)) {
            //we hit an enemy ship
            if (enemyMap.isGameOver()) {
                return;
            }
            //the enemy still has ships left
            makeTurn(enemyMap);
        }
    }
}
