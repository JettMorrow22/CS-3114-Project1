import student.TestCase;

/**
 * test class for Hash
 * 
 * @author Jett Morrow & Adam Schantz
 * @version jettmorrow & adams03
 */
public class HashTest extends TestCase {
    private Hash hashTable;

    /**
     * Sets up the tests that follow. In general, used for initialization
     */
    public void setUp() {
        hashTable = new Hash(10);
    }


    /**
     * method to test the quadratic probing with loop around
     * 
     * @throws Exception
     *             exception
     */
    public void testCollision() throws Exception {
        hashTable.insert("zerof"); // all these index 0
        hashTable.insert("Jett Morrow"); // all these index 0
        hashTable.insert("Jett t");
        hashTable.insert("Jett t6raoewqah");
        hashTable.insert("Adamasa");

        assertTrue(hashTable.getRecords()[0].getKey().equals("zerof"));
        assertTrue(hashTable.getRecords()[1].getKey().equals("Jett Morrow"));
        assertTrue(hashTable.getRecords()[4].getKey().equals("Jett t"));
        assertTrue(hashTable.getRecords()[9].getKey().equals(
            "Jett t6raoewqah"));
        assertTrue(hashTable.getRecords()[6].getKey().equals("Adamasa"));
    }


    /**
     * Test cases for insert method
     * 
     * @throws Exception
     *             exception
     */
    public void testInsert() throws Exception {
        // inserting a key
        hashTable.insert("Jett Morrow"); // should be index 0
        assertTrue(hashTable.getRecords()[0].getKey().equals("Jett Morrow"));
        assertEquals(1, hashTable.getTableSize());

        hashTable.insert("Adam"); // should be index 1
        assertTrue(hashTable.getRecords()[1].getKey().equals("Adam"));
        assertEquals(2, hashTable.getTableSize());

        // inserting a duplicate
        hashTable.insert("Jett Morrow"); // should be index 0
        assertEquals(2, hashTable.getTableSize());

        // probing test
        hashTable.insert("Adamasa"); // should be index 0, so prob to 1, then
                                     // prob to 4
        assertEquals(3, hashTable.getTableSize());
        assertTrue(hashTable.getRecords()[4].getKey().equals("Adamasa"));

        hashTable.insert("Jenna"); // should be index 7
        assertTrue(hashTable.getRecords()[7].getKey().equals("Jenna"));
        assertEquals(4, hashTable.getTableSize());
        assertEquals(10, hashTable.getTableCap());

        // check when adding to a tombstone
        hashTable.remove("Adamasa"); // remove and add tombstone to 4
        assertTrue(hashTable.insert("Jettt w")); // try and add to 4
        assertTrue(hashTable.getRecords()[4].getKey().equals("Jettt w"));

        // hit tombstone and find dup later
        hashTable.remove("Jettt w");
        hashTable.insert("Adamasa");

        // remove 0
        hashTable.remove("Jett Morrow");
        // this hits a tombstone, probes, end up finding dup so doesnt add it
        assertFalse(hashTable.insert("Adamasa"));

    }


    /**
     * test case for find method
     * 
     * @throws Exception
     *             exception
     */
    public void testFind() throws Exception {
        hashTable.insert("Jett Morrow"); // should be index 0
        hashTable.insert("Adam"); // should be index 1
        hashTable.insert("Adamasa"); // should be index 0, so prob to 1, then
                                     // prob to 5

        // in table
        assertTrue(hashTable.find("Jett Morrow").getKey().equals(
            "Jett Morrow"));
        assertTrue(hashTable.find("Adam").getKey().equals("Adam"));
        assertTrue(hashTable.find("Adamasa").getKey().equals("Adamasa"));

        // not in table
        assertNull(hashTable.find("JENNA"));
    }


    /**
     * testcase for checkAndResize method without tombstones
     * 
     * @throws Exception
     *             exception
     */
    public void testCheckAndResize() throws Exception {
        hashTable.insert("Jett Morrow"); // should be index 0
        hashTable.insert("Adam"); // should be index 1
        hashTable.insert("Adamasa"); // should be index 0, so prob to 1, then
                                     // prob to 5

        assertFalse(hashTable.checkAndResize());
        hashTable.insert("Jenna"); // should be index 7
        assertEquals(4, hashTable.getTableSize());
        assertEquals(10, hashTable.getTableCap());

        // if we were to add one more it would have to double in size
        hashTable.insert("Kant");
        hashTable.checkAndResize();
        assertEquals(20, hashTable.getTableCap());
        assertEquals(5, hashTable.getTableSize());
        // check if everything got rehashed correclty
        assertTrue(hashTable.getRecords()[0].getKey().equals("Jett Morrow"));
        assertTrue(hashTable.getRecords()[1].getKey().equals("Adam"));
        assertTrue(hashTable.getRecords()[10].getKey().equals("Adamasa"));
        assertTrue(hashTable.getRecords()[7].getKey().equals("Jenna"));
        assertTrue(hashTable.getRecords()[3].getKey().equals("Kant"));
    }


    /**
     * test case for checkAndResize with tombstones and probing on second hash
     * 
     * @throws Exception
     *             exception
     */
    public void testCheckAndResizeTombstones() throws Exception {
        hashTable.insert("Jett Morrow"); // should be index 0
        hashTable.insert("Adam"); // should be index 1
        hashTable.insert("Adamasa"); // home = 0, index 4
        
        // add some tombstones
        hashTable.insert("JENNA");
        hashTable.remove("JENNA"); //TOMBSTONE @ 9

        // add records that will have to probe when rehashed
        hashTable.insert("Jettt wasd"); //home 1, prob 2
        hashTable.remove("Jettt wasd"); //TOMBSTONE @ 2
        
        hashTable.insert("JENNA KANTOWSKI"); //home 0, prob 1, prob 4, prob 9
                                             //add 9
       
        assertFalse(hashTable.checkAndResize());
        assertTrue(hashTable.getRecords()[0].getKey().equals("Jett Morrow"));
        assertTrue(hashTable.getRecords()[1].getKey().equals("Adam"));
        assertTrue(hashTable.getRecords()[4].getKey().equals("Adamasa"));
        assertTrue(hashTable.getRecords()[9].getKey().equals(
            "JENNA KANTOWSKI"));
        
        
        //check resizing with tombstones
        hashTable.insert("Hello"); //index 5
        hashTable.checkAndResize();
        assertTrue(hashTable.getRecords()[0].getKey().equals("Jett Morrow"));
        assertTrue(hashTable.getRecords()[1].getKey().equals("Adam"));
        assertTrue(hashTable.getRecords()[10].getKey().equals("Adamasa"));
        assertTrue(hashTable.getRecords()[4].getKey().equals(
            "JENNA KANTOWSKI"));
        assertTrue(hashTable.getRecords()[15].getKey().equals("Hello"));
    }


    /**
     * test case for remove
     * 
     * @throws Exception
     *             exception
     */
    public void testRemove() throws Exception {
        hashTable.insert("Jett Morrow"); // should be index 0
        hashTable.insert("Adam"); // should be index 1
        hashTable.insert("Adamasa"); // should be index 0, so prob to 1, then to
                                     // 4
        // remove home index, now tombstone at 1
        assertTrue(hashTable.remove("Adam"));
        assertTrue(hashTable.getRecords()[1].getKey().equals("TOMBSTONE"));
        assertEquals(2, hashTable.getTableSize());

        // remove something that is a tombstone now
        assertFalse(hashTable.remove("Adam"));

        // remove key that DNE
        assertFalse(hashTable.remove("DNE"));
        assertEquals(2, hashTable.getTableSize());

        // remove Adamasa, tries 0 not it, prob 1 tombstone, checks 4 removes it
        assertTrue(hashTable.remove("Adamasa"));
        assertEquals(1, hashTable.getTableSize());

    }


    /**
     * test case for getRecords
     * 
     * @throws Exception
     *             exception
     */
    public void testGetRecords() throws Exception {
        hashTable.insert("Jett Morrow"); // should be index 0
        hashTable.insert("Adam"); // should be index 1
        hashTable.insert("Adamasa"); // should be index 0, so prob to 1, then
                                     // prob to 5
        assertEquals(10, hashTable.getRecords().length);
    }


    /**
     * test case for getTableCap
     * 
     * @throws Exception
     *             exception
     */
    public void testGetTableCap() throws Exception {
        assertEquals(10, hashTable.getTableCap());
    }


    /**
     * test case for getTableSize
     * 
     * @throws Exception
     *             exception
     */
    public void testGetTableSize() throws Exception {
        hashTable.insert("Jett Morrow"); // should be index 0
        hashTable.insert("Adam"); // should be index 1
        hashTable.insert("Adamasa"); // should be index 0, so prob to 1, then
                                     // prob to 5

        assertEquals(3, hashTable.getTableSize());
    }


    /**
     * method to test the Print method
     * 
     * @throws Exception
     *             exception
     */
    public void testPrint() throws Exception {
        // check it returns valid strings
        hashTable.insert("Jett Morrow"); // should be index 0
        hashTable.insert("Adam"); // should be index 1
        hashTable.insert("Adamasa"); // should be index 0, so prob to 1, then
                                     // prob to 4
        hashTable.remove("Adam");

        String one = "0: |Jett Morrow|";
        String two = "1: TOMBSTONE";
        String three = "4: |Adamasa|";

        String[] res = hashTable.print();
        assertTrue(one.equals(res[0]));
        assertTrue(two.equals(res[1]));
        assertTrue(three.equals(res[4]));

    }


    /**
     * Check out the sfold method
     *
     * @throws Exception
     *             either a IOException or FileNotFoundException
     */
    public void testSfold() throws Exception {
        assertTrue(Hash.h("a", 10000) == 97);
        assertTrue(Hash.h("b", 10000) == 98);
        assertTrue(Hash.h("aaaa", 10000) == 1873);
        assertTrue(Hash.h("aaab", 10000) == 9089);
        assertTrue(Hash.h("baaa", 10000) == 1874);
        assertTrue(Hash.h("aaaaaaa", 10000) == 3794);
        assertTrue(Hash.h("Long Lonesome Blues", 10000) == 4635);
        assertTrue(Hash.h("Long   Lonesome Blues", 10000) == 4159);
        assertTrue(Hash.h("long Lonesome Blues", 10000) == 4667);
    }
}
