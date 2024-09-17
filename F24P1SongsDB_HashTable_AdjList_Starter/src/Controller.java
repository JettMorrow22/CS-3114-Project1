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

// insert {artist-name}<SEP>{song-name}
// Note that the characters <SEP> are literally a part of the string, and are
// used
// to separate the artist name from the song name. Check if {ar;st-name} appears
// in the artist hash table, and if it does not, add a node for that artist name
// to the graph, and store the string and node in the appropriate slot of the
// artist hash table. (A good approach is to create a small class to store the
// name and node together in a slot of the hash table.) Likewise, check if
// {song-name} appears in the song hash table, and if it does not, add a node
// for
// that song to the graph, and store the song name and graph node in the
// appropriate slot of the song hash table. You should print a message if the
// insert causes a hash table to expand in size.

// remove {artist|song}{name}
// Remove the specified artist or song name from the appropriate hash table, and
// remove that node from the graph. Report the outcome (whether the name
// appears,
// and whether it was successfully removed).
//
// print {artist|song|graph}
// Depending on the parameter value, you will print out either a complete
// listing
// of the artists contained in the database, or the songs, or else the graph.
// For
// artists or songs, print the graph: simply move sequentially through the
// associated hash table, retrieving the strings and printing them in the order
// encountered (along with the slot number where it appears in the hash table).
// Then print the total number of artists or total number of songs. When the
// graph
// option is given, you will do the following:
//
// • Compute connected components on the graph. (You will use the Union/FIND
// algorithm for this purpose, see OpenDSA Module 13.2.) Print out the number of
// connected components, and the number of nodes in the largest connected
// component.

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
        // insert artist in artists & song in songs

        // try and add artist and song to hash
        // if we add it then I need to add it to graph

        // HASH TABLE
        // if an insert happens show it, if it doubled hash show it
        // this for artists
        if (artists.checkAndResize()) {
            output.println("Artist hash table size doubled.");
            output.flush();
        }

        // create record for Artist
        Record artistRecord = new Record(artist, null);
        if (artists.insert(artistRecord)) {
            output.println("|" + artist + "|"
                + " is added to the Artist database.");
            output.flush();

            // if successfully added to table then we need to add node to graph
            int temp = graph.checkFreeSpots();
            Node node = new Node(temp == -1 ? graph.getnumberOfNodes() : temp);
            artists.find(artist).setNode(node);
            graph.addNode(node.getIndex());
        }

        // this for the songs
        if (songs.checkAndResize()) {
            output.println("Song hash table size doubled.");
            output.flush();
        }

        Record songRecord = new Record(song, null);
        if (songs.insert(songRecord)) {
            output.println("|" + song + "|"
                + " is added to the Song database.");
            output.flush();

            // if successfully added to table then we need to add node to graph
            int temp = graph.checkFreeSpots();
            Node node = new Node(temp == -1 ? graph.getnumberOfNodes() : temp);
            songs.find(song).setNode(node);
            graph.addNode(node.getIndex());

        }

        if (!graph.addEdge(artists.find(artist).getNode().getIndex(), songs
            .find(song).getNode().getIndex())) {
            output.println("|" + artist + "<SEP>" + song
                + "| duplicates a record already in the database.");
            output.flush();
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
        if (artists.remove(artist)) {
            output.println("|" + artist
                + "| is removed from the Artist database.");
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
        if (songs.remove(song)) {
            output.println("|" + song + "| is removed from the Song database.");
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
            // skip empty strings
            if (s == null)
                continue;
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
            if (s == null)
                continue;
            output.println(s);
        }

        output.println("total songs: " + songs.getTableSize());
        output.flush();
    }

    // create methods for everything, insert, delete, print
}
