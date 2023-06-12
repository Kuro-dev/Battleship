package battleship;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.kurodev.battleship.Coordinate;
import org.kurodev.battleship.Ship;
import org.kurodev.battleship.ShipAlignment;

public class ShipTests {
    private final Coordinate anchorpoint = new Coordinate(5, 6);

    private Ship horizontal;
    private Ship vertical;

    @BeforeEach
    public void prepareShips() {
        horizontal = new Ship("Testhip horizontal 3", anchorpoint, 3, ShipAlignment.HORIZONTAL);
        vertical = new Ship("Testhip Vertical 3", anchorpoint, 3, ShipAlignment.VERTICAL);
    }

    @Test
    public void TestCoordinatesHorizontal() {
        Coordinate pos1 = new Coordinate(6, 6);
        Coordinate pos2 = new Coordinate(7, 6);

        Assertions.assertEquals(anchorpoint, horizontal.getShipCoordinates()[0]);
        Assertions.assertEquals(pos1, horizontal.getShipCoordinates()[1]);
        Assertions.assertEquals(pos2, horizontal.getShipCoordinates()[2]);
    }

    @Test
    public void TestCoordinatesVertical() {
        Coordinate pos1 = new Coordinate(5, 7);
        Coordinate pos2 = new Coordinate(5, 8);


        Assertions.assertEquals(anchorpoint, vertical.getShipCoordinates()[0]);
        Assertions.assertEquals(pos1, vertical.getShipCoordinates()[1]);
        Assertions.assertEquals(pos2, vertical.getShipCoordinates()[2]);
    }

    @Test
    public void TestIsShipHitHorizontal() {
        Coordinate shotPoint = new Coordinate(6, 6);
        Assertions.assertFalse(horizontal.isShipHit(shotPoint));
        horizontal.getHits()[1] = true; //simulate a hit
        Assertions.assertTrue(horizontal.isShipHit(shotPoint));
    }

    @Test
    public void TestIsShipHitVertical() {
        Coordinate shotPoint = new Coordinate(5, 8);
        Assertions.assertFalse(vertical.isShipHit(shotPoint));
        vertical.getHits()[1] = true; //simulate a hit
        Assertions.assertTrue(vertical.isShipHit(shotPoint));
    }

    @Test
    public void TestShootHorizontal() {
        Coordinate shotPoint = new Coordinate(6, 6);
        Assertions.assertTrue(horizontal.shoot(shotPoint));
        Assertions.assertTrue(horizontal.getHits()[1], "The index of the hit has not been set properly");
    }

    @Test
    public void TestShootVertical() {
        Coordinate shotPoint = new Coordinate(5, 8);
        Assertions.assertTrue(vertical.shoot(shotPoint));
        Assertions.assertTrue(vertical.getHits()[2], "The index of the hit has not been set properly");
    }

    @Test
    public void TestVerticalShipDestroyed() {
        Assertions.assertFalse(vertical.isDestroyed(), "The ship is not destroyed yet");

        Assertions.assertTrue(vertical.shoot(anchorpoint));
        Assertions.assertFalse(vertical.isDestroyed(), "The ship is not destroyed yet");

        Assertions.assertFalse(vertical.shoot(new Coordinate(7, 7)), "This shot should be a miss and therefore should not return true");
        Assertions.assertFalse(vertical.isDestroyed(), "The ship is not destroyed yet");

        Assertions.assertTrue(vertical.shoot(new Coordinate(5, 7)));
        Assertions.assertFalse(vertical.isDestroyed(), "The ship is not destroyed yet");

        Assertions.assertTrue(vertical.shoot(new Coordinate(5, 8)));
        Assertions.assertTrue(vertical.isDestroyed(), "The ship should be destroyed after 3 hits yet");
    }

    @Test
    public void TestHorizontalShipDestroyed() {
        Assertions.assertFalse(horizontal.isDestroyed(), "The ship is not destroyed yet");

        Assertions.assertTrue(horizontal.shoot(anchorpoint));
        Assertions.assertFalse(horizontal.isDestroyed(), "The ship is not destroyed yet");

        Assertions.assertFalse(horizontal.shoot(new Coordinate(7, 7)), "This shot should be a miss and therefore should not return true");
        Assertions.assertFalse(horizontal.isDestroyed(), "The ship is not destroyed yet");

        Assertions.assertTrue(horizontal.shoot(new Coordinate(6, 6)));
        Assertions.assertFalse(horizontal.isDestroyed(), "The ship is not destroyed yet");

        Assertions.assertTrue(horizontal.shoot(new Coordinate(7, 6)));
        Assertions.assertTrue(horizontal.isDestroyed(), "The ship should be destroyed after 3 hits yet");
    }


}
