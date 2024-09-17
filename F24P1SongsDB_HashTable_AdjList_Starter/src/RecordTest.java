import student.TestCase;

/**
 * record test class
 * 
 * @author Jett Morrow & Adam Schantz
 * 
 * @version jettmorrow & adams03
 */
public class RecordTest extends TestCase {

    private Record record;

    /**
     * setup method for record Test
     */
    public void setUp() {
        record = new Record("Hello", new Node(5));
    }


    /**
     * test method for all the methods in record
     */
    public void testAll() {
        assertTrue(record.getKey().equals("Hello"));

        assertTrue(record.getNode().getIndex() == 5);
        assertFalse(record.getNode().getIndex() == 4);
        record.setNode(new Node(1));
        assertTrue(record.getNode().getIndex() == 1);

    }
}
