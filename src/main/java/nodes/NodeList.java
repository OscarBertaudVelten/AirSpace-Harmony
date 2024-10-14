package nodes;

import colors.ColorList;
import colors.ColorPlus;
import graphTools.GraphPlus;

import java.util.ArrayList;

/**
 * Represents a list of nodes in a graph.
 */
public class NodeList {

    /** Array of NodePlus objects representing the nodes in the graph. */
    private NodePlus[] nodeList;

    /** Array of integers representing the degrees of nodes in the graph. */
    private int[] degreeList;

    /**
     * Constructs a new NodeList object based on a given graph.
     *
     * @param graph the graph to create the NodeList from
     */
    public NodeList(GraphPlus graph) {
        nodeList = createNodeList(graph);
        degreeList = createDegreeList();
    }

    /**
     * Gets the array of NodePlus objects.
     *
     * @return the array of NodePlus objects
     */
    public NodePlus[] getNodeList() {
        return nodeList;
    }

    /**
     * Retrieves the degree list of nodes.
     *
     * @return the array of node degrees
     */
    public int[] getDegreeList() {
        return degreeList;
    }

    /**
     * Creates an array of NodePlus objects based on a given graph.
     *
     * @param graph the graph to create NodePlus objects from
     * @return the array of NodePlus objects
     */
    private NodePlus[] createNodeList(GraphPlus graph) {
        int size = graph.getNodeCount();
        NodePlus[] nodes = new NodePlus[size];
        for (int i = 0; i < size; i++) {
            nodes[i] = new NodePlus(graph.getNode(i));
        }
        return nodes;
    }

    /**
     * Creates an array representing the degrees of nodes in the graph.
     *
     * @return the array of node degrees
     */
    private int[] createDegreeList() {
        int size = nodeList.length;
        int[] degrees = new int[size];
        for (int i = 0; i < size; i++) {
            degrees[i] = nodeList[i].getDegree();
        }
        return degrees;
    }

    /**
     * Returns the number of nodes in the NodeList.
     *
     * @return the number of nodes
     */
    public int size() {
        return nodeList.length;
    }

    /**
     * Retrieves the NodePlus object at the specified index.
     *
     * @param ind the index of the node to retrieve
     * @return the NodePlus object at the specified index
     */
    public NodePlus get(int ind) {
        return nodeList[ind];
    }

    /**
     * Calculates the number of unique colors used in the coloring of nodes.
     *
     * @param nodeColors an array representing the colors of nodes
     * @return the number of unique colors used
     */
    public int getNbColors(int[] nodeColors) {
        ArrayList<Integer> uniqueColors = new ArrayList<>();
        for (int color : nodeColors) {
            if (!uniqueColors.contains(color))
                uniqueColors.add(color);
        }
        return uniqueColors.size();
    }

    /**
     * Colors the graph using GraphStream with the specified node colors.
     *
     * @param nodeColors an array representing the colors of nodes
     */
    public void colorGraphGraphStream(int[] nodeColors) {
        ColorPlus[] colorsList = new ColorList(getNbColors(nodeColors)).generateRandomColorList();
        for (int i = 0; i < nodeColors.length; i++) {
            this.get(i).setColor(colorsList[nodeColors[i]]);
        }
    }
}
