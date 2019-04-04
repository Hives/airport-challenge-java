import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class FizzBuzzTest {

    FizzBuzz fizzBuzz = new FizzBuzz();

    @Test
    public void fizzBuzz1Equals1() {
        assertEquals(fizzBuzz.play(1), "1");
    }

    @Test
    public void fizzBuzz2Equals2() {
        assertEquals(fizzBuzz.play(2), "2");
    }

    @Test
    public void fizzBuzz3EqualsFizz() {
        assertEquals(fizzBuzz.play(3), "Fizz");
    }
}
