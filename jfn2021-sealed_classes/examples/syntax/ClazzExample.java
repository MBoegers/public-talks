package io.github.mboegers.sealedclass.solutions.Asyntax;

/**
 * Here I show you how sealed and non-sealed can play together with classes
 */
public class ClazzExample {
    sealed class SealedClass permits OtherSealedClass, NonSealedClass {} // keep in mind: C is not abstract

    sealed class OtherSealedClass extends SealedClass permits Other {} // A again only allows Other
    non-sealed class Other extends OtherSealedClass {} // Other is now open for further extension

    non-sealed class NonSealedClass extends SealedClass {} // B opens for further extension, need to state that
    class Arbitrary extends NonSealedClass { } // OtherOther does not need to state if sealed or not
    final class EndOfTheRoad extends NonSealedClass {} // this is the end, but it can have multiple siblings
}
