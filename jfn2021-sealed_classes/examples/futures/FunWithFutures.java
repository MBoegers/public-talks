/**
 * Here we see a proposal how Futures could be less ceremonial.
 * With this we can make it explicit that we are okay with a timeout or.
 * Like: "Yes it can time out, then it uses this fallback." -> No exception needed.
 * Credits to Brain Goetz @ InforQ https://www.infoq.com/articles/java-sealed-classes/
 */
public class FunWithFutures {

    /**
     * This should be new in the Future API
     * An AsyncResult can be:
     * a Success containing the result
     * a Failure containing the raised error
     * a Timeout, which may be an error
     * or signal an interruption
     */
    sealed interface AsyncReturn<V> {
        record Success<V>(V result) implements AsyncReturn<V> {}
        record Failure<V>(Throwable cause) implements AsyncReturn<V> {}
        record Timeout<V>() implements AsyncReturn<V> {}
        record Interrupted<V>() implements AsyncReturn<V> {}
    }

    interface Future<V> {
        AsyncReturn<V> get();
    }

    /**
     * working with this Kind of AsyncResult can reduce the code and is more readable than the current, exception driven approach
     */
    void funWithFutures() {
        Future<String> result = null; // insert a service call here
        switch (result.get()) {
            case AsyncReturn.Success s -> System.out.println(s.result);
            case AsyncReturn.Failure f -> f.cause.printStackTrace();
            case AsyncReturn.Timeout t -> System.err.println("Timeout exceeded");
            case AsyncReturn.Interrupted i -> System.out.println("Request interrupted");
        }
    }
}
