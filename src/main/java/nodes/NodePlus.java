package nodes;

import colors.ColorPlus;
import org.graphstream.graph.Edge;
import org.graphstream.graph.Node;

import java.util.ArrayList;

/**
 * This class extends the functionality of the GraphStream Node class by providing additional methods.
 */
public class NodePlus {

    /**
     * The underlying GraphStream Node object.
     */
    private final Node node;

    /**
     * Constructs a new NodePlus object with the specified GraphStream Node.
     *
     * @param newNode The GraphStream Node to wrap.
     */
    public NodePlus(Node newNode) {
        node = newNode;
    }

    /**
     * Retrieves an array of NodePlus objects representing adjacent nodes.
     *
     * @return An array of NodePlus objects representing adjacent nodes.
     */
    public NodePlus[] getAdjNodeList() {
        NodePlus[] adjList = new NodePlus[node.getDegree()];
        for (int i = 0; i < node.getDegree(); i++) {
            Edge edge = node.getEdge(i);
            adjList[i] = new NodePlus(edge.getOpposite(node));
        }
        return adjList;
    }

    /**
     * Retrieves a list of indices of adjacent nodes.
     *
     * @return A list of indices of adjacent nodes.
     */
    public ArrayList<Integer> getAdjIndList() {
        ArrayList<Integer> adjIndList = new ArrayList<>();
        for (int i = 0; i < node.getDegree(); i++) {
            Edge edge = node.getEdge(i);
            adjIndList.add(edge.getOpposite(node).getIndex());
        }
        return adjIndList;
    }

    /**
     * Gets the degree of the node.
     *
     * @return The degree of the node.
     */
    public int getDegree() {
        return node.getDegree();
    }

    /**
     * Gets the identifier of the node.
     *
     * @return The identifier of the node.
     */
    public String getId() {
        return node.getId();
    }

    /**
     * Gets the index of the node.
     *
     * @return The index of the node.
     */
    public int getIndex() {
        return node.getIndex();
    }

    /**
     * Sets an attribute of the node.
     *
     * @param attributeName The name of the attribute.
     * @param attributeValues The value(s) of the attribute.
     */
    public void setAttribute(String attributeName, Object... attributeValues) {
        node.setAttribute(attributeName, attributeValues);
    }

    /**
     * Sets the color of the node.
     *
     * @param color The color to set.
     */
    public void setColor(ColorPlus color) {
        String rgbColor = "rgb(" + color.getRed() + "," + color.getGreen() + "," + color.getBlue() + ")";
        setAttribute("ui.style", "fill-color: " + rgbColor + ";");
    }

    /**
     * Checks if there is an edge between this node and another node.
     *
     * @param otherNode The other node to check for an edge.
     * @return True if there is an edge between this node and otherNode, false otherwise.
     */
    public boolean hasEdge(NodePlus otherNode) {
        return this.node.hasEdgeBetween(otherNode.node);
    }

    /**
     * Returns a string representation of the node.
     *
     * @return A string representation of the node.
     */
    @Override
    public String toString() {
        return node.getId();
    }
}
