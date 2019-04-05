import static org.junit.Assert.*;
import org.junit.Test;
import java.util.ArrayList;

public class AirportTest {

    Airport airport = new Airport();
    Plane plane1 = new Plane();
    Plane plane2 = new Plane();
    Plane plane3 = new Plane();

    // As an air traffic controller 
    // So I can get passengers to a destination 
    // I want to instruct a plane to land at an airport
    @Test
    public void itCanInstructAPlaneToLand() {
        ArrayList<Plane> hanger = airport.clearForLanding(plane1);
        assertTrue(airport.contains(plane1));
    }

    @Test
    public void itCanInstructThreePlanesToLand() {
        airport.clearForLanding(plane1);
        airport.clearForLanding(plane2);
        airport.clearForLanding(plane3);
        assertTrue(airport.contains(plane1));
        assertTrue(airport.contains(plane2));
        assertTrue(airport.contains(plane3));
    }

}
