// -------------------------------------------------------------------------

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * Main for Graph project (CS3114/CS5040 Fall 2023 Project 4).
 * Usage: java GraphProject <init-hash-size> <command-file>
 *
 * @author Jett Morrow & Adam Schantz
 * @version jettmorrow & adams03
 *
 */

// On my honor:
// - I have not used source code obtained from another current or
// former student, or any other unauthorized source, either
// modified or unmodified.
//
// - All source code and documentation used in my program is
// either my original work, or was derived by me from the
// source code published in the textbook for this course.
//
// - I have not discussed coding details about this project with
// anyone other than my partner (in the case of a joint
// submission), instructor, ACM/UPE tutors or the TAs assigned
// to this course. I understand that I may discuss the concepts
// of this program with other students, and that another student
// may help me debug my program so long as neither of us writes
// anything during the discussion or modifies any computer file
// during the discussion. I have violated neither the spirit nor
// letter of this restriction.

public class GraphProject {
    /**
     * The main program Entry point, Initializes CP with Controller to call
     * methods on
     * 
     * @param args
     *            Command line parameters {HashTableSize} {Command File}
     * 
     */
    public static void main(String[] args) {
        // input should be {HashTableSize} {Command File}
        if (args.length != 2) {
            throw new IllegalArgumentException(
                "Expected exactly 2 argument: {HashTableSize} {Command File}");
        }

        // check if hashTableSize is int and >= 1
        int hashTableSize;
        try {
            hashTableSize = Integer.parseInt(args[0]);

            if (hashTableSize < 1) {
                throw new IllegalArgumentException(
                    "{HashTableSize} must be >= 1");
            }
        }
        catch (NumberFormatException e) {
            throw new NumberFormatException(
                "{HashTableSize} must be a number >= 1");
        }

        // set up input Stream
        try (Scanner fileInput = new Scanner(new File(args[1]))) {
            // set up the output stream
            PrintWriter stdout = new PrintWriter(System.out);

            // create the Controller & interpreter
            Controller controller = new Controller(hashTableSize);
            CommandProcessor interpreter = new CommandProcessor(controller);

            // process all the commands in the input file
            interpreter.interpretAllLines(fileInput, stdout);
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }
}
