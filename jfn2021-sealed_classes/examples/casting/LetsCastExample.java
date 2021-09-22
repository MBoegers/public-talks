/**
 * Here I demonstrate the solution to UpCasting, a sometimes handy but hacky situation
 */
public class LetsCastExample {
    sealed interface S permits A, B {
        default void foo() {
            System.out.println("foo called");
        }
    }

    record A() implements S {
        public void faa() {
            System.out.println("faa called");
        }
    }

    record B() implements S {
        public void baa() {
            System.out.println("baa called");
        }
    }

    /**
     * The upcasting is possible via switching of the type
     */
    private void upCaseOK() {
        S s = new B(); // possible since first beta, probably
        // ...
        s = new A();
        // ...
        s.foo();
        switch (s) { // upcasting via switch expression
            case A a -> a.faa();
            case B b -> b.baa();
        }
    }

    /**
     * Upcasting is an evel thing to do because they can AND WILL cause {@linkplain ClassCastException}!
     */
    private void upCastCCE() {
        S s = new B(); // possible since first beta, probably
        // ...
        s = new A();
        // ...
        s.foo();
        ((B) s).baa(); // Class Cast Exception
    }


    public static void main(String[] args) {
        //new LetsCast().upCastCCE();
        new LetsCastExample().upCaseOK();
    }
}
