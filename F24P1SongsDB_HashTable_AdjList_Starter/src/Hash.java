/**
 * Hash table class
 *
 * @author Jett Morrow & Adam Schantz
 * @version jettmorrow & adams03
 */

public class Hash {

    private Record[] records;
    private int tableCap;
    private int tableSize;
    private Record tombstone;

    public Hash(int hashSize) {
        records = new Record[hashSize];
        tableCap = hashSize;
        tableSize = 0;
        tombstone = new Record("TOMBSTONE");
    }

    // needs methods
    // insert
    // if an insert will be size >= 50% of cap then double size of records and
    // rehash
    // first check home position, if not quadratic probe to find open positions
    // when encountering a tombstone, mark that index, probe to check for
    // duplicates
    // if we find duplicate do not add it, it we find empty position then add at
    // the first tombstone

    // my idea of a tombstone is, remove a record in records, by placing the
    // tombstone record in its place

    //print |songname| is added to the Artist database.
    public void insert(String key) {

        // check if we need to double records size
        if (tableSize + 1 >= tableCap / 2) {
            reSizeRecords();
        }

        // if not add it to records
        int index = h(key, tableCap);
        int i = 1;
        // find empty position
        // either index is empty, tombstone, cur Key, or random key

        while (records[index] != null) {
            // if we find tombstone remember it and check for dup
            if (records[index].getKey().equals("TOMBSTONE")) {
                //key is not in the hash table so add it
                if (find(key) == null) {
                    break;
                }
                else {
                    //key already exists
                    return;
                }
            }

            // quadratic prob;
            index += Math.pow(i, 2) % tableCap;
            i++;
        }
        
        //add the key to table and increment size
        records[index] = new Record(key);
        tableSize++;
    }


    /**
     * Find function to determine if a key value is in the hashtabl
     * 
     * @param key
     *            takes in a key value to search the hash
     * @return the record if found, or null if not found
     */
    public Record find(String key) {
        int home = h(key, tableCap);
        int i = 1;

        while (records[home] != null) {
            if (records[home].getKey().equals(key)) {
                return records[home];
            }

            home += Math.pow(i, 2) % tableCap;
            i++;
        }

        // was never found
        return null;
    }


    public void reSizeRecords() {
        //create new records array, double size, rehasheverything
        //reset tombstones, update cap, and update size
        
        // create new records array
        Record[] newRecords = new Record[tableCap * 2];

        // rehash all records, except tombstons from records into newRecords
        for (Record r : records) {
            // if tombstone or empty skip it

            // if valid key, rehash and place in newRecords
        }
    }

    // remove
    // print


    /**
     * Compute the hash function
     * 
     * @param s
     *            The string that we are hashing
     * @param length
     *            Length of the hash table (needed because this method is
     *            static)
     * @return
     *         The hash function value (the home slot in the table for this key)
     */
    public static int h(String s, int length) {
        int intLength = s.length() / 4;
        long sum = 0;
        for (int j = 0; j < intLength; j++) {
            char[] c = s.substring(j * 4, (j * 4) + 4).toCharArray();
            long mult = 1;
            for (int k = 0; k < c.length; k++) {
                sum += c[k] * mult;
                mult *= 256;
            }
        }

        char[] c = s.substring(intLength * 4).toCharArray();
        long mult = 1;
        for (int k = 0; k < c.length; k++) {
            sum += c[k] * mult;
            mult *= 256;
        }

        return (int)(Math.abs(sum) % length);
    }
}
