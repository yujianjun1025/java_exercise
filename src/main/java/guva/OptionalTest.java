package guva;

import com.google.common.base.Optional;

/**
 * Created with IntelliJ IDEA.
 * User: yujj
 * Date: 5/13/14
 * Time: 7:51 PM
 */
public class OptionalTest {
    public static void main(String[] args) {

        Optional<Integer> possible = Optional.of(5);
        possible.isPresent(); // returns true
        possible.get(); // returns 5
        System.out.println( possible.get());
    }
}
