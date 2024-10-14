package colorationAlgorithms;

import graphTools.GraphPlus;
import nodes.NodeList;
import nodes.NodePlus;
import org.graphstream.graph.Graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

/**
 * Implements the Welsh-Powell graph coloring algorithm.
 */
public class WelshPowell {

    /** The list of nodes in the graph. */
    private final NodeList nodeList;

    /** The sorted indexes of nodes based on their degrees. */
    private int[] sortedIndexes;

    /** The number of nodes in the graph. */
    private final int nbNodes;

    /** The colors assigned to each node. */
    private int[] nodeColors;

    private int kMax;

    private ArrayList<int[]> indConflictsList;

    GraphPlus graph;

    /**
     * Constructs a WelshPowell object for the given graph.
     *
     * @param newGraph the input graph
     */
    public WelshPowell(GraphPlus newGraph) {
        graph = newGraph;
        nodeList = new NodeList(graph);
        kMax = graph.getKMax();

        sortedIndexes = createSortedIndexes();
        nbNodes = nodeList.size();
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
     * Creates and returns the sorted indexes of nodes based on their degrees.
     *
     * @return the sorted indexes
     */
    private int[] createSortedIndexes() {
        int nbNodes = nodeList.size();
        sortedIndexes = new int[nbNodes];
        for (int ii = 0; ii < nbNodes; ii++) {
            sortedIndexes[ii] = ii;
        }
        sortedIndexes = mergeSort(sortedIndexes);
        return sortedIndexes;
    }

    /**
     * Sorts the indexes using the merge sort algorithm.
     *
     * @param sortedIndexes the indexes to be sorted
     * @return the sorted indexes
     */
    public int[] mergeSort(int[] sortedIndexes) {
        int nbNodesIndexes = sortedIndexes.length;
        if (nbNodesIndexes > 1) {
            int mid = nbNodesIndexes / 2;
            int[] sortedIndexesL = new int[mid];
            int[] sortedIndexesR = new int[nbNodesIndexes - mid];

            System.arraycopy(sortedIndexes, 0, sortedIndexesL, 0, mid);
            System.arraycopy(sortedIndexes, mid, sortedIndexesR, 0,
                    nbNodesIndexes - mid);

            mergeSort(sortedIndexesL);
            mergeSort(sortedIndexesR);
            merge(sortedIndexes, sortedIndexesL, sortedIndexesR);
        }
        return sortedIndexes;
    }

    /**
     * Merges two sorted subarrays into one sorted array.
     *
     * @param indexes the array to be merged into
     * @param indexesL the left subarray
     * @param indexesR the right subarray
     */
    private void merge(int[] indexes, int[] indexesL, int[] indexesR) {
        int nbNodesL = indexesL.length, nbNodesR = indexesR.length;
        int indL = 0, indR = 0, ind = 0;

        while (indL < nbNodesL && indR < nbNodesR) {
            int[] degreeList = nodeList.getDegreeList();
            if (degreeList[indexesL[indL]] >= degreeList[indexesR[indR]]) {
                indexes[ind++] = indexesL[indL++];
            } else {
                indexes[ind++] = indexesR[indR++];
            }
        }

        while (indL < nbNodesL) {
            indexes[ind++] = indexesL[indL++];
        }

        while (indR < nbNodesR) {
            indexes[ind++] = indexesR[indR++];
        }
    }

    /**
     * Executes the Welsh-Powell algorithm to color the graph.
     */
    public void welshPowellAlgo() {
        int colorIndTmp;
        Random rand = new Random();

        if (nbNodes > 0) {
            nodeColors = new int[nbNodes];
            Arrays.fill(nodeColors, -1);
            nodeColors[0] = 0;

            boolean[] availableColors = new boolean[nbNodes];
            Arrays.fill(availableColors, true);

            for (int nodeIndex = 1; nodeIndex < nbNodes; nodeIndex++) {
                availableColors = setAvailableColors(nodeIndex, nodeColors);
                colorIndTmp = getFirstAvailableColor(availableColors);
                if (colorIndTmp >= kMax) {
                    colorIndTmp = rand.nextInt(kMax);
                }
                nodeColors[nodeIndex] = colorIndTmp;
            }
        }

        calcIndConflictsList();
    }

    /**
     * Determines available colors for a node based on its adjacent nodes.
     *
     * @param nodeIndex the index of the node
     * @param nodeColors the array representing colors assigned to nodes
     * @return an array indicating available colors
     */
    public boolean[] setAvailableColors(int nodeIndex, int[] nodeColors) {
        int nbAdjacentNodes = nodeList.get(nodeIndex).getDegree();
        NodePlus[] adjNodesList = nodeList.get(nodeIndex).getAdjNodeList();

        boolean[] availableColors = new boolean[nbNodes];
        Arrays.fill(availableColors, true);

        for (int ii = 0; ii < nbAdjacentNodes; ii++) {
            int nodeIndexAdj = adjNodesList[ii].getIndex();
            if (nodeColors[nodeIndexAdj] != -1)
                availableColors[nodeColors[nodeIndexAdj]] = false;
        }

        return availableColors;
    }

    /**
     * Gets the index of the first available color.
     *
     * @param colorIsAvailable an array indicating available colors
     * @return the index of the first available color
     */
    public int getFirstAvailableColor(boolean[] colorIsAvailable) {
        int listSize = colorIsAvailable.length;
        int colorInd = 0;
        while (colorInd < listSize && !colorIsAvailable[colorInd]) {
            colorInd++;
        }
        return colorInd;
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

    /**
     * Returns a string representation of the object.
     *
     * @return a string representation
     */
    public String toString() {
        String result = "[";
        if (nbNodes > 0) {
            result += String.valueOf(nodeList.get(sortedIndexes[0]));
            for (int ii = 1; ii < nbNodes; ii++) {
                result = String.format("%s, %s", result,
                        nodeList.get(sortedIndexes[ii]).toString());
            }
        }
        return result + "]";
    }
}
