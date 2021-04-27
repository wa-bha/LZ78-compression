import java.io.*;

public class LZunpack {
    public static void main(String[] args) {
        start();
    }

    public static void start () {

    }
}

/* 
    Psuedocode:
    Declare variables to temporarily store the currentPhraseIndex and currentMismatched value
    Declare a 32 bit integer buffer to be able to use as a stack 
        - pop off into variables and print
        - push onto buffer once there are no more characters
    Declare an current index location for finding where you are on the buffer

    Retrieve standard output, in the form of a stream of bytes
    
    Seperate bytes into parentPhraseIndex and mismatched values (the tuples)
        - First case is only going to be a byte (8 bits only) - phrase index is implied to be 0
        After that:
        - Calculate number of bits required for the phrase index
        - Use this to seperate into pop off the next individual item in the tuple
        - Pop off into variables

    Writes the variables in the correct format to standard output (adding " " and nextLines)

*/