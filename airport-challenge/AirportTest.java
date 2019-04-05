import static org.junit.Assert.assertEquals;
import org.junit.Test;
import java.util.ArrayList;

public class AirportTest {

    Airport airport = new Airport();
    Plane plane = new Plane();

    @Test
    public void itCanLandAPlane() {
        ArrayList<Plane> hanger = airport.clearForLanding(plane);
        ArrayList<Plane> testHanger = new ArrayList<Plane>();
        testHanger.add(plane);
        assertEquals(testHanger, hanger);
    }
}
