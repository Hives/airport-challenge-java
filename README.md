# Java Adventure

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