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

    public void clearForLanding(Plane plane) throws AirportException {
        if (hanger.size() >= capacity) throw new AirportException("Plane could not land. Airport is full.");
        if (weather.isStormy()) throw new AirportException("Plane could not land. Weather was stormy.");
        if (contains(plane)) throw new AirportException("Plane could not land. Plane already at airport.");
        hanger.add(plane);
    }

    public void clearForTakeOff(Plane plane) throws AirportException {
        if (weather.isStormy()) throw new AirportException("Plane could not take off. Weather was stormy.");
        if (!contains(plane)) throw new AirportException("Plane could not take off. Plane not at airport.");
        hanger.remove(plane);
    }

    public boolean contains(Plane plane) {
        return hanger.contains(plane);
    }

}

