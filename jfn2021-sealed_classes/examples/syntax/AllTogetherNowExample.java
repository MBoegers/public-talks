/**
 * Sealed interfaces can be implemented by all kinds of <i>classes</i>.
 * It's very concise if we use the other ADT like records or enums.
 * Otherwise, we need a final, sealed or non-sealed class.
 * In the second layer everything is open again.
 * It is important to notice that every methode, even those defined in sealed classes can be overwritten.
 */
public class AllTogetherNowExample {
    // lvl 0
    sealed interface SealedInterface {} /* permits FC, SC, R, E*/

    //lvl 1
    final class FinalClass implements SealedInterface {} /* end of this path */
    sealed class SealedClass implements SealedInterface {} /* sealed again, permits FC2 */

    record Record() implements SealedInterface {} /* less code because records are final by definition */
    enum Enum implements SealedInterface {} /* same for records, both can not extend a (sealed) class */
    non-sealed class NotSealedClass implements SealedInterface {} /* NoS opens for extension */

    //lvl 2
    final class OtherFinalClass extends SealedClass {} /* an end of the hirachy in second level */

    //lvl 3 + n
    class NS2 extends NotSealedClass {} /* one of the extension for NoS, and I */
}
