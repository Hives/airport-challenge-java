import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class FizzBuzzTest {

    FizzBuzz fizzBuzz = new FizzBuzz();

    @Test
    public void fizzBuzz1Equals1() {
        assertEquals("1", fizzBuzz.play(1));
    }

    @Test
    public void fizzBuzz2Equals2() {
        assertEquals("2", fizzBuzz.play(2));
    }

    @Test
    public void fizzBuzz3EqualsFizz() {
        assertEquals("Fizz", fizzBuzz.play(3));
    }

    @Test
    public void fizzBuzz5EqualsBuzz() {
        assertEquals("Buzz", fizzBuzz.play(5));
    }

    @Test
    public void fizzBuzz6EqualsFizz() {
        assertEquals("Fizz", fizzBuzz.play(6));
    }

    @Test
    public void fizzBuzz10EqualsBuzz() {
        assertEquals("Buzz", fizzBuzz.play(10));
    }

    @Test
    public void fizzBuzz15EqualsFizzBuzz() {
        assertEquals("FizzBuzz", fizzBuzz.play(15));
    }

    @Test
    public void fizzBuzz30EqualsFizzBuzz() {
        assertEquals("FizzBuzz", fizzBuzz.play(30));
    }
}
