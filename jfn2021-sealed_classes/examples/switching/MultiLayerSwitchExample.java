/**
 * Creddits: Twitter @tagir_valeev
 */
public class MultiLayerSwitchExample {

    sealed interface AorBorC {}
    
    sealed interface AorB extends AorBorC {}
    sealed interface AorC extends AorBorC {}
    sealed interface BorC extends AorBorC {}

    final class A implements AorB, AorC {}
    final class B implements AorB, BorC {}
    final class C implements AorC, BorC {}

    private void multiLayerSwitch() {
        AorBorC abc = new A();
        switch (abc) {
            case A a -> System.out.println("It's an A");
            case B b -> System.out.println("It's an B");
            case C c -> System.out.println("It's an C");
    }

    public static void main(String[] args) {
        new MultiLayerSwitchExample().multiLayerSwitch();
    }
}
