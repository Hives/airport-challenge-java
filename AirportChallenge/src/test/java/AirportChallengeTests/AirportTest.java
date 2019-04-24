package AirportChallengeTests;

import AirportChallenge.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.*;

class AirportTest {

    private Airport airport;

    @Mock
    Plane planeMock;

    @Mock
    Plane planeMock2;

    @Mock
    Plane planeMock3;

    @Mock
    Weather weatherMock;

    @BeforeEach
    private void init() {
        initMocks(this);
        when(weatherMock.isStormy()).thenReturn(false);
        airport = new Airport(weatherMock);
    }

    @Test
    public void itCanTellPlanesToLand() throws AirportException, PlaneException {
        airport.clearForLanding(planeMock);
        verify(planeMock, times(1)).land(airport);
    }

    @Test
    public void itCantLandALandedPlane() throws AirportException, PlaneException {
        doThrow(PlaneException.class).when(planeMock).land(airport);
        try {
            airport.clearForLanding(planeMock);
        }
        catch (PlaneException exception) {}
        assertFalse(airport.contains(planeMock));
    }

    @Test
    public void itContainsAPlaneAfterLandingIt() throws AirportException, PlaneException {
        airport.clearForLanding(planeMock);
        assertTrue(airport.contains(planeMock));
    }

    @Test
    public void itCanTellAPlaneToTakeOff() throws AirportException, PlaneException {
        airport.clearForLanding(planeMock);
        airport.clearForTakeOff(planeMock);
        verify(planeMock, times(1)).takeOff();
    }

    @Test
    public void itNoLongerContainsAPlaneAfterItTakesOff() throws AirportException, PlaneException {
        airport.clearForLanding(planeMock);
        airport.clearForTakeOff(planeMock);
        assertFalse(airport.contains(planeMock));
    }

    @Test
    public void itTellsTheRightPlaneToTakeOff() throws AirportException, PlaneException {
        airport.clearForLanding(planeMock);
        airport.clearForLanding(planeMock2);
        airport.clearForLanding(planeMock3);

        airport.clearForTakeOff(planeMock2);

        assertTrue(airport.contains(planeMock));
        assertFalse(airport.contains(planeMock2));
        assertTrue(airport.contains(planeMock3));
    }

    @Test
    public void itThrowsErrorIfTakeOffAttemptedInBadWeather() throws AirportException, PlaneException {
        airport.clearForLanding(planeMock);
        when(weatherMock.isStormy()).thenReturn(true);
        Throwable exception = assertThrows(AirportException.class, () -> {
            airport.clearForTakeOff(planeMock);
        });
        assertEquals("Could not clear plane for take off. Weather was stormy.", exception.getMessage());
    }

    @Test
    public void itDoesNotTakeOffAPlaneInBadWeather() throws AirportException, PlaneException {
        airport.clearForLanding(planeMock);
        when(weatherMock.isStormy()).thenReturn(true);
        try {
            airport.clearForTakeOff(planeMock);
        }
        catch (AirportException exception) {}
        assertTrue(airport.contains(planeMock));
    }

    @Test
    public void itThrowsErrorIfLandingAttemptedInBadWeather() {
        when(weatherMock.isStormy()).thenReturn(true);
        Throwable exception = assertThrows(AirportException.class, () -> {
            airport.clearForLanding(planeMock);
        });
        assertEquals("Could not clear plane for landing. Weather was stormy.", exception.getMessage());
    }

    @Test
    public void itDoesNotLandAPlaneInBadWeather() throws PlaneException {
        when(weatherMock.isStormy()).thenReturn(true);
        try {
            airport.clearForLanding(planeMock);
        }
        catch (AirportException exception) {}
        assertFalse(airport.contains(planeMock));
    }


    @Test
    public void itThrowsErrorIfLandingAttemptedWhenFull() throws AirportException, PlaneException {
        int capacity = Airport.DEFAULT_MAX_CAPACITY;
        for (int i = 0; i < capacity; i++) {
            airport.clearForLanding(mock(Plane.class));
        }
        Throwable exception = assertThrows(AirportException.class, () -> {
            airport.clearForLanding(planeMock);
        });
        assertEquals("Could not clear plane for landing. Airport is full.", exception.getMessage());
    }

    @Test
    public void itDoesNotLandAPlaneIfFull() throws AirportException, PlaneException {
        for (int i = 0; i < Airport.DEFAULT_MAX_CAPACITY; i++) {
            airport.clearForLanding(new PlaneDouble());
        }
        try {
            airport.clearForLanding(planeMock);
        }
        catch (AirportException exception) {}
        assertFalse(airport.contains(planeMock));
    }

    @Test
    public void theCapacityCanBeOverridden() throws AirportException, PlaneException {
        Airport airport = new Airport(weatherMock, 30);
        for (int i = 0; i < 30; i++) {
            airport.clearForLanding(mock(Plane.class));
        }
        Throwable exception = assertThrows(AirportException.class, () -> {
            airport.clearForLanding(planeMock);
        });
        assertEquals("Could not clear plane for landing. Airport is full.", exception.getMessage());
    }

    @Test
    public void itCanOnlyTakeOffPlanesWhichAreAtTheAirport() throws PlaneException, AirportException {
        Airport airport2 = new Airport(weatherMock, 30);
        airport.clearForLanding(planeMock);
        Throwable exception = assertThrows(AirportException.class, () -> {
            airport2.clearForTakeOff(planeMock);
        });
        assertEquals("Could not clear plane for take off. Plane is not at this airport.", exception.getMessage());
    }
}

