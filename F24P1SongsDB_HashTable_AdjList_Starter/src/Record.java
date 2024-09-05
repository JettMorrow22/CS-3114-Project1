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

    /**
     * basic constructor for the record
     * 
     * @param k
     *            the key
     */
    public Record(String k) {
        key = k;
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
