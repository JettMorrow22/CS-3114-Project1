/**
 * Node class
 *
 * @author Jett Morrow & Adam Schantz
 * @version jettmorrow & adams03
 */
public class Node {

    private int index;

    /**
     * constructor for index
     * 
     * @param i
     *            the index
     */
    public Node(int i) {
        index = i;
    }


    /**
     * Basic getter for index
     * 
     * @return index
     */
    public int getIndex() {
        return index;
    }


    /**
     * basic setter for index
     * 
     * @param index
     *            new index
     */
    public void setIndex(int index) {
        this.index = index;
    }

}
