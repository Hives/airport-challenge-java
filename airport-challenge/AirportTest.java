import static org.junit.Assert.*;
import org.junit.Test;
import java.util.ArrayList;

public class AirportTest {

    Airport airport = new Airport();
    Plane plane1 = new Plane();
    Plane plane2 = new Plane();
    Plane plane3 = new Plane();

    @Test
    public void itCanLandAPlane() {
        ArrayList<Plane> hanger = airport.clearForLanding(plane1);
        assertTrue(airport.contains(plane1));
    }

}
