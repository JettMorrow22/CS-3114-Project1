import student.TestCase;

/**
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

        
        //check when adding to a tombstone
        hashTable.remove("Adamasa"); //remove and add tombstone to 4
        assertTrue(hashTable.insert("Jettt w")); //try and add to 4
        assertTrue(hashTable.getRecords()[4].getKey().equals("Jettt w"));
        
        //hit tombstone and find dup later
        hashTable.remove("Jettt w");
        hashTable.insert("Adamasa");
        
        //remove 0
        hashTable.remove("Jett Morrow");
        //this hits a tombstone, probes, end up finding dup so doesnt add it
        assertFalse(hashTable.insert("Adamasa"));
        
        
        
    }


    public void testFind() throws Exception {
        hashTable.insert("Jett Morrow"); // should be index 0
        hashTable.insert("Adam"); // should be index 1
        hashTable.insert("Adamasa"); // should be index 0, so prob to 1, then
                                     // prob to 5
        
        //in table
        assertTrue(hashTable.find("Jett Morrow").getKey().equals(
            "Jett Morrow"));
        assertTrue(hashTable.find("Adam").getKey().equals("Adam"));
        assertTrue(hashTable.find("Adamasa").getKey().equals("Adamasa"));
        
        //not in table
        assertNull(hashTable.find("JENNA"));
    }


    /**
     * I need to check about not re hashing tombstones
     * 
     * @throws Exception
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
        hashTable.checkAndResize();
        hashTable.insert("Kant");
        assertEquals(20, hashTable.getTableCap());
        assertEquals(5, hashTable.getTableSize());
        // check if everything got rehashed correclty
        assertTrue(hashTable.getRecords()[0].getKey().equals("Jett Morrow"));
        assertTrue(hashTable.getRecords()[1].getKey().equals("Adam"));
        assertTrue(hashTable.getRecords()[10].getKey().equals("Adamasa"));
        assertTrue(hashTable.getRecords()[7].getKey().equals("Jenna"));
        assertTrue(hashTable.getRecords()[3].getKey().equals("Kant"));
    }
    
    public void testCheckAndResizeTombstones() throws Exception {
        hashTable.insert("Jett Morrow"); // should be index 0
        hashTable.insert("Adam"); // should be index 1
        hashTable.insert("Adamasa"); // home = 0, index 4
        hashTable.insert("JENNA");
        
        //add some tombstones
        hashTable.remove("JENNA");
        
        //add records that will have to prob when rehashed
        hashTable.insert("Jettt wasd"); // should go to 1 on both
        hashTable.insert("JENNA KANTOWSKI");
        hashTable.checkAndResize();
        
        assertTrue(hashTable.getRecords()[0].getKey().equals("Jett Morrow"));
        assertTrue(hashTable.getRecords()[1].getKey().equals("Adam"));
        assertTrue(hashTable.getRecords()[10].getKey().equals("Adamasa"));
        assertTrue(hashTable.getRecords()[2].getKey().equals("Jettt wasd"));
        assertTrue(hashTable.getRecords()[4].getKey().equals("JENNA KANTOWSKI"));
    }
    
//    if (r == null || r == TOMBSTONE) {
//        continue;
//    }
    //I need T & F, F & T, TT, FF


    public void testRemove() throws Exception {
        hashTable.insert("Jett Morrow"); // should be index 0
        hashTable.insert("Adam"); // should be index 1
        hashTable.insert("Adamasa"); // should be index 0, so prob to 1, then to
                                     // 4
        //remove home index, now tombstone at 1
        assertTrue(hashTable.remove("Adam"));
        assertTrue(hashTable.getRecords()[1].getKey().equals("TOMBSTONE"));
        assertEquals(2, hashTable.getTableSize());
        
        //remove something that is a tombstone now
        assertFalse(hashTable.remove("Adam"));
        
        //remove key that DNE
        assertFalse(hashTable.remove("DNE"));
        assertEquals(2, hashTable.getTableSize());
        
        //remove Adamasa, tries 0 not it, prob 1 tombstone, checks 4 removes it
        assertTrue(hashTable.remove("Adamasa"));
        assertEquals(1, hashTable.getTableSize());
        
    }
    
    public void testGetRecords() throws Exception {
        hashTable.insert("Jett Morrow"); // should be index 0
        hashTable.insert("Adam"); // should be index 1
        hashTable.insert("Adamasa"); // should be index 0, so prob to 1, then
                                     // prob to 5
        assertEquals(10, hashTable.getRecords().length);
    }
    
    public void testGetTableCap() throws Exception {
        assertEquals(10, hashTable.getTableCap());
    }
    
    public void testGetTableSize() throws Exception {
        hashTable.insert("Jett Morrow"); // should be index 0
        hashTable.insert("Adam"); // should be index 1
        hashTable.insert("Adamasa"); // should be index 0, so prob to 1, then
                                     // prob to 5
        
        assertEquals(3, hashTable.getTableSize());
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
