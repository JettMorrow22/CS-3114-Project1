/**
 * Our record class which has a key for the hashTable and a node for the graph
 * 
 * @author Jett Morrow & Adam Schantz
 * 
 * @version jettmorrow & adams03
 */
public class Record {

    // key will be the artist/song
    private String key;
    // node is the node in the graph
    private Node node;

    /**
     * basic constructor for record
     * 
     * @param k
     *            key
     * @param n
     *            node
     */
    public Record(String k, Node n) {
        key = k;
        node = n;
    }


    /**
     * basic getter for Node
     * 
     * @return node
     */
    public Node getNode() {
        return node;
    }


    /**
     * Basic setter for Node
     * 
     * @param node
     *            new node
     */
    public void setNode(Node node) {
        this.node = node;
    }


    /**
     * basic getter for key field
     * 
     * @return key
     */
    public String getKey() {
        return key;
    }

}
