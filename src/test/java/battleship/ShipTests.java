package battleship;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.kurodev.battleship.Coordinate;
import org.kurodev.battleship.Ship;
import org.kurodev.battleship.ShipAlignment;

import static org.junit.jupiter.api.Assertions.*;

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

        assertEquals(anchorpoint, horizontal.getShipCoordinates()[0]);
        assertEquals(pos1, horizontal.getShipCoordinates()[1]);
        assertEquals(pos2, horizontal.getShipCoordinates()[2]);
    }

    @Test
    public void TestCoordinatesVertical() {
        Coordinate pos1 = new Coordinate(5, 7);
        Coordinate pos2 = new Coordinate(5, 8);


        assertEquals(anchorpoint, vertical.getShipCoordinates()[0]);
        assertEquals(pos1, vertical.getShipCoordinates()[1]);
        assertEquals(pos2, vertical.getShipCoordinates()[2]);
    }

    @Test
    public void TestIsShipHitHorizontal() {
        Coordinate shotPoint = new Coordinate(6, 6);
        assertFalse(horizontal.isShipHit(shotPoint));
        horizontal.getHits()[1] = true; //simulate a hit
        assertTrue(horizontal.isShipHit(shotPoint));
    }

    @Test
    public void TestIsShipHitVertical() {
        Coordinate shotPoint = new Coordinate(5, 7);
        assertFalse(vertical.isShipHit(shotPoint));
        vertical.getHits()[1] = true; //simulate a hit
        assertTrue(vertical.isShipHit(shotPoint));
    }

    @Test
    public void TestShootHorizontal() {
        Coordinate shotPoint = new Coordinate(6, 6);
        assertTrue(horizontal.shoot(shotPoint));
        assertTrue(horizontal.getHits()[1], "The index of the hit has not been set properly");
    }

    @Test
    public void TestShootVertical() {
        Coordinate shotPoint = new Coordinate(5, 8);
        assertTrue(vertical.shoot(shotPoint));
        assertTrue(vertical.getHits()[2], "The index of the hit has not been set properly");
    }

    @Test
    public void TestVerticalShipDestroyed() {
        assertFalse(vertical.isDestroyed(), "The ship is not destroyed yet");

        assertTrue(vertical.shoot(anchorpoint));
        assertFalse(vertical.isDestroyed(), "The ship is not destroyed yet");

        assertFalse(vertical.shoot(new Coordinate(7, 7)), "This shot should be a miss and therefore should not return true");
        assertFalse(vertical.isDestroyed(), "The ship is not destroyed yet");

        assertTrue(vertical.shoot(new Coordinate(5, 7)));
        assertFalse(vertical.isDestroyed(), "The ship is not destroyed yet");

        assertTrue(vertical.shoot(new Coordinate(5, 8)));
        assertTrue(vertical.isDestroyed(), "The ship should be destroyed after 3 hits yet");
    }

    @Test
    public void TestHorizontalShipDestroyed() {
        assertFalse(horizontal.isDestroyed(), "The ship is not destroyed yet");

        assertTrue(horizontal.shoot(anchorpoint));
        assertFalse(horizontal.isDestroyed(), "The ship is not destroyed yet");

        assertFalse(horizontal.shoot(new Coordinate(7, 7)), "This shot should be a miss and therefore should not return true");
        assertFalse(horizontal.isDestroyed(), "The ship is not destroyed yet");

        assertTrue(horizontal.shoot(new Coordinate(6, 6)));
        assertFalse(horizontal.isDestroyed(), "The ship is not destroyed yet");

        assertTrue(horizontal.shoot(new Coordinate(7, 6)));
        assertTrue(horizontal.isDestroyed(), "The ship should be destroyed after 3 hits yet");
    }


}
