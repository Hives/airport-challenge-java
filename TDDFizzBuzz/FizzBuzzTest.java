import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class FizzBuzzTest {

    @Test
    public void fizzBuzz1Equals1() {
        FizzBuzz fizzBuzz = new FizzBuzz();
        String output = fizzBuzz.play(1);
        assertEquals("1", output);
    }
}
