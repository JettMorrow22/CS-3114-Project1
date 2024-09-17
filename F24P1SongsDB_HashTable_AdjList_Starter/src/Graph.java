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
    
    /**
     * Method to use empty slots in vertex
     * @return -1 if no freeSpots and index of free spot if available
     */
    public int checkFreeSpots() {
        if (freeSpots[0] == 0) return -1;
        
        int index = 0;
        while (freeSpots[index] != 0) {
            index++;
        }
        int temp = freeSpots[index];
        //get rid of free spot
        freeSpots[index] = 0;
        return temp;
    }

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


    // newNode
    public boolean addNode(int index) {
        // if key already in graph do not add it
        if (hasNode(index))
            return false;

        // create new DoubleLL for node
        // create initial node that is adjacent to nothing
        vertex[numberOfNodes] = new DoubleLL();
        numberOfNodes++;
        return true;
    }


    /**
     * method to determine if the graph contains a DLLNode with a recod with the
     * key
     * 
     * @param s
     *            the key
     * @return true if the graph contains the key, false if not
     */
    public boolean hasNode(int index) {
        // determine if the key is already in the graph
        for (int x = 0; x < numberOfNodes; x++) {
            // if we already have this key in the graph
            if (vertex[x].getHead().getData() == index) {
                return true;
            }
        }
        return false;
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

    // this is deadass all in 14.02

}
