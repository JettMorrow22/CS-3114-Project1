import student.TestCase;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * This class was designed to test the GraphProject
 *
 * @author Jett Morrow & Adam Schantz
 * @version jettmorrow & adams03
 */
public class GraphProjectTest extends TestCase {
    // ----------------------------------------------------------
    /**
     * Read contents of a file into a string
     * 
     * @param path
     *            File name
     * @return the string
     * @throws IOException
     */
    static String readFile(String path) throws IOException {
        byte[] encoded = Files.readAllBytes(Paths.get(path));
        return new String(encoded);
    }


    /**
     * Set up the tests that follow.
     */
    public void setUp() {
        // Nothing needed yet
    }


    /**
     * This method is simply to get code coverage of the class declaration.
     */
    public void testQInit() {
        GraphProject it = new GraphProject();
        assertNotNull(it);
    }


    /**
     * test the invalid argument exception
     * 
     * @throws Exception
     *             exception
     */
    public void testArgLength() throws Exception {
        // if the arg length isnt 2
        String[] args = new String[1];
        args[0] = "10";
        try {
            GraphProject.main(args);
        }
        catch (IllegalArgumentException e) {
            assertEquals("Expected exactly 2 argument: {HashTableSize}"
                + " {Command File}", e.getMessage());
        }
        // if the hashTableSize is less than 1
        String[] args1 = new String[2];
        args1[0] = "0";
        try {
            GraphProject.main(args1);
        }
        catch (IllegalArgumentException e) {
            assertEquals("{HashTableSize} must be >= 1", e.getMessage());
        }
        // if the hashTableSize is not an int
        String[] args2 = new String[2];
        args2[0] = "s";
        try {
            GraphProject.main(args2);
        }
        catch (NumberFormatException e) {
            assertEquals("{HashTableSize} must be a number >= 1", e
                .getMessage());
        }

    }


    /**
     * Example 2: This method runs on a command sample IO file
     * You will write similar test cases
     * using different text files
     *
     * @throws Exception
     */
    public void testSampleIO() throws Exception {
        // Setting up all the parameters
        String[] args = new String[2];

        args[0] = "10";
        args[1] = "nonexistentfile.txt";

        // this should throw a FileNotFoundException
        GraphProject.main(args);

        args[1] = "P1_sampleInput.txt";

        // Invoke main method of our Graph Project
        GraphProject.main(args);

        // Actual output from your System console
        String actualOutput = systemOut().getHistory();

        // Expected output from file
        String expectedOutput = readFile(
            "solutionTestData/P1_sampleOutput.txt");

        // Compare the two outputs
        // TODO: uncomment the following line
        // once you have implemented your project
        assertFuzzyEquals(expectedOutput, actualOutput);

    }

}
