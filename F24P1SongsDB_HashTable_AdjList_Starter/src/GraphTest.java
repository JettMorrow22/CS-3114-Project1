import student.TestCase;

/**
 * Graph Test class
 *
 * @author Jett Morrow & Adam Schantz
 * @version jettmorrow & adams03
 */
public class GraphTest extends TestCase {

    private Graph graph;

    /**
     * setup method for all test methods
     */
    public void setUp() {
        graph = new Graph();
    }


    /**
     * test method for adding a node
     */
    public void testAddNode() {
        assertEquals(graph.getnumberOfNodes(), 0);
        assertNull(graph.getVertex()[0]);
        assertEquals(0, graph.addNode());
        assertEquals(1, graph.addNode());

        assertEquals(2, graph.getnumberOfNodes());

        assertNotNull(graph.getVertex()[0]);
        assertNotNull(graph.getVertex()[1]);
    }


    /**
     * test method for adding and edge
     */
    public void testAddEdge() {
        graph.addNode();
        graph.addNode();

        // neither node has the other
        assertTrue(graph.addEdge(0, 1));
        assertTrue(graph.getVertex()[0].contains(1));
        assertTrue(graph.getVertex()[1].contains(0));

        // both nodes have the other
        assertFalse(graph.addEdge(0, 1));

        graph.addNode();
        assertTrue(graph.addEdge(0, 2));
        assertEquals(graph.getVertex()[0].getSize(), 2);
        assertTrue(graph.getVertex()[0].contains(2));
        assertTrue(graph.getVertex()[2].contains(0));
    }


    /**
     * test method for removing a edge
     */
    public void testRemoveEdge() {
        graph.addNode();
        graph.addNode();
        graph.addNode();
        graph.addNode(); // I have nodes 0 - 3

        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(0, 3);
        assertEquals(graph.getVertex()[0].getSize(), 3);
        assertEquals(graph.getVertex()[1].getSize(), 1);
        assertEquals(graph.getVertex()[2].getSize(), 1);
        assertEquals(graph.getVertex()[3].getSize(), 1);

        assertTrue(graph.removeEdge(0, 3));
        assertEquals(graph.getVertex()[0].getSize(), 2);
        assertEquals(graph.getVertex()[3].getSize(), 0);

    }


    /**
     * test method for removing a node
     */
    public void testRemoveNode() {
        graph.addNode();
        graph.addNode();
        graph.addNode();
        graph.addNode(); // I have nodes 0 - 3

        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(0, 3);

        // remove invalid node
        assertFalse(graph.removeNode(10));

        // remove valid node
        assertTrue(graph.removeNode(2));

        // adjacency is removed
        assertFalse(graph.getVertex()[0].contains(2));
        assertEquals(graph.getVertex()[0].getSize(), 2);

        // node is removed
        assertNull(graph.getVertex()[2]);

        // node count
        assertEquals(graph.getnumberOfNodes(), 3);

        // free spots
        assertEquals(graph.getFreeSpotIndex(), 1);
        assertEquals(graph.getFreeSpots()[0], 2);
        assertEquals(graph.checkFreeSpots(), 2);

    }


    /**
     * test method for adding to freed spots
     */
    public void testAddToFree() {
        graph.addNode();
        graph.addNode();
        graph.addNode();
        graph.addNode(); // I have nodes 0 - 3

        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(0, 3);

        graph.removeNode(2);

        assertNull(graph.getVertex()[2]);

        // add to free spot
        assertEquals(2, graph.addNode());

        assertEquals(0, graph.getFreeSpotIndex());
        assertEquals(-1, graph.getFreeSpots()[0]);

    }


    /**
     * test method for expanding
     */
    public void testExpand() {
        for (int x = 0; x < 50; x++) {
            graph.addNode();
        }
        // add some connections to first node
        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(0, 3);
        graph.addEdge(0, 4);

        // graph should be full
        assertEquals(graph.getnumberOfNodes(), 50);

        // add another should expand
        assertEquals(50, graph.addNode());

        assertEquals(51, graph.getnumberOfNodes());
        assertEquals(4, graph.getVertex()[0].getSize());

    }

}
