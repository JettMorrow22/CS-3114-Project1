import java.io.PrintWriter;

/**
 * 
 * The controller has the two hash and graph objects and calls appriorate
 * methods
 * including printing the output out
 * 
 * @author Jett Morrow & Adam Schantz
 * @version jettmorrow & adams03
 */
public class Controller {
    // main class that has hashs and graphs

    private Hash artists;
    private Hash songs;
    private Graph graph;

    /**
     * General constructor for Controller
     * initializes the two hashes and the graph
     * 
     * @param hashSize
     *            the length of the hashTable
     */
    public Controller(int hashSize) {
        artists = new Hash(hashSize);
        songs = new Hash(hashSize);
        graph = new Graph();
    }


    /**
     * Insert method
     * for both the artist and song hash table it checks if it needs to be
     * resized, then adds the param name into the hash table if not already
     * then prints the outcome of the insert
     * 
     * @param output
     *            the output file
     * @param artist
     *            the artist to be added
     * @param song
     *            the song to be addded
     */
    public void insert(PrintWriter output, String artist, String song) {

        // HASH TABLE
        // if added a node is also added to graph
        helpInsertHash(output, artist, artists, "Artist");
        helpInsertHash(output, song, songs, "Song");

        // add the edges between the two nodes just added
        int artistNode = artists.find(artist).getNode().getIndex();
        int songNode = songs.find(song).getNode().getIndex();
        if (!graph.addEdge(artistNode, songNode)) {
            output.println("|" + artist + "<SEP>" + song
                + "| duplicates a record already in the database.");
            output.flush();
        }
    }


    /**
     * method for inserting a key into a specified Hash
     * 
     * @param output
     *            to print
     * @param key
     *            the key to be added
     * @param table
     *            song or artist hash table
     * @param type
     *            to specify song or artist hash table
     */
    public void helpInsertHash(
        PrintWriter output,
        String key,
        Hash table,
        String type) {
        if (table.checkAndResize()) {
            output.println(type + " hash table size doubled.");
            output.flush();
        }

        Record record = new Record(key, null);
        if (table.insert(record)) {
            output.println("|" + key + "| is added to the " + type
                + " database.");
            output.flush();

            // Update the node index and add a new node in the graph
            table.find(key).setNode(new Node(graph.addNode()));
        }
    }


    /**
     * remove method specifically for the artist hash table
     * prints outcome of the remoce whether it was succesful or not
     * 
     * @param output
     *            output file
     * @param artist
     *            artist to to be removed
     */
    public void removeArtist(PrintWriter output, String artist) {
        // HASH
        Record record = artists.remove(artist); 
        if (record != null) {
            output.println("|" + artist
                + "| is removed from the Artist database.");

            // if found remove it from the graph
            graph.removeNode(record.getNode().getIndex());
        }
        else {
            output.println("|" + artist
                + "| does not exist in the Artist database.");
        }
        output.flush();
    }


    /**
     * remove method specifically for the song hash table
     * prints outcomes of the remove whether it was successful or not
     * 
     * @param output
     *            the output file
     * @param song
     *            the song to be removed
     */
    public void removeSong(PrintWriter output, String song) {
        Record record = songs.remove(song);
        if (record != null) {
            output.println("|" + song + "| is removed from the Song database.");

            // remove from graph
            graph.removeNode(record.getNode().getIndex());
        }
        else {
            output.println("|" + song
                + "| does not exist in the Song database.");
        }
        output.flush();
    }


    /**
     * Print method for the artist hash table
     * 
     * @param output
     *            the output file
     */
    public void printArtist(PrintWriter output) {
        String[] res = artists.print();

        for (String s : res) {
            output.println(s);
        }

        output.println("total artists: " + artists.getTableSize());
        output.flush();
    }


    /**
     * Print method for the song hash table
     * 
     * @param output
     *            the output file
     */
    public void printSong(PrintWriter output) {
        String[] res = songs.print();

        for (String s : res) {
            output.println(s);
        }

        output.println("total songs: " + songs.getTableSize());
        output.flush();
    }


    /**
     * Print method for the graph
     * 
     * @param output
     *            the output file
     */
    public void printGraph(PrintWriter output) {
        int numComp = graph.connectedComponents()[0];
        int largestComp = graph.connectedComponents()[1];
        output.println("There are " + numComp + " connected components");
        output.println("The largest connected component has " + largestComp
            + " elements");
        output.flush();
    }
}
