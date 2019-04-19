package AirportChallengeTests;

import AirportChallenge.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.*;

class AirportTest {

    Plane planeDouble1 = new Plane();
    Plane planeDouble2 = new Plane();
    Plane planeDouble3 = new Plane();
    WeatherDouble weatherDouble = new WeatherDouble();
    Airport airport = new Airport.AirportBuilder()
            .setWeather(weatherDouble)
            .build();

    @Mock
    Plane planeMock;

    @BeforeEach
    void init() {
        initMocks(this);
    }

    @Test
    public void itCanTellPlanesToLand() throws AirportException, PlaneException {
        airport.clearForLanding(planeMock);
        verify(planeMock, times(1)).land();
    }

    @Test
    public void itCantLandALandedPlane() throws AirportException, PlaneException {
        doThrow(PlaneException.class).when(planeMock).land();
        try {
            airport.clearForLanding(planeMock);
        }
        catch (PlaneException exception) {}
        assertFalse(airport.contains(planeMock));
    }

    @Test
    public void itContainsAPlaneAfterLandingIt() throws AirportException, PlaneException {
        airport.clearForLanding(planeDouble1);
        assertTrue(airport.contains(planeDouble1));
    }

    @Test
    public void itCanTellAPlaneToTakeOff() throws AirportException, PlaneException {
        airport.clearForTakeOff(planeMock);
        verify(planeMock, times(1)).takeOff();
    }

    @Test
    public void itNoLongerContainsAPlaneAfterItTakesOff() throws AirportException, PlaneException {
        airport.clearForLanding(planeDouble1);
        airport.clearForTakeOff(planeDouble1);
        assertFalse(airport.contains(planeDouble1));
    }

    @Test
    public void itTellsTheRightPlaneToTakeOff() throws AirportException, PlaneException {
        airport.clearForLanding(planeDouble1);
        airport.clearForLanding(planeDouble2);
        airport.clearForLanding(planeDouble3);

        airport.clearForTakeOff(planeDouble2);

        assertTrue(airport.contains(planeDouble1));
        assertFalse(airport.contains(planeDouble2));
        assertTrue(airport.contains(planeDouble3));
    }

    @Test
    public void itThrowsErrorIfTakeOffAttemptedInBadWeather() throws AirportException, PlaneException {
        airport.clearForLanding(planeDouble1);
        weatherDouble.stormy = true;
        Throwable exception = assertThrows(AirportException.class, () -> {
            airport.clearForTakeOff(planeDouble1);
        });
        assertEquals("Could not clear plane for take off. Weather was stormy.", exception.getMessage());
    }

    @Test
    public void itDoesNotTakeOffAPlaneInBadWeather() throws AirportException, PlaneException {
        airport.clearForLanding(planeDouble1);
        weatherDouble.stormy = true;
        try {
            airport.clearForTakeOff(planeDouble1);
        }
        catch (AirportException exception) {}
        assertTrue(airport.contains(planeDouble1));
    }

    @Test
    public void itThrowsErrorIfLandingAttemptedInBadWeather() {
        weatherDouble.stormy = true;
        Throwable exception = assertThrows(AirportException.class, () -> {
            airport.clearForLanding(planeDouble1);
        });
        assertEquals("Could not clear plane for landing. Weather was stormy.", exception.getMessage());
    }

    @Test
    public void itDoesNotLandAPlaneInBadWeather() throws PlaneException {
        weatherDouble.stormy = true;
        try {
            airport.clearForLanding(planeDouble1);
        }
        catch (AirportException exception) {}
        assertFalse(airport.contains(planeDouble1));
    }


    @Test
    public void itThrowsErrorIfLandingAttemptedWhenFull() throws AirportException, PlaneException {
        int capacity = Airport.AirportBuilder.MAX_CAPACITY;
        for (int i = 0; i < capacity; i++) {
            airport.clearForLanding(new PlaneDouble());
        }
        Throwable exception = assertThrows(AirportException.class, () -> {
            airport.clearForLanding(new PlaneDouble());
        });
        assertEquals("Could not clear plane for landing. Airport is full.", exception.getMessage());
    }

    @Test
    public void itDoesNotLandAPlaneIfFull() throws AirportException, PlaneException {
        for (int i = 0; i < Airport.AirportBuilder.MAX_CAPACITY; i++) {
            airport.clearForLanding(new PlaneDouble());
        }
        try {
            airport.clearForLanding(planeDouble1);
        }
        catch (AirportException exception) {}
        assertFalse(airport.contains(planeDouble1));
    }

    @Test void theCapacityCanBeOverridden() throws AirportException, PlaneException {
        Airport airport = new Airport.AirportBuilder()
                .setCapacity(30)
                .setWeather(weatherDouble)
                .build();

        for (int i = 0; i < 30; i++) {
            airport.clearForLanding(new PlaneDouble());
        }
        Throwable exception = assertThrows(AirportException.class, () -> {
            airport.clearForLanding(planeDouble1);
        });
        assertEquals("Could not clear plane for landing. Airport is full.", exception.getMessage());
    }
}

