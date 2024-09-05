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

    /**
     * Constructor for hash
     * inializes teh Record[] with param size and tableCap and tableSize
     * 
     * @param hashSize
     *            the length of the Record[]
     */
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


    /**
     * Attempts to insert key into the hash table
     * 
     * @param key
     *            the key to be added
     * @return true if key was added, false if not
     */
    public boolean insert(String key) {

        int home = h(key, tableCap);
        int index = home;
        int i = 1;

        // find empty position
        // either index is empty, tombstone, cur Key, or random key
        while (records[index] != null) {

            // if we find tombstone remember it and check for dup
            if (records[index] == tombstone) {
                // if no dup then break and add it
                if (find(key) == null) {
                    break;
                }
                else {
                    // dup return false
                    return false;
                }
            }
            else if (records[index].getKey().equals(key)) {
                // we at a duplicate
                return false;
            }

            // quadratic prob
            index = (home + i * i) % tableCap;
            i++;
        }

        // add the key to table and increment size
        records[index] = new Record(key);
        tableSize++;
        return true;
    }


    /**
     * Find function to determine if a key value is in the hashtable
     * 
     * @param key
     *            takes in a key value to search the hash
     * @return the record if found, or null if not found
     */
    public Record find(String key) {
        int home = h(key, tableCap);
        int index = home;
        int i = 1;

        while (records[index] != null) {
            if (records[index].getKey().equals(key)) {
                return records[index];
            }

            index = (home + i * i) % tableCap;
            i++;
        }

        // was never found
        return null;
    }


    /**
     * method to determine if the table needs resizing before an insertion
     * if so it doubles the cap and rehashes everything except TOMBSTONE and
     * null
     * 
     * @return true if it needs to be resized and gets resized false if not
     */
    public boolean checkAndResize() {
        // check if we need to double records size then add it
        if (tableSize + 1 >= tableCap / 2) {
            // create new records array
            Record[] newRecords = new Record[tableCap * 2];

            // rehash all records, except tombstons from records into newRecords
            for (Record r : records) {
                // if tombstone or empty skip it
                if (r == null || r == tombstone) {
                    continue;
                }

                // if valid key, rehash and place in newRecords
                int home = h(r.getKey(), tableCap * 2);
                int index = home;
                int i = 1;
                while (newRecords[index] != null) {
                    index = (home + i * i) % (tableCap * 2);
                    i++;
                }

                newRecords[index] = new Record(r.getKey());
            }

            // update records and tableCap
            records = newRecords;
            tableCap = tableCap * 2;
            return true;
        }
        return false;

    }


    /**
     * this method attempts to remove key from the hashTable
     * if found it replaces the record with TOMBSTONE and return true
     * if not return false
     * 
     * @param key
     *            the key to be searching for
     * @return true if key is removed false if not
     */
    public boolean remove(String key) {
        // search for key in table, if it exists remove it
        int home = h(key, tableCap);
        int index = home;
        int i = 1;
        while (records[index] != null) {
            if (records[index].getKey().equals(key)) {
                records[index] = tombstone;
                tableSize--;
                return true;
            }
            index = (home + i * i) % tableCap;
            i++;
        }
        return false;
    }


    /**
     * Print method, prints each non null record as the index of their array
     * then the key
     * 
     * @return String[] to represent each record
     */
    public String[] print() {
        // account for tombstones
        String[] res = new String[tableCap];

        for (int x = 0; x < tableCap; x++) {
            String line = "";

            if (records[x] == null) {
                continue;
            }
            else if (records[x] == tombstone) {
                line += x + ": TOMBSTONE";
            }
            else {
                line += x + ": |" + records[x].getKey() + "|";
            }
            res[x] = line;
        }

        return res;
    }


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


    /**
     * basic getter for records
     * 
     * @return records
     */
    public Record[] getRecords() {
        return records;
    }


    /**
     * basic getter for tableCap
     * 
     * @return tableCap
     */
    public int getTableCap() {
        return tableCap;
    }


    /**
     * basic getter for tableSize
     * 
     * @return tableSize
     */
    public int getTableSize() {
        return tableSize;
    }
}
