import student.TestCase;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Scanner;

/**
 * This class was designed to test the CommandProcessor
 *
 * @author Jett Morrow & Adam Schantz
 * @version jettmorrow & adams03
 */
public class CommandProcessorTest extends TestCase {
    // ~ Fields ................................................................
    private CommandProcessor processor;
    private Controller c;
    private Scanner scanner;
    private PrintWriter printer;

    /**
     * setUp method for the test class
     */
    public void setUp() {
        c = new Controller(10);
        processor = new CommandProcessor(c);
    }


    /**
     * test method for interpretAllLines
     */
    public void testInterpretAllLines() {
        scanner = new Scanner("P1_sampleInput.txt");
        printer = new PrintWriter(System.out, true);

        processor.interpretAllLines(scanner, printer);
    }
    // the rest of the CommandProcessor class is fully tested in the
    // GraphProject test class
}
