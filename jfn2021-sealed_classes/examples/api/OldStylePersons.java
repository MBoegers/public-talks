import java.util.stream.Stream;

public class OldStylePersons {

    public abstract class Person {
        private final String name;
        Person(String aName) { name = aName; }
        public String name() { return name; }
    }

    public enum EmployeeRole {MINION, GRU}

    public final class Employee extends Person {
        private final EmployeeRole role;
        public Employee(String name, EmployeeRole role) {
            super(name);
            this.role = role;
        }
        public EmployeeRole role() { return role; }
    }
    
    public enum CustomerType {PRIVATE, B2B}

    public final class Customer extends Person {
        private final CustomerType type;
        public Customer(String name, CustomerType type) {
            super(name);
            this.type = type;
        }
        public CustomerType type() { return type; }
    }

    public void printRelation() {
        Stream.of(new Employee("Merlin", EmployeeRole.MINION),
                    new Employee("Vanessa", EmployeeRole.GRU),
                    new Customer("Fred", CustomerType.PRIVATE),
                    new Customer("Friede", CustomerType.B2B)
                )
                .map(p -> {
                    String relation = "unknown";
                    if (p instanceof Employee e) relation = e.role().name();
                    if (p instanceof Customer c) relation = c.type().name();
                    return p.name() + "is a:" + relation;
                })
                .forEach(System.out::println);
    }

    public static void main(String[] args) {
        new OldStylePersons().printRelation();
    }
}
