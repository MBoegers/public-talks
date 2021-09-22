import java.util.stream.Stream;

public class SealedPersons {
    public abstract sealed class Person { // permits is optional
        private final String name;
        public Person(String name) {
            this.name = name;
        }
    }

    public enum EmployeeRole {MINION, GRU}

    public final class Employee extends Person {
        private final EmployeeRole role;
        public Employee(String name, EmployeeRole role) {
            super(name);
            this.role = role;
        }
    }

    public enum CustomerType {PRIVATE, B2B}
    
    public final class Customer extends Person {
        private final CustomerType type;
        public Customer(String name, CustomerType role) {
            super(name);
            type = role;
        }
    }

    public void printRelation() {
        Stream.of(new Employee("Merlin", EmployeeRole.MINION),
                        new Employee("Vanessa", EmployeeRole.GRU),
                        new Customer("Fred", CustomerType.PRIVATE),
                        new Customer("Friede", CustomerType.B2B))
                .map(p -> {
                    String relation = p.name + " is a: ";
                    if (p instanceof Employee e) relation += e.role.name();
                    if (p instanceof Customer c) relation += c.type.name();
                    return relation; // must be one of these, it's in the definition!
                })
                .forEach(System.out::println);
    }

    public static void main(String[] args) {
        new SealedPersons().printRelation();
    }
}
