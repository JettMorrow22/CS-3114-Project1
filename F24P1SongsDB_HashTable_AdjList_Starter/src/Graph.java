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

    // keep track of the freed spots, but how

    public Graph() {
        vertex = new DoubleLL[50];
        numberOfNodes = 0;
        freeSpots = new int[50];
    }


    /**
     * basic getter for numberOfNodes
     *
     * @return numberOfNodes
     */
    public int getnumberOfNodes() {
        return numberOfNodes;
    }


    public DoubleLL[] getVertex() {
        return vertex;
    }


    /**
     * Method to use empty slots in vertex
     * 
     * @return -1 if no freeSpots and index of free spot if available
     */
    public int checkFreeSpots() {
        if (freeSpots[0] == 0)
            return -1;

        int index = 0;
        while (freeSpots[index] != 0) {
            index++;
        }
        int temp = freeSpots[index];
        // get rid of free spot
        freeSpots[index] = 0;
        return temp;
    }


    // newNode
    public boolean addNode(int index) {
        // create new DoubleLL for node
        // create initial node that is adjacent to nothing
        vertex[numberOfNodes] = new DoubleLL();
        numberOfNodes++;
        return true;
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

        // they both have the adjacency so its a duplicate
        if (vertex[v1].contains(v2) && vertex[v2].contains(v1)) {
            return false;
        }

        // add one or both edges to vertices
        if (!vertex[v1].contains(v2)) {
            vertex[v1].addToEnd(v2);
        }

        if (!vertex[v2].contains(v1)) {
            vertex[v2].addToEnd(v1);
        }
        return true;

    }

    // addEdge

    // hasEdge
    // removeEdge
    // removeNode -> remove all edges from the node
    // expand
    // print
    // union
    // connectedCompopent
    // diameter

    // OMG GUY SAID TO CREATE NEW NODE AND RECORD IN THE CONTROLLER CLASS

    // vertex[x] has DoubleLL of all the adjacent nodes

    // vertex is just
    // Okay so graph has a array of Doubly linked list
    // each doubly linked list has DLLNodes which contain prev, next, and Record
    // the record has the key for the hashtable and the index in the graph

    // When insert command
    // if new artist name or new song, add new nodes to the graph
    // add a link in the recording graph between nodes for the artist and song

    // need to be able to remove nodes from the graph, this is done in DLL

    // okay nodes are the vertex
    // and the nodes are the songs or artist
    // the DoubleLL is the list of adjacent nodes
    // we add Node when there is new song or artist
    // we add edge when there is a new artist-song pair

    // Keep track of removed spots so we do not waste memory

}
