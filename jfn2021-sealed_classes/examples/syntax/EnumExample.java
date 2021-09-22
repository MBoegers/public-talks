package io.github.mboegers.sealedclass.solutions.Asyntax;

/**
 * Here I show you how enums and sealed interfaces work together
 */
public class EnumExample {
    sealed interface SealedInterface permits FinalEnum, SealedEnum {} // enums can not extend a class, so only interfaces are possible

    enum FinalEnum implements SealedInterface {Inst1, Inst2} // enums are not extendable by definition
    enum SealedEnum implements SealedInterface { // defines a final class that implements a sealed enum class (jls-8.9.1)
        Inst1, Inst2;
        public void printHash() {
            System.out.println(this.hashCode());
        }
    }
}
