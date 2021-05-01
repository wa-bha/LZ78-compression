import java.io.*;

public class LZunpack {
    public static final int BYTE_NUM_BITS = 8;
    public static final int BUFFER_NUM_BITS = 32;

    public static void main(String[] args) {
        start();
    }

    public static void start () {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

            int buffer = 0;
            int bufferIndex = 0;
            int readerIndex = 0;

            int phraseNumber;
            int mismatchedValue;
            
            //For the first case, read ONLY the first 8 bits, print 0 + "8-bit phraseNumber"
            for (int j = 0; j < 8; j++) {
                reader.read();
                
                //THIS PROCESS

            }
            writer.write("0 " + mismatchedValue)

            //while we are not at the end of byte stream
                //Fill buffer with bytes

                //while 

                //Calculate how many bits need: second one will be 9
                //





            //Read in a stream of bytes

            // Once standard input has been exhausted
            writer.flush();
            writer.close();
            reader.close();
            
        } catch (Exception e){
            System.out.println("Invalid piped in file");
            e.printStackTrace();
        }
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