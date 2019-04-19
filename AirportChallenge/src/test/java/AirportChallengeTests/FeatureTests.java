package AirportChallengeTests;

import AirportChallenge.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class FeatureTests {
    Plane plane1 = new Plane();
    WeatherDouble weatherDouble = new WeatherDouble();
    Airport airport = new Airport.AirportBuilder()
                                 .setWeather(weatherDouble)
                                 .build();

    // As an air traffic controller
    // So I can get passengers to a destination
    // I want to instruct a plane to land at an airport
    @Test
    public void anAirportCanInstructAPlaneToLand() throws AirportException, PlaneException {
        airport.clearForLanding(plane1);
        assertTrue(airport.contains(plane1));
    }

    // As an air traffic controller
    // So I can get passengers on the way to their destination
    // I want to instruct a plane to take off from an airport and confirm that it is no longer in the airport
    @Test
    public void anAirportCanInstructAPlaneToTakeOff() throws AirportException, PlaneException {
        airport.clearForLanding(plane1);
        airport.clearForTakeOff(plane1);
        assertFalse(airport.contains(plane1));
    }

    // As an air traffic controller
    // To ensure safety
    // I want to prevent takeoff when weather is stormy
    @Test
    public void takeOffIsPreventedInBadWeather() throws AirportException, PlaneException {
       airport.clearForLanding(plane1);
       weatherDouble.stormy = true;
       Throwable exception = assertThrows(AirportException.class, () -> {
           airport.clearForTakeOff(plane1);
       });
       assertEquals("Could not clear plane for take off. Weather was stormy.", exception.getMessage());
    }

    // As an air traffic controller
    // To ensure safety
    // I want to prevent landing when weather is stormy
    @Test
    public void landingIsPreventedInBadWeather() {
        weatherDouble.stormy = true;
        Throwable exception = assertThrows(AirportException.class, () -> {
            airport.clearForLanding(plane1);
        });
        assertEquals("Could not clear plane for landing. Weather was stormy.", exception.getMessage());
    }

    // As an air traffic controller
    // To ensure safety
    // I want to prevent landing when the airport is full
    @Test
    public void landingIsPreventedIfAirportIsFull() throws AirportException, PlaneException {
        int capacity = Airport.AirportBuilder.MAX_CAPACITY;
        for (int i = 0; i < capacity; i++) {
            airport.clearForLanding(new Plane());
        }
        Throwable executable = assertThrows(AirportException.class, () -> {
            airport.clearForLanding(plane1);
        });
        assertEquals("Could not clear plane for landing. Airport is full.", executable.getMessage());
    }

    // As the system designer
    // So that the software can be used for many different airports
    // I would like a default airport capacity that can be overridden as appropriate
    @Test
    public void defaultCapacityCanBeOverridden() throws AirportException, PlaneException {
        Airport airport = new Airport.AirportBuilder()
                             .setCapacity(30)
                             .setWeather(weatherDouble)
                             .build();

        for (int i = 0; i < 30; i++) {
            airport.clearForLanding(new Plane());
        }
        Throwable exception = assertThrows(AirportException.class, () -> {
            airport.clearForLanding(plane1);
        });
        assertEquals("Could not clear plane for landing. Airport is full.", exception.getMessage());
    }

    // Edge cases:
    // Planes that are flying cannot take off
    @Test
    public void planesThatAreFlyingCannotTakeOff() throws AirportException, PlaneException {
        airport.clearForLanding(plane1);
        airport.clearForTakeOff(plane1);
        Throwable exception = assertThrows(PlaneException.class, () -> {
            plane1.takeOff();
        });
        assertEquals("Plane could not take off. Plane is already flying.", exception.getMessage());
    }

    // Planes that are landed cannot land again
    @Test
    public void planesThatAreNotFlyingCannotLand() throws AirportException, PlaneException {
        airport.clearForLanding(plane1);
        Throwable exception = assertThrows(PlaneException.class, () -> {
            plane1.land(airport);
        });
        assertEquals("Plane could not land. Plane is not flying.", exception.getMessage());
    }

    // Planes that are landed must be in an airport
    @Test
    public void planesThatAreLandedMustBeInAnAirport() throws AirportException, PlaneException {
        airport.clearForLanding(plane1);
        assertEquals(plane1.airport(), airport);
    }

    // Planes that are flying cannot be in an airport
    @Test
    public void planesThatAreFlyingCannotBeInAnAirport() {
        Throwable exception = assertThrows(PlaneException.class, () -> {
            plane1.airport();
        });
        assertEquals("Plane cannot be at an airport. Plane is flying.", exception.getMessage());
    }

    // Planes can only take off from airports they are in
    @Test
    public void planesCanOnlyTakeOffFromAirportsTheyAreIn () throws AirportException, PlaneException {
        Airport airport2 = new Airport.AirportBuilder()
                .setWeather(weatherDouble)
                .build();
        airport.clearForLanding(plane1);
        Throwable exception = assertThrows(AirportException.class, () -> {
            airport2.clearForTakeOff(plane1);
        });
        assertEquals("Could not clear plane for take off. Plane is not at this airport.", exception.getMessage());
    }
}
