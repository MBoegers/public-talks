package io.github.mboegers.sealedclass.solutions.Asyntax;

/**
 * Here I show you how records work together with sealed interfaces
 */
public class RecordsExample {
    sealed interface SealedInterface permits FirstRecord, OtherRecord {} // a record can not extend a class, therefore only interfaces are possible

    record FirstRecord() implements SealedInterface {} // no propagation of sealed is needed because they are final by definition

    record OtherRecord() implements SealedInterface { // implementing methods does not break anything, surprise ;-)
        void printHash() {
            System.out.println(this.hashCode());
        }
    }
}
