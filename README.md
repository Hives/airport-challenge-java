# Trying to test drive Airport Challenge in Java

*[Airport Challenge](https://github.com/makersacademy/airport_challenge)*

## Process

### 1. Hello world

- This was useful getting started: [Creating, running, and packaging a java app (jetbrains.com)](https://www.jetbrains.com/help/idea/creating-and-running-your-first-java-application.html)

- Packages and classes:
  > Packages are used for grouping together classes that belong to the same category or provide similar functionality, for structuring and organizing large applications with hundreds of classes.

- In IntelliJ I select `src/` inthe project window, command-N, 'Java Class' to create a new class, and set the name as `com.example.helloworld.HelloWorld`. This means a package called `com.example.helloworld` with a class called `HelloWorld`.
  IntelliJ creates the file with some scaffolding from templates.

### 2. FizzBuzz

- [Methods in Java](https://www.programiz.com/java-programming/methods):
    - The `public` keyword makes myMethod() method public. Public members can be accessed from outside of the class. To learn more, visit: Java public and private Modifiers.
    - The `static` keyword denotes that the method can be accessed without creating the object of the class. To learn more, visit: Static Keyword in Java
    - The `void` keyword signifies that the method doesn’t return any value. You will learn about returning value from the method later in this article.

### 3. Get started with junit

- [junit 4 on GitHub](https://github.com/junit-team/junit4/wiki/Getting-started). Steps in 'getting started':
    - install junit and hamcrest (i.e. copy the `.jar` files into your project folder)
    - create the class under test
        1. write the code
        2. compile the code `javac YourClass.java`
    - create a test
        1. write the code for the test
        2. compile the test `javac -cp .:junit-4.XX.jar:hamcrest-core-1.3.jar YourClassTest.java`
    - run the test `java -cp .:junit-4.XX.jar:hamcrest-core-1.3.jar org.junit.runner.JUnitCore YourClassTest`
    - check the output
    - modify your class, recompile the class
    - run the test again (no need to recompile)

### 4. TDD FizzBuzz

- [Configuring testing libraries in IntelliJ (jetbrains.com)](https://www.jetbrains.com/help/idea/configuring-testing-libraries.html) - nah, forget that
- From the tests: `FizzBuzz fizzBuzz = new FizzBuzz();` - why has this line got so many fizzbuzzes in it?

### 5. Airport challenge

- Will want a REPL. [JShell](https://www.infoq.com/articles/jshell-java-repl)
- Java gives you way more error messages than Ruby and Javascript, even before the tests run. e.g. this error telling me the `airport.clearForTakeOff` method doesn't exist comes from the compiler, not the test
  ```
  AirportTest.java:44: error: cannot find symbol
        airport.clearForTakeOff(plane2);
               ^
  symbol:   method clearForTakeOff(Plane)
  location: variable airport of type Airport
  ```
- [`throw` and `throws` in Java](https://www.geeksforgeeks.org/throw-throws-java/)  
    **Should we make our exceptions checked or unchecked?**  
    Following is the bottom line from Java documents:  
    > If a client can reasonably be expected to recover from an exception, make it a checked exception. If a client cannot do anything to recover from the exception, make it an unchecked exception
- [Unchecked Exceptions — The Controversy](https://docs.oracle.com/javase/tutorial/essential/exceptions/runtime.html) 😲
- In jshell:
  ```
  jshell> import airport.*;
  
  jshell> Airport airport = new Airport();
  airport ==> airport.Airport@42e26948
  
  jshell> Plane plane = new Plane();
  plane ==> airport.Plane@53b32d7
  
  jshell> airport.clearForLanding(plane);
  $4 ==> [airport.Plane@53b32d7]
  
  jshell> airport.clearForTakeOff(plane);
  $5 ==> []
  ```
  NICE
- [JUnit FAQ](https://junit.org/junit4/faq.html)  
  **Is there a basic template I can use to create a test?**

    (Submitted by: Eric Armstrong)

    The following templates are a good starting point. Copy/paste and edit these templates to suit your coding style.

    SampleTest is a basic test template:

        import org.junit.*;
        import static org.junit.Assert.*;
         
        public class SampleTest {
         
            private java.util.List emptyList;
         
            /**
             * Sets up the test fixture. 
             * (Called before every test case method.)
             */
            @Before
            public void setUp() {
                emptyList = new java.util.ArrayList();
            }
         
            /**
             * Tears down the test fixture. 
             * (Called after every test case method.)
             */
            @After
            public void tearDown() {
                emptyList = null;
            }
            
            @Test
            public void testSomeBehavior() {
                assertEquals("Empty list should have 0 elements", 0, emptyList.size());
            }
         
            @Test(expected=IndexOutOfBoundsException.class)
            public void testForException() {
                Object o = emptyList.get(0);
            }
        }
- Maven, Graven, Grapple, Gradle...... wtf was that all about
- [This (tdd, jetbrains.com)](https://www.jetbrains.com/help/idea/tdd-with-intellij-idea.html) and [this (configuring testing libraries)](https://www.jetbrains.com/help/idea/configuring-testing-libraries.html) finally sorted me out
