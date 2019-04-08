package AirportChallenge;

public class Weather {
    public boolean isStormy() {
        double p = Math.random();
        return p < 0.25;
    }
}
