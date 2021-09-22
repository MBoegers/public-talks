import java.util.function.Function;
import java.util.stream.Stream;

/**
 * How does sealed interfaces and a non-sealed class work together with a switch expression
 */
public class SwitchNonSealedExample {
    sealed interface SealedInterface permits FinalClass, NonSealedClass {}
    final class FinalClass implements SealedInterface {}
    non-sealed class NonSealedClass implements SealedInterface {}
    class ArbitraryClass extends NonSealedClass {}

    private void testSealedCoverage() {
        Function<SealedInterface, String> testInstance = i -> switch (i) {
            case FinalClass a -> "It's an FinalClass";
            case NonSealedClass b -> "It's an NonSealedClass"; // remember arbitrary classes are instances of NonSealedClasses ;)
        };
        Stream.of(new FinalClass(), new NonSealedClass(), new ArbitraryClass())
                .map(testInstance)
                .forEach(System.out::println);
    }

    public static void main(String[] args) {
        new SwitchNonSealedExample().testSealedCoverage();
    }
}
