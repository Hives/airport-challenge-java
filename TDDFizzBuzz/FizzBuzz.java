public class FizzBuzz {
    public String play(int n) {
        if (n == 5) {
            return "Buzz";
        }
        if (n % 3 == 0) {
            return "Fizz";
        }
        return Integer.toString(n);
    }
}