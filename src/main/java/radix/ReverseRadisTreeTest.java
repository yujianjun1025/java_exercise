package radix;

import com.google.common.collect.Iterables;
import com.googlecode.concurrenttrees.common.PrettyPrinter;
import com.googlecode.concurrenttrees.radix.node.concrete.DefaultCharArrayNodeFactory;
import com.googlecode.concurrenttrees.radix.node.util.PrettyPrintable;
import com.googlecode.concurrenttrees.radixreversed.ConcurrentReversedRadixTree;
import com.googlecode.concurrenttrees.radixreversed.ReversedRadixTree;

/**
 * Created with IntelliJ IDEA.
 * User: yujj
 * Date: 5/13/14
 * Time: 2:05 PM
 */
public class ReverseRadisTreeTest {

    public static void main(String[] args) {
        ReversedRadixTree<Integer> tree = new ConcurrentReversedRadixTree<Integer>(new DefaultCharArrayNodeFactory());

        tree.put("TEST", 1);
        tree.put("TOAST", 2);
        tree.put("TEAM", 3);

        System.out.println("Tree structure:");
        // PrettyPrintable is a non-public API for testing, prints semi-graphical representations of trees...
        PrettyPrinter.prettyPrint((PrettyPrintable) tree, System.out);

        System.out.println();
        System.out.println("Value for 'TEST' (exact match): " + tree.getValueForExactKey("TEST"));
        System.out.println("Value for 'TOAST' (exact match): " + tree.getValueForExactKey("TOAST"));
        System.out.println();
        System.out.println("Keys ending with 'ST': " + Iterables.toString(tree.getKeysEndingWith("ST")));
        System.out.println("Keys ending with 'M': " + Iterables.toString(tree.getKeysEndingWith("M")));
        System.out.println();
        System.out.println("Values for keys ending with 'ST': " + Iterables.toString(tree.getValuesForKeysEndingWith("ST")));
        System.out.println("Key-Value pairs for keys ending with 'ST': " + Iterables.toString(tree.getKeyValuePairsForKeysEndingWith("ST")));
    }
}
