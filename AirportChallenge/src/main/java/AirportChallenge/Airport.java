package AirportChallenge;

import java.util.ArrayList;

public class Airport {

    private ArrayList<Plane> hanger = new ArrayList<Plane>();
    private Weather weather;
    private int capacity;

    public static class AirportBuilder {

        private Weather weather;
        private int capacity;
        public static final int MAX_CAPACITY = 25;

        public AirportBuilder() {
            this.weather = new WeatherDouble();
            this.capacity = MAX_CAPACITY;
        }

        public AirportBuilder setWeather(Weather weather) {
            this.weather = weather;
            return this;
        }

        public AirportBuilder setCapacity(int capacity) {
            this.capacity = capacity;
            return this;
        }

        public Airport build() {
            return new Airport(this);
        }
    }

    private Airport(AirportBuilder builder) {
        this.weather = builder.weather;
        this.capacity = builder.capacity;
    }

    public void clearForLanding(Plane plane) throws AirportException, PlaneException {
        if (hanger.size() >= capacity) throw new AirportException("Could not clear plane for landing. Airport is full.");
        if (weather.isStormy()) throw new AirportException("Could not clear plane for landing. Weather was stormy.");
        plane.land(this);
        hanger.add(plane);
    }

    public void clearForTakeOff(Plane plane) throws AirportException, PlaneException {
        if (weather.isStormy()) throw new AirportException("Could not clear plane for take off. Weather was stormy.");
        plane.takeOff();
        hanger.remove(plane);
    }

    public boolean contains(Plane plane) {
        return hanger.contains(plane);
    }

}

