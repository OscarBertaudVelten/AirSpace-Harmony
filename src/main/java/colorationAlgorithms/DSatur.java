package colorationAlgorithms;

import graphTools.GraphPlus;
import nodes.NodeList;
import nodes.NodePlus;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

/**
 * Implements the DSatur graph coloring algorithm.
 */
public class DSatur {
    /** List of nodes in the graph */
    private final NodeList nodeList;

    /** Saturation degree of each node */
    private int[] saturationDegree;

    /** Color assigned to each node */
    @SuppressWarnings("CanBeFinal")
    public int[] nodeColors;

    private int kMax;

    private ArrayList<int[]> indConflictsList;

    boolean rand;

    GraphPlus graph;

    /**
     * Constructs a DSaturKMax object with the given graph.
     * Initializes the node list, saturation degree array, and node colors array.
     * @param newGraph The graph to be colored.
     */
    public DSatur(GraphPlus newGraph, boolean rand) {
        graph = newGraph;
        nodeList = new NodeList(graph);
        kMax = graph.getKMax();
        createSaturationDegreeArray();



        nodeColors = new int[nodeList.size()];
        Arrays.fill(nodeColors, -1); // Initialize colors to -1 (unassigned)
    }

    /**
     * Retrieves the number of colors used in the graph.
     * @return The number of colors used.
     */
    @SuppressWarnings("unused")
    public int getNbColors() {
        int nbColors = 0;
        if (nodeList.size() > 0) {
            for (int color : nodeColors) {
                if (nbColors < color)
                    nbColors = color;
            }
            nbColors++;
        }
        return nbColors;
    }

    public GraphPlus getGraph() { return graph; }

    /**
     * Retrieves the number of conflicts in the graph.
     * @return The number of conflicts.
     */
    public int getNbConflicts() {
        return indConflictsList.size();
    }

    /**
     * Retrieves the list of conflicts in the graph.
     * @return The list of conflicts.
     */
    public ArrayList<int[]> getIndConflictsList() {
        return indConflictsList;
    }

    /**
     * Sets a new maximum number of colors (kMax).
     * @param newKMax The new maximum number of colors.
     */
    @SuppressWarnings("unused")
    public void setKMax(int newKMax) {
        kMax = newKMax;
    }

    /**
     * Retrieves the node list associated with this DSaturKMax instance.
     * @return The list of nodes.
     */
    @SuppressWarnings("unused")
    public NodeList getNodeList() {
        return nodeList;
    }

    /**
     * Retrieves the colors assigned to each node.
     * @return The array of node colors.
     */
    public int[] getNodeColors() {
        return nodeColors;
    }

    /**
     * Initializes the saturation degree array based on the degrees of nodes in the graph.
     */
    public void createSaturationDegreeArray() {
        saturationDegree = new int[nodeList.size()];
        Arrays.fill(saturationDegree, 0); // Initialize saturation to 0
    }

    /**
     * Executes the DSatur algorithm to color the graph.
     */
    public void dSaturAlgo() {
        int indTreatedNodeTmp;

        for (int ii = 0; ii < saturationDegree.length; ii++) {
            indTreatedNodeTmp = getHighestSaturationDegreeIndex();
            colorNode(indTreatedNodeTmp);
            updateAdjacentNodes(indTreatedNodeTmp);
        }

        calcIndConflictsList();
    }

    /**
     * Colors the node at the specified index with the lowest available color.
     * @param nodeIndex The index of the node to be colored.
     */
    private void colorNode(int nodeIndex) {
        saturationDegree[nodeIndex] = -1; // Mark node as colored
        nodeColors[nodeIndex] = getBestColor(nodeIndex);
    }

    /**
     * Checks if a node is already colored.
     * @param nodeIndex The index of the node to check.
     * @return True if the node is colored, otherwise false.
     */
    private boolean isColored(int nodeIndex) {
        return nodeColors[nodeIndex] != -1;
    }

    /**
     * Finds the index of the node with the highest saturation degree.
     * @return The index of the node.
     */
    public int getHighestSaturationDegreeIndex() {
        int indMax = -1;
        for (int ii = 0; ii < saturationDegree.length; ii++) {
            if (!isColored(ii)) {
                if (indMax == -1 || saturationDegree[ii] > saturationDegree[indMax] ||
                        (saturationDegree[ii] == saturationDegree[indMax] && nodeList.get(ii).getDegree() > nodeList.get(indMax).getDegree())) {
                    indMax = ii;
                }
            }
        }
        return indMax;
    }

    /**
     * Finds the best color for a node based on saturation degree and conflict resolution.
     * @param nodeIndex The index of the node.
     * @return The best color for the node.
     */
    public int getBestColor(int nodeIndex) {
        int bestColor = getFirstAvailableColor(nodeIndex);
        if (bestColor >= kMax) {
            if (rand)
                bestColor = new Random().nextInt(kMax);
            else
                bestColor = getLeastConflictColor(nodeIndex);
        }

        return bestColor;
    }

    /**
     * Finds the lowest available color for a node.
     * @param nodeIndex The index of the node.
     * @return The lowest available color.
     */
    public int getFirstAvailableColor(int nodeIndex) {
        ArrayList<Integer> usedColors = getAdjacentColors(nodeIndex);
        for (int color = 0; color < kMax; color++) {
            if (!usedColors.contains(color)) {
                return color;
            }
        }
        return kMax + 1; // Return an invalid color if all colors are used
    }

    /**
     * Finds the least conflict color for a node.
     * @param nodeIndex The index of the node.
     * @return The least conflict color.
     */
    public int getLeastConflictColor(int nodeIndex) {
        int[] nbEachColorAdjNodes = getNbEachColorAdjNodes(nodeIndex);
        int leastConflictColor = 0;

        for (int i = 1; i < nbEachColorAdjNodes.length; i++) {
            if (nbEachColorAdjNodes[i] < nbEachColorAdjNodes[leastConflictColor]) {
                leastConflictColor = i;
            }
        }

        return leastConflictColor;
    }

    /**
     * Retrieves the number of adjacent nodes for each color around a node.
     * @param nodeIndex The index of the node.
     * @return An array where each index represents a color and the value at that index represents the number of adjacent nodes of that color.
     */
    public int[] getNbEachColorAdjNodes(int nodeIndex) {
        NodePlus node = nodeList.get(nodeIndex);
        ArrayList<Integer> indAdjNodes = node.getAdjIndList();

        int[] nbEachColorAdjNodes = new int[kMax];

        int color;

        for (int indAdjNode : indAdjNodes) {
            color = nodeColors[indAdjNode];
            if (color != -1)
                nbEachColorAdjNodes[color]++;
        }

        return nbEachColorAdjNodes;
    }

    /**
     * Retrieves the colors used by adjacent nodes.
     * @param nodeIndex The index of the node.
     * @return An ArrayList containing the colors used by adjacent nodes.
     */
    private ArrayList<Integer> getAdjacentColors(int nodeIndex) {
        NodePlus node = nodeList.get(nodeIndex);
        ArrayList<Integer> indAdjNodes = node.getAdjIndList();
        ArrayList<Integer> usedColors = new ArrayList<>();

        for (int indTmp : indAdjNodes) {
            if (!usedColors.contains(nodeColors[indTmp]))
                usedColors.add(nodeColors[indTmp]);
        }

        return usedColors;
    }

    /**
     * Updates the saturation degrees of adjacent nodes after coloring a node.
     * @param nodeIndex The index of the colored node.
     */
    private void updateAdjacentNodes(int nodeIndex) {
        NodePlus node = nodeList.get(nodeIndex);
        ArrayList<Integer> indAdjNodes = node.getAdjIndList();
        for (int indTmp : indAdjNodes) {
            if (!isColored(indTmp)) {
                saturationDegree[indTmp] = getSaturDegree(indTmp);
            }
        }
    }

    /**
     * Calculates the saturation degree of a node.
     * @param nodeIndex The index of the node.
     * @return The saturation degree of the node.
     */
    private int getSaturDegree(int nodeIndex) {
        NodePlus node = nodeList.get(nodeIndex);
        ArrayList<Integer> indAdjNodes = node.getAdjIndList();
        ArrayList<Integer> viewedColors = new ArrayList<>();
        int saturDegree = 0;
        int colorTmp;

        for (int indTmp : indAdjNodes) {
            colorTmp = nodeColors[indTmp];
            if (isColored(indTmp) && !viewedColors.contains(colorTmp)) {
                saturDegree++;
                viewedColors.add(colorTmp);
            }
        }
        return saturDegree;
    }

    /**
     * Calculates the list of conflicting nodes based on their colors.
     */
    private void calcIndConflictsList() {
        indConflictsList = new ArrayList<>();
        for (NodePlus node : nodeList.getNodeList()) {
            for (int indAdjNode : nodeList.get(node.getIndex()).getAdjIndList()) {
                if (nodeColors[node.getIndex()] == nodeColors[indAdjNode]
                        && node.getIndex() < indAdjNode) {
                    indConflictsList.add(new int[]{node.getIndex(), indAdjNode});
                }
            }
        }
    }
}
