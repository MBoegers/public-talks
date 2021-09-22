package io.github.mboegers.sealedclass.solutions.Asyntax;

/**
 * Here I show you how interfaces work with sealed classes
 */
public class SealedInterfaceExample {
    sealed interface SealedInterface 
        permits SecondSealedInterface, NonSealedInterface {}
    sealed interface SecondSealedInterface extends SealedInterface
        permits SecondNonSealedInterface {} // A propagates sealed to the next level
    non-sealed interface NonSealedInterface extends SealedInterface {} // B open for extension
    interface ArbitraryInterface extends NonSealedInterface {} // D can freely extend B, can have arbitrary siblings
    non-sealed interface SecondNonSealedInterface extends SecondSealedInterface, NonSealedInterface {} // combining sealed and unsealed is possible
}