import java.util.function.Function;
import java.util.stream.Stream;

/**
 * What can we du with switch expression of the has sealed interface
 * For further information about switch pattern see https://openjdk.java.net/jeps/406
 */
public class SealedInterfaceSwitchExample {
    sealed interface SealedInterface permits FinalClass, Enum, Record {}
    final class FinalClass implements SealedInterface {}
    enum Enum implements SealedInterface {INST}
    record Record(int i) implements SealedInterface {}

    void testSealedCoverage() {
        Function<SealedInterface, String> toPrio = s -> switch (s) {
            case FinalClass c -> "It's a FinalClass";
            case Enum e -> "It's an Enum";
            case Record r -> "It's a Record with i=%s".formatted(r.i);
        };
        Stream.of(new FinalClass(), Enum.INST, new Record(3), new Record(4))
                .map(toPrio)
                .forEach(System.out::println);
    }

    public static void main(String[] args) {
        new SealedInterfaceSwitchExample().testSealedCoverage();
    }
}
