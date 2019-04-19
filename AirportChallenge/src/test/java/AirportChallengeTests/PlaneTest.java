package AirportChallengeTests;

import AirportChallenge.Airport;
import AirportChallenge.Plane;
import AirportChallenge.PlaneException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.MockitoAnnotations.initMocks;

public class PlaneTest {
    Plane plane = new Plane();

    @Mock
    Airport airportMock;

    @BeforeEach
    void init() {
        initMocks(this);
    }


    @Test void aNewPlaneIsFlying() {
        assertTrue(plane.isFlying());
    }

    @Test
    public void itCanLandIfFlying() throws PlaneException {
        plane.land(airportMock);
        assertFalse(plane.isFlying());
    }

    @Test
    public void itCanTakeOffIfNotFlying() throws PlaneException {
        plane.land(airportMock);
        plane.takeOff();
        assertTrue(plane.isFlying());
    }

    @Test
    public void itRaisesErrorIfFlyingAndToldToTakeOff() {
        Throwable exception = assertThrows(PlaneException.class, () -> {
            plane.takeOff();
        });
        assertEquals("Plane could not take off. Plane is already flying.", exception.getMessage());
    }

    @Test
    public void itRaisesErrorIfNotFlyingAndToldToLand() throws PlaneException {
        plane.land(airportMock);
        Throwable exception = assertThrows(PlaneException.class, () -> {
            plane.land(airportMock);
        });
        assertEquals("Plane could not land. Plane is not flying.", exception.getMessage());
    }

    @Test
    public void itIsAtAnAirportAfterLanding() throws PlaneException {
        plane.land(airportMock);
        assertEquals(plane.airport(), airportMock);
    }

    @Test public void itCantBeAtAnAirportIfItsFlying() {
        Throwable exception = assertThrows(PlaneException.class, () -> {
            plane.airport();
        });
        assertEquals("Plane cannot be at an airport. Plane is flying.", exception.getMessage());
    }
}
