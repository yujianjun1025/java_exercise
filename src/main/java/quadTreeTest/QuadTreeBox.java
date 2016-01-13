package quadTreeTest;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * Created with IntelliJ IDEA. User: jianjun.yu Date: 14-11-25 Time: 下午2:17 To change this template use File | Settings
 * | File Templates.
 */

// box 索引 box
public class QuadTreeBox<T> {

    private T s;
    private int depth;
    private Node<T> root;

    public QuadTreeBox(int depth, Box box, T s) {
        this.depth = depth;
        this.s = s;
        root = new Node<T>(box, depth);
        initQuadTree(root);
    }

    private void initQuadTree(Node<T> node) {

        Box box = node.box;
        int depth = node.depth;

        if (node.depth > 0) {

            node.NW = new Node<T>(new Box(box.xLow, box.yHigh, box.xLow + (box.xHigh - box.xLow) / 2, box.yHigh
                    - (box.yHigh - box.yLow) / 2), depth - 1);
            initQuadTree(node.NW);

            node.NE = new Node<T>(new Box(box.xLow + (box.xHigh - box.xLow) / 2, box.yHigh, box.xHigh, box.yHigh
                    - (box.yHigh - box.yLow) / 2), depth - 1);
            initQuadTree(node.NE);

            node.SE = new Node<T>(new Box(box.xLow + (box.xHigh - box.xLow) / 2,
                    box.yHigh - (box.yHigh - box.yLow) / 2, box.xHigh, box.yLow), depth - 1);
            initQuadTree(node.SE);

            node.SW = new Node<T>(new Box(box.xLow, box.yHigh - (box.yHigh - box.yLow) / 2, box.xLow
                    + (box.xHigh - box.xLow) / 2, box.yLow), depth - 1);
            initQuadTree(node.SW);

        }

    }

    public static class Node<T> {

        private T s;
        private int depth;
        private Box box;
        private Node<T> NW, NE, SE, SW;

        private Node(Box box, int depth) {
            this.box = box;
            this.depth = depth;
        }

        @Override
        public String toString() {
            return "Node{" + "depth=" + depth + ", box=" + box + '}';
        }
    }

    public static class Box {

        private double xLow, yHigh, xHigh, yLow;

        public Box(double xLow, double yHigh, double xHigh, double yLow) {
            this.xLow = xLow;
            this.xHigh = xHigh;
            this.yLow = yLow;
            this.yHigh = yHigh;
        }

        public boolean contain(double x, double y) {
            return (x > xLow && x < xHigh && y > yLow && y < yHigh);
        }

        public static BoxRelation contain(Box box1, Box box2) {

            BoxRelation result = containInner(box1, box2);

            if (result != null) {
                return result;
            }

            if (BoxRelation.CONTAIN == containInner(box2, box1)) {
                return BoxRelation.CONTAINED;
            }

            return BoxRelation.NO_RELATION;

        }

        private static BoxRelation containInner(Box box1, Box box2) {

            boolean hadIn = false;
            boolean allIn = true;

            boolean contain;
            contain = box1.contain(box2.xLow, box2.yHigh);
            hadIn |= contain;
            allIn &= contain;

            contain = box1.contain(box2.xHigh, box2.yHigh);
            hadIn |= contain;
            allIn &= contain;

            contain = box1.contain(box2.xLow, box2.yLow);
            hadIn |= contain;
            allIn &= contain;

            contain = box1.contain(box2.xHigh, box2.yLow);
            hadIn |= contain;
            allIn &= contain;

            if (allIn) {
                return BoxRelation.CONTAIN;
            }

            if (hadIn) {
                return BoxRelation.CROSS;
            }

            return null;
        }

        @Override
        public String toString() {
            return "Box{" + "xLow=" + xLow + ", yHigh=" + yHigh + ", xHigh=" + xHigh + ", yLow=" + yLow + '}';
        }
    }

    enum BoxRelation {
        CROSS, CONTAIN, CONTAINED, NO_RELATION, UN_KNOW
    }

    private void queryB(Node<T> node, Box box, List<Node<T>> result) {

        if(node == null){
            return;
        }

        switch (Box.contain(node.box, box)) {
        case CONTAIN:
            if(node.depth != 0){
                queryA(node, box, result);
                break;
            }
            result.add(node);
        case CROSS:
            if(node.depth != 0){
                queryA(node, box, result);
                break;
            }
            result.add(node);
            break;
        case CONTAINED:
            if(node.depth != 0){
                queryA(node, box, result);
                break;
            }
            result.add(node);
            break;
        default:
            break;
        }
    }

    private void queryA(Node<T> node, Box box, List<Node<T>> result) {

        if (node == null) {
            return;
        }

        queryB(node.NW, box, result);
        queryB(node.NE, box, result);
        queryB(node.SE, box, result);
        queryB(node.SW, box, result);

    }

    public List<Node<T>> query(Box box) {

        List<Node<T>> result = Lists.newArrayList();
        queryA(root, box, result);
        return result;
    }

    private void display(Node<T> node, String label) {

        if (node == null) {
            return;
        }

        System.out.println(node.depth + "\t" + label + "\t" + node.box.toString());
        display(node.NW, "NW");
        display(node.NE, "NE");
        display(node.SE, "SE");
        display(node.SW, "SW");

    }

    public void display() {
        display(root, "root");
    }

    public static void main(String[] args) {

        QuadTreeBox<String> treeBox = new QuadTreeBox<String>(5, new Box(-10, 10, 10, -10), "root");
        treeBox.display();

        System.out.println("----------------------------");

        for (Node<String> node : treeBox.query(new Box(-1, 1, 1, 1))) {
            System.out.println(node.depth + "\t" + node.box.toString());
        }
    }

}
