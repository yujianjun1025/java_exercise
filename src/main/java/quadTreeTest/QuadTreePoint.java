package quadTreeTest;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * Created with IntelliJ IDEA. User: jianjun.yu Date: 14-11-25 Time: 下午2:17 To change this template use File | Settings
 * | File Templates.
 */

// box 索引 点的情况
public class QuadTreePoint<T> {

    private Node<T> root;

    public static class Node<T> {

        public double x, y;
        public Node<T> NW, NE, SE, SW;
        public T s;

        public Node(double x, double y, T s) {
            this.x = x;
            this.y = y;
            this.s = s;
        }
    }

    public static class Box {

        private double xLow, xHigh, yLow, yHigh;

        public Box(double xLow, double xHigh, double yLow, double yHigh) {
            this.xLow = xLow;
            this.xHigh = xHigh;
            this.yLow = yLow;
            this.yHigh = yHigh;
        }

        public boolean contain(double x, double y) {
            return (x <= xHigh && x >= xLow && y <= yHigh && y >= yLow);
        }

    }

    public Node<T> insert(Node<T> node, double x, double y, T s) {

        if (node == null) {
            return new Node<T>(x, y, s);
        }

        if (x < node.x && y < node.y) {
            node.NW = insert(node.NW, x, y, s);
            return node;
        }

        if (x < node.x && !(y < node.y)) {
            node.SW = insert(node.SW, x, y, s);
            return node;
        }

        if (!(x < node.x) && y < node.y) {
            node.NE = insert(node.NE, x, y, s);
            return node;
        }

        if (!(x < node.x) && !(y < node.y)) {
            node.SE = insert(node.SE, x, y, s);
        }

        return null;

    }

    public List<Node<T>> query(Box box) {

        List<Node<T>> result = Lists.newArrayList();
        query(root, box, result);
        return result;
    }

    private void query(Node<T> node, Box box, List<Node<T>> result) {

        if (node == null) {
            return;
        }

        if (box.contain(node.x, node.y)) {
            result.add(node);
            return;
        }

        if (node.x > box.xHigh) {
            query(node.SW, box, result);
            query(node.NW, box, result);
            return;
        }

        if (node.x < box.xLow) {
            query(node.NE, box, result);
            query(node.NW, box, result);
            return;
        }

        if (node.y > box.yHigh) {
            query(node.SE, box, result);
            query(node.SW, box, result);
            return;
        }

        if (node.y < box.yLow) {
            query(node.NE, box, result);
            query(node.NW, box, result);
            return;
        }

    }
}
