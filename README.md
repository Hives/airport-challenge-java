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
  - LOL.

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
  But you can apply the same BDD logic to these compiler errors - get your feature and unit tests to produce the same compiler errors, and then write the code to fix them. Good approach?
- In the exemplar vid he uses a symbol, `:plane` as a double for plane early on, as he doesn't require it to have any particular properties at that point. This is a nice way of doubling a simple object. You can't do this in Java though because it wants to know the type of everything, so I had to create my `Plane` class much earlier, even though it wasn't doing anything.
- Related - in the exemplar he interprets the 'i want to be able to instruct a plane to land' user story as just requiring that `airport.land(plane)` won't throw an error. But in Java, the code won't compile unless there's an `airport.land()` which will accept `plane` as an input, so you can't write the equivalent test. So I chose to test that after doing `airport.land(plane)`, `airport.contains(plane)` was true.
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
- Struggled to get JUnit set up in my project. Do I need to use some kind of dependency manager? Maven, Grapple?
- [This (tdd, jetbrains.com)](https://www.jetbrains.com/help/idea/tdd-with-intellij-idea.html) and [this (configuring testing libraries)](https://www.jetbrains.com/help/idea/configuring-testing-libraries.html) finally sorted me out. IntelliJ comes with JUnit, so you can install it without using Maven/Grapple
- [Constructor overloading](https://www.geeksforgeeks.org/constructor-overloading-java/) - can use to provide a default value for the constructor method. Used for dependency injection in your tests. Basically you have two different constructors, one for if no argument is provided, and another one if you provide an argument, i.e. your mocked dependency.
- Constructor overloading not so good when you want to pass in multiple optional things into your constructor, like a weather mock object and a capacity. Seems to introduce unnecessary repetition. Look into builder pattern? **Update** - I implemented the builder constructor pattern along with user story 6
- For the edge cases I started to need to mock out the behaviour of my classes. Used Mockito for this. Installed it and its dependencies as a package through Maven, which I installed as a dependency in my project using IntelliJ. Maven is also now managing JUnit, instead of using IntelliJ's version. Managing project s in Java is complicated :-/
- How do you check coverage?
- Here's an example of some Java BDD - I wrote a feature test, ran it and got an error, then wrote a unit test which generated the same error: <img src="/images/replicating-error-in-feature-and-unit-tests.png">  
  The interesting thing about this though is that these errors are being thrown by the compiler, not the tests. So in a compiled language, you can (have to?) follow the compiler errors as much as the test failures.
- Just for completeness here's what two actual test failures at feature and unit levels looks like: <img src="/images/replicating-test-failure-in-feature-and-unit-tests.png">
- run JShell with `jshell --class-path AirportChallenge/target/classes`, then:
  ```
  jshell> import AirportChallenge.*;

  jshell> Airport airport = new Airport.AirportBuilder().build;
  airport ==> AirportChallenge.Airport@3434d3d

  jshell> Plane plane = new Plane()
  plane ==> AirportChallenge.Plane@548e7350

  jshell> airport.clearForLanding(plane);

  jshell> airport.clearForTakeOff(plane);
  | Exception AirportChallenge.AirportException: Could not clear plane for take off. Weather was stormy.
  |       at Airport.clearForTakeOff (Airport.java:51)
  |       at (#5:1)

  etc.
  ```
- What does `static` mean?
  - For variables - basically a class variable.
  - For methods - can be accessed before any objects of its class have been instantiated, can only directly call other static methods, can only directly access static data. Sounds a bit like a class method in Ruby then?
- The builder constructor allowed my `Airport` to be initialised with optional arguments capacity and weather, which allowed me to inject the weather dependency without requiring capacity to also be specified. It's not the most elegant user interface though. Mockito allows you to inject dependencies outside of the constructor by identifying them with the `@InjectMocks` annotation. This would probably be better!
