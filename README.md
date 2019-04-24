# Airport Challenge in Java

In this project I test-drove a solution to the airport challenge in Java, a language I had never used before. The Airport Challenge involves a small number of classes interacting, so test-driving it succesfully requires techniques like the use of mocks and dependency injection, and stubbing out random behaviour.

## Installation

1. ??
2. Compile it
3. Import it into JShell
4. Then:
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

## Technology

- Java
- JUnit5
- Mockito
- Maven
- JShell
- Intellij IDEA

## Process

*[All the notes I made along the way](notes.md)*

My target was to complete a full solution to the Airport Challenge, implementing all the users stories and edge cases.

This was a sequel to my [Airport Challenge in Javascript](https://github.com/Hives/airport-challenge-javascript), where I did the same exercise in JavaScript, a language in which I did have some experience.

This was much harder! I found many resources for beginners in Java, and many resources for beginners in JUnit, the most popular Java test framework, but the JUnit resources assumed more knowledge of Java than I had. There was not much help for people who were beginners in both.

My biggest problem at first was working out how to structure a Java project - what folders you need to make modules and packages which can easily be compiled. A lot of resources were telling me to use an IDE to set up my project, but I was resistent to this - I wanted to do it all manually, to better learn how it worked. I managed to do some simple exercises like this, [FizzBuzz](https://github.com/Hives/java-stuffs/tree/master/FizzBuzz) and then [TDDing FizzBuzz](https://github.com/Hives/java-stuffs/tree/master/TDDFizzBuzz), both completed using Vim and compiling and running from the command line. This taught me the basics about Java's static typing, and compiling and running source code and JUnit tests.

But when I started on the Airport Challenge, a more complicated program with multiple source and test files, I ran into trouble trying to manage everything manually. I then began to oscillate between trying to set up my source and test code in IntelliJ IDEA, and doing it manually in the command line. After getting stuck with one method I would suspect that the other method might be easier, and switch to that. After a morning's work I didn't start really making progress until I took a break and came back to it two weeks later.

In this period when I wasn't making much progress I wasn't ever totally stuck - I was learning, but slowly! This would have been a good time to ask for help, but I wanted to have the experience of learning something on my own.

In the end I did settle on working in IntelliJ, and using that to structure my project. Once I got going with that progress was at a steady pace. Other hurdles along the way were:
- Working out how to import my module into JShell so I could manually test it
- Working out how to use Maven in IntelliJ to manage dependencies, to pull in Mockito, a mocking framework for Java.
- Working out how to test for exceptions in Java I found confusing, until I figured out the meaning of the error messages I was getting

As with the JavaScript airport challenge I found that TDD concepts like the red/green/refactor cycle, using dependency injection to make tests independent, and mocking were easily translatable into Java and could be implemented without too much trouble.

Java gives you way more error messages than Ruby and Javascript, even before the tests run. e.g. this error telling me the `airport.clearForTakeOff` method doesn't exist comes from the compiler, not the test
```
AirportTest.java:44: error: cannot find symbol
    airport.clearForTakeOff(plane2);
            ^
symbol:   method clearForTakeOff(Plane)
location: variable airport of type Airport
```
But you can apply the same BDD logic to these compiler errors - get your feature and unit tests to produce the same compiler errors, and then write the code to fix them.

I used a builder constructor for my Airport class as it allowed me to have multiple optional arguments, which I needed in order to make the capacity and Weather dependency optional. It means you have to run this command to set up a default Airport though, which is not pretty:
```java
Airport airport = new Airport.AirportBuilder().build;
```
It would be better to be able to do this to get a default airport:
```java
Airport airport = new Airport()
```
I wonder if there's a way to combine constructor overloading with the builder pattern to achieve this?

## Chat with Katerina

- Make sure I bring out the general principles I used to do this which could be re-used in other situations:
  - Do a simple task - FizzBuzz
  - Research test frameworks
  - Do a simple exercise with JUnit
  - TDD FizzBuzz with JUnit
  This will be good for 'i can TDD anything' and 'i can learn anything'
- Builder constructor is a code smell - quite a big extra load of code just so I can test my dependency injection. You should try and avoid writing code just for your tests. Katerina and Michael thought parameters can have default values, so I should look into that. Would be a good thing to point to in terms of refactoring/writing clean code or whatever...
- Katerina said she could give me more feedback if I highlighted specific things in relation to my goals
