import student.TestCase;
import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * test class for Controller
 * 
 * @author Jett Morrow & Adam Schantz
 * @version jettmorrow & adams03
 */
public class ControllerTest extends TestCase {

    private Controller controller;
    private PrintWriter printer;
    private StringWriter writer;

    /**
     * setUp method for the test class
     */
    public void setUp() {
        controller = new Controller(20);
        writer = new StringWriter();
        printer = new PrintWriter(writer);
    }


    /**
     * test case for insert method
     */
    public void testInsertHash() {
        controller.insert(printer, "Artist1", "Song1");
        String output = writer.toString();

        assertTrue(output.contains("|Artist1| is added to "
            + "the Artist database."));
        assertTrue(output.contains("|Song1| is added to the Song database."));

        // should get resized
        controller.insert(printer, "Artist2", "Song2");
        // output = writer.toString();
        // assertTrue(output.contains("Song hash table size doubled."));

        // add a duplicate
        controller.insert(printer, "Artist1", "Song1");
        writer.getBuffer().setLength(0); // clear the string
        output = writer.toString();
        assertFalse(output.contains("|Artist1| is added "
            + "to the Artist database."));
        assertFalse(output.contains("|Song1| is added to the Song database."));

    }


    /**
     * test case for removeArtist method
     */
    public void testRemoveArtist() {

        controller.insert(printer, "treaty", "stop and stare");
        controller.removeSong(printer, "treaty");

    }


    /**
     * test case for removeSong method
     */
    public void testRemoveSongArtist() {
        controller.insert(printer, "treaty", "stop and stare");
        controller.removeSong(printer, "stop and stare");
        controller.removeSong(printer, "jett");
        controller.removeArtist(printer, "jett");

    }


    /**
     * test case for printArtist method
     */
    public void testPrintArtist() {
        controller.printArtist(printer);
        controller.insert(printer, "treaty", "stop and stare");
        controller.printArtist(printer);
    }
}
