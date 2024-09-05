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
    private Record TOMBSTONE;

    public Hash(int hashSize) {
        records = new Record[hashSize];
        tableCap = hashSize;
        tableSize = 0;
        TOMBSTONE = new Record("TOMBSTONE");
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


    // print |songname| is added to the Artist database.
    // returns true is there was a valid insert, false if not
    public boolean insert(String key) {

        int home = h(key, tableCap);
        int i = 1;

        // find empty position
        // either index is empty, tombstone, cur Key, or random key
        while (records[home] != null) {

            // if we find tombstone remember it and check for dup
            if (records[home] == TOMBSTONE) {
                // if no dup then break and add it
                if (find(key) == null) {
                    break;
                }
                else {
                    // dup return false
                    return false;
                }
            }

            // quadratic prob
            home = (home + i * i) % tableCap;
            i++;
        }

        // add the key to table and increment size
        records[home] = new Record(key);
        tableSize++;
        return true;
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

            home = (home + i * i) % tableCap;
            i++;
        }

        // was never found
        return null;
    }


    /**
     * method to determine if the table needs resizing before an insertion
     * 
     * @return true if it needs to be resized and gets resized false if not
     */
    public boolean checkAndResize() {
        // check if we need to double records size then add it
        if (tableSize + 1 >= tableCap / 2) {
            reSizeRecords();
            return true;
        }
        return false;

    }


    /**
     * method to double the size of records to decrease collision
     * rehashes all valid records and disregards tombstones
     */
    public void reSizeRecords() {
        // create new records array, double size, rehasheverything
        // reset tombstones, update cap, and update size

        // create new records array
        Record[] newRecords = new Record[tableCap * 2];

        // rehash all records, except tombstons from records into newRecords
        for (Record r : records) {
            // if tombstone or empty skip it
            if (r == null || r == TOMBSTONE) {
                continue;
            }

            // if valid key, rehash and place in newRecords
            int home = h(r.getKey(), tableCap * 2);
            int i = 1;
            while (newRecords[home] != null) {
                home = (home + i * i) % (tableCap * 2);
                i++;
            }

            newRecords[home] = new Record(r.getKey());
        }

        // update records and tableCap
        records = newRecords;
        tableCap = tableCap * 2;
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
        int i = 1;
        while (records[home] != null) {
            if (records[home].getKey().equals(key)) {
                records[home] = TOMBSTONE;
                tableSize--;
                return true;
            }
            home = (home + i * i) % tableCap;
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
        String[] res = new String[tableSize];
        int index = 0;

        for (int x = 0; x < tableCap; x++) {
            if (records[x] == null || records[x] == TOMBSTONE)
                continue;
            String line = x + ": |" + records[x].getKey() + "|";
            res[index] = line;
            index++;
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
}
