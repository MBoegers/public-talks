import java.util.function.Function;
import java.util.stream.Stream;

/**
 * What happens wiht the switch if we extend a sealed class
 * records and enum can not extend a class
 * default catches S, other way would be <code>case S s1 -> -1</code> or mark S as <code>abstract</code>
 * For further information about switch pattern see https://openjdk.java.net/jeps/406
 */
public class SealedClassSwitchPitfall {
    sealed class SealedClass permits FinalClass, OtherFinalClass {}
    final class FinalClass extends SealedClass {}
    final class OtherFinalClass extends SealedClass {}

    void testSealedCoverage() {
        Function<SealedClass, String> toPrio = s -> switch (s) {
            case FinalClass a -> "It's a FinalClass";
            case OtherFinalClass b -> "It's a OtherFinalClass";
            default -> "It's a SealedClass"; // because SealedClass is not abstract we can have instances ;-)
        };
        Stream.of(new FinalClass(), new OtherFinalClass())
                .map(toPrio)
                .forEach(System.out::println);
    }

    public static void main(String[] args) {
        new SealedClassSwitchPitfall().testSealedCoverage();
    }
}
