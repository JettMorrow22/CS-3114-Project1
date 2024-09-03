
public class Controller {
    //main class that has hashs and graphs
    
//  insert {artist-name}<SEP>{song-name}
//  Note that the characters <SEP> are literally a part of the string, and are used
//  to separate the artist name from the song name. Check if {ar;st-name} appears
//  in the artist hash table, and if it does not, add a node for that artist name
//  to the graph, and store the string and node in the appropriate slot of the
//  artist hash table. (A good approach is to create a small class to store the
//  name and node together in a slot of the hash table.) Likewise, check if
//  {song-name} appears in the song hash table, and if it does not, add a node for
//  that song to the graph, and store the song name and graph node in the
//  appropriate slot of the song hash table. You should print a message if the
//  insert causes a hash table to expand in size.
  
//  remove {artist|song}{name}
//  Remove the specified artist or song name from the appropriate hash table, and
//  remove that node from the graph. Report the outcome (whether the name appears,
//  and whether it was successfully removed).
//
//  print {artist|song|graph}
//  Depending on the parameter value, you will print out either a complete listing
//  of the artists contained in the database, or the songs, or else the graph. For
//  artists or songs, print the graph: simply move sequentially through the
//  associated hash table, retrieving the strings and printing them in the order
//  encountered (along with the slot number where it appears in the hash table).
//  Then print the total number of artists or total number of songs. When the graph
//  option is given, you will do the following:
//
//  • Compute connected components on the graph. (You will use the Union/FIND
//    algorithm for this purpose, see OpenDSA Module 13.2.) Print out the number of
//    connected components, and the number of nodes in the largest connected
//    component.
    
    
    private Hash artist;
    private Hash songs;

    
    public Controller(int hashSize) {
        artist = new Hash(hashSize);
        songs = new Hash(hashSize);
    }
    
    //create methods for everything, insert, delete, print
}
