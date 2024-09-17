import student.TestCase;

/**
 * Node Test class
 *
 * @author Jett Morrow & Adam Schantz
 * @version jettmorrow & adams03
 */
public class NodeTest extends TestCase {
    private Node node;

    /**
     * setup method for test class
     */
    public void setUp() {
        node = new Node(5);
    }


    /**
     * test get and set for index
     */
    public void testGetIndex() {
        assertEquals(node.getIndex(), 5);
        node.setIndex(4);
        assertEquals(node.getIndex(), 4);

    }

}
