import student.TestCase;

/**
 * DoubleLL Test class
 *
 * @author Jett Morrow & Adam Schantz
 * @version jettmorrow & adams03
 */
public class DoubleLLTest extends TestCase {
    private DoubleLL list;

    /**
     * setUp method called before each test method
     */
    public void setUp() {
        list = new DoubleLL();

    }


    /**
     * test method for addingToEnd
     */
    public void testAddToEnd() {
        list.addToEnd(1);
        assertEquals(list.getSize(), 1);
        assertEquals(list.getHead().getData(), 1);
        assertEquals(list.getTail().getData(), 1);

        list.addToEnd(2);
        assertEquals(list.getSize(), 2);
        assertEquals(list.getHead().getData(), 1);
        assertEquals(list.getTail().getData(), 2);

        list.addToEnd(3);
        list.addToEnd(4);
        list.addToEnd(5);

        assertTrue(list.contains(1));
        assertTrue(list.contains(2));
        assertTrue(list.contains(3));
        assertTrue(list.contains(4));
        assertTrue(list.contains(5));
        assertTrue(list.getSize() == 5);
        assertEquals(list.getHead().getData(), 1);
        assertEquals(list.getTail().getData(), 5);
    }


    /**
     * test method for removing from the middle of the list
     */
    public void testRemoveMiddle() {
        list.addToEnd(0);
        list.addToEnd(1);
        list.addToEnd(2);
        list.addToEnd(3);
        list.addToEnd(4);
        list.addToEnd(5);

        assertTrue(list.remove(2));
        assertFalse(list.contains(2));
        assertTrue(list.contains(0));
        assertTrue(list.contains(5));
        assertTrue(list.getHead().getData() == 0);
        assertTrue(list.getTail().getData() == 5);
        assertEquals(list.getSize(), 5);

        // remove nothing
        assertFalse(list.remove(354));
    }


    /**
     * test method for removing the head
     */
    public void testRemoveHead() {
        list.addToEnd(0);
        list.addToEnd(1);
        list.addToEnd(2);
        list.addToEnd(3);
        list.addToEnd(4);
        list.addToEnd(5);

        assertTrue(list.remove(0));
        assertFalse(list.contains(0));
        assertTrue(list.contains(5));
        assertTrue(list.getHead().getData() == 1);
        assertTrue(list.getTail().getData() == 5);
        assertEquals(list.getSize(), 5);

    }


    /**
     * test method for removing the tail
     */
    public void testRemoveTail() {
        list.addToEnd(0);
        list.addToEnd(1);
        list.addToEnd(2);
        list.addToEnd(3);
        list.addToEnd(4);
        list.addToEnd(5);

        assertTrue(list.remove(5));
        assertFalse(list.contains(5));
        assertTrue(list.getHead().getData() == 0);
        assertTrue(list.getTail().getData() == 4);
        assertEquals(list.getSize(), 5);
    }


    /**
     * test method to test removing when there is only one object
     */
    public void testOne() {
        list.addToEnd(0);
        assertTrue(list.remove(0));
        assertNull(list.getHead());
        assertNull(list.getTail());
        assertEquals(0, list.getSize());
    }


    /**
     * test method to test random methods in DLLNode
     */
    public void testRandom() {
        list.addToEnd(0);
        list.addToEnd(1);
        list.addToEnd(2);

        assertNull(list.getHead().getPrev());
        assertEquals(list.getHead().getNext().getData(), 1);
        list.getHead().setData(3);
        assertEquals(list.getHead().getData(), 3);
    }
}
