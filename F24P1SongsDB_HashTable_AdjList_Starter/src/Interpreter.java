import java.io.PrintWriter;
import java.util.Scanner;

// -------------------------------------------------------------------------
/**
 * A general-purpose API for line-oriented interpreters. It uses
 * {@link java.util.Scanner} objects to represent input channels
 * and {@link java.io.PrintWriter} objects to represent output channels.
 *
 * @author Stephen Edwards
 * @version 2023.09.15
 */
public interface Interpreter {
    // ~ Public Methods ........................................................

    // ----------------------------------------------------------
    /**
     * Calls interpretLine() repeatedly for every line in the provided
     * input.
     *
     * @param input
     *            The Scanner to read the command from.
     * @param output
     *            The destination to send any command output.
     */
    public void interpretAllLines(Scanner input, PrintWriter output);


    // ----------------------------------------------------------
    /**
     * Processes one line containing a command, and executes
     * the corresponding behavior.
     *
     * @param oneLine
     *            The Scanner to read the single line contents from.
     * @param output
     *            The destination to send any command output.
     * @param remainingInputLines
     *            A separate Scanner providing access
     *            to the remainder of the input stream, if the command spans
     *            multiple input lines.
     */
    public void interpretLine(
        Scanner oneLine,
        PrintWriter output,
        Scanner remainingInputLines);
}
