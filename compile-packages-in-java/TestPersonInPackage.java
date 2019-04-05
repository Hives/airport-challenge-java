import personpackage.Person;
public class TestPersonInPackage {

    public static void main(String[] args) {
        Person person=new Person();

        person.setFirstName("Stephen");
        person.setLastName("Withrow");

        System.out.println("Java bean data: " + person.getFirstName() + " " + person.getLastName());

    }
}
