public class FizzBuzz {
    public static String play(int n) {
        if (n % 15 == 0) {
            return "FizzBuzz";
        }
        if (n % 5 == 0) {
            return "Buzz";
        }
        if (n % 3 == 0) {
            return "Fizz";
        }
        return Integer.toString(n);
    }
    
    public static void main(String[] args) {
        for (int i = 1; i < 101; i++) {
            System.out.println(play(i));
        }
    }
}
