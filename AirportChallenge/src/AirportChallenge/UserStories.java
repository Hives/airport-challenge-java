package AirportChallenge;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class UserStories {
    Plane plane1 = new Plane();
    WeatherDouble weatherDouble = new WeatherDouble();
    Airport airport = new Airport.AirportBuilder()
                                 .setWeather(weatherDouble)
                                 .build();

    // As an air traffic controller
    // So I can get passengers to a destination
    // I want to instruct a plane to land at an airport
    @Test
    public void anAirportCanInstructAPlaneToLand() throws AirportException {
        airport.clearForLanding(plane1);
        assertTrue(airport.contains(plane1));
    }

    // As an air traffic controller
    // So I can get passengers on the way to their destination
    // I want to instruct a plane to take off from an airport and confirm that it is no longer in the airport
    @Test
    public void anAirportCanInstructAPlaneToTakeOff() throws AirportException {
        airport.clearForLanding(plane1);
        airport.clearForTakeOff(plane1);
        assertFalse(airport.contains(plane1));
    }

    // As an air traffic controller
    // To ensure safety
    // I want to prevent takeoff when weather is stormy
    @Test
    public void takeOffIsPreventedInBadWeather() throws AirportException {
       airport.clearForLanding(plane1);
       weatherDouble.stormy = true;
       assertThrows(AirportException.class, () -> {
           airport.clearForTakeOff(plane1);
       });
    }

    // As an air traffic controller
    // To ensure safety
    // I want to prevent landing when weather is stormy
    @Test
    public void landingIsPreventedInBadWeather() {
        weatherDouble.stormy = true;
        assertThrows(AirportException.class, () -> {
            airport.clearForLanding(plane1);
        });
    }

    // As an air traffic controller
    // To ensure safety
    // I want to prevent landing when the airport is full
    @Test
    public void landingIsPreventedIfAirportIsFull() throws AirportException {
        int capacity = Airport.AirportBuilder.MAX_CAPACITY;
        for (int i = 0; i < capacity; i++) {
            airport.clearForLanding(new Plane());
        }
        assertThrows(AirportException.class, () -> {
            airport.clearForLanding(plane1);
        });
    }

    // As the system designer
    // So that the software can be used for many different airports
    // I would like a default airport capacity that can be overridden as appropriate
    @Test public void defaultCapacityCanBeOverridden() throws AirportException {
        Airport airport = new Airport.AirportBuilder()
                             .setCapacity(30)
                             .setWeather(weatherDouble)
                             .build();

        for (int i = 0; i < 30; i++) {
            airport.clearForLanding(new Plane());
        }
        assertThrows(AirportException.class, () -> {
            airport.clearForLanding(plane1);
        });
    }
}
