/**
 * Graph class
 *
 * @author Jett Morrow & Adam Schantz
 * @version jettmorrow & adams03
 */
public class Graph {

    private DoubleLL[] vertex;
    private int numberOfNodes;
    private int[] freeSpots;
    private int freeSpotIndex;
    private int[] parent;
    private int[] weight;

    // keep track of the freed spots, but how

    /**
     * basic constructor for graph
     */
    public Graph() {
        vertex = new DoubleLL[50];
        numberOfNodes = 0;
        freeSpots = new int[50];
        for (int x = 0; x < 50; x++) {
            freeSpots[x] = -1;
        }
        freeSpotIndex = 0;
        
        //create the array for the parents and assign each a weight of 0
        parent = new int[50];
        weight = new int[50];
        for (int i = 0; i < 50; i++) {
            parent[i] = -1; // each node is set to no parent
            weight[i] = 1;   // initial weight is 1
        }
    }


    /**
     * basic getter for numberOfNodes
     *
     * @return numberOfNodes
     */
    public int getnumberOfNodes() {
        return numberOfNodes;
    }


    /**
     * basic getter for vertex for tetst
     * 
     * @return
     */
    public DoubleLL[] getVertex() {
        return vertex;
    }


    /**
     * getter for freeSpots field
     * 
     * @return freeSpots
     */
    public int[] getFreeSpots() {
        return freeSpots;
    }


    /**
     * getter for freeSpotIndex
     * 
     * @return freeSpotIndex
     */
    public int getFreeSpotIndex() {
        return freeSpotIndex;
    }


    /**
     * adds Node to vertex array and initilizes DoubleLL
     * 
     * @return the index it is at
     */
    public int addNode() {
        expand();

        // check to use freeSpots first
        int index = checkFreeSpots();
        if (index == -1) {
            index = numberOfNodes;
        }
        vertex[index] = new DoubleLL();
        numberOfNodes++;
        return index;
    }


    /**
     * Adds edges for two vertices
     * 
     * @param v1
     *            first vertex in graph
     * @param v2
     *            second vertex in graph
     * @return false if both vertices already have edge for the other true if
     *         not
     * 
     */
    public boolean addEdge(int v1, int v2) {
        // check if edge is already there

        // check if it contains other vertex, if not adds it
        boolean addedV1 = !vertex[v1].contains(v2) && vertex[v1].addToEnd(v2);

        boolean addedV2 = !vertex[v2].contains(v1) && vertex[v2].addToEnd(v1);

        // return true if any edge was added
        return addedV1 || addedV2;

    }


    /**
     * Method to use empty slots in vertex
     * 
     * @return -1 if no freeSpots and index of free spot if available
     */
    public int checkFreeSpots() {
        if (freeSpotIndex == 0) {
            return -1;
        }
        freeSpotIndex--;
        int index = freeSpots[freeSpotIndex];

        // remove freeSpot
        freeSpots[freeSpotIndex] = -1;

        return index;
    }


    /**
     * removes the edge between v1 and v2
     * 
     * @param v1
     *            vertex 1
     * @param v2
     *            vertex 2
     * @return true if any edges are removed, false if no edges are removed
     */
    public boolean removeEdge(int v1, int v2) {
        boolean removed = false;

        // remove v2 from v1 DoubleLL
        if (vertex[v1].contains(v2)) {
            vertex[v1].remove(v2);
            removed = true;
        }

        // remove v1 from v2 DoubleLL
        if (vertex[v2].contains(v1)) {
            vertex[v2].remove(v1);
            removed = true;
        }

        return removed;
    }


    /**
     * removes all the occurences of the node from the other nodes
     * then sets the vertex to null to indicate a empty spot
     * updates freeSpots and numberOfNodes count
     * 
     * @param n
     *            node to be removed
     * @return true if removed false if nothing to be removed
     */
    public boolean removeNode(int n) {
        if (vertex[n] == null)
            return false;

        // remove from all the other nodes DoubleLL
        for (int x = 0; x < vertex.length; x++) {
            if (x != n && vertex[x] != null) {
                removeEdge(x, n);
            }
        }

        // now remove the node
        freeSpots[freeSpotIndex] = n;
        freeSpotIndex++;
        vertex[n] = null;
        numberOfNodes--;
        return true;
    }


    /**
     * method called before adding any node to check if we need to expand the
     * graph or not
     */
    public void expand() {
        // we do not need to expand
        if (numberOfNodes < vertex.length) {
            return;
        }

        // we expand vertex first
        DoubleLL[] temp = new DoubleLL[vertex.length * 2];
        for (int x = 0; x < vertex.length; x++) {
            temp[x] = vertex[x];
        }

        // update fields
        vertex = temp;

    }

    /**
     * union method, aims to combine two disjoint graphs if they have
     * different parent methods
     * @param a one of the nodes we are using
     * @param b the other node we are using
     */
    public void union(int a, int b)
    {
        //we need to use the find method to determine if the nodes
        //are in the same graph or not
        //if they are, do nothing - if they aren't, combine them
        int root1 = find(a);
        int root2 = find(b);
        if (root1 != root2)
            {
            if (weight[root2] > weight[root1]) {
                parent[root1] = root2;
                weight[root2] += weight[root1];
              } else {
                parent[root2] = root1;
                weight[root1] += weight[root2];
              }
            }
    }
    /**
     * find method for a node, we want to return the root of the node
     * @param node the node we are finding the root of
     * @return the root of the node
     */
    public int find(int node)
    {
        if (parent[node] == -1)
        {
            return node;
        }
        parent[node] = find(parent[node]);
        return parent[node];
    }
    /**
     * print method
     * @param title the name of whatever we want to print
     */
    public void print(String title)
    {
        //print either the list of artists, list of songs, or the graph
    }
    // connectedComponent
    // diameter

}
