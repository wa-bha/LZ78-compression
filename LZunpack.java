//  Name                    ID
//  Jesse Reyneke-Barnard   1351388
//  Bhavit Wadhwa           1516846

import java.io.*;

//
// LZ Unpack
//
public class LZunpack {
    public static final int BYTE_NUM_BITS = 8;
    private static int buffer = 0;
    private static int index = 0;

    public static void main(String[] args) {
        start();
    }

    private static class InputFinished extends Exception {
        InputFinished() {
            super();
        }
    }

    // Returns a single bit using an 8 bit buffer
    private static int readNextBit() throws IOException, InputFinished {
        // IF buffer is empty, read a new byte and reset index
        if (index == 0) {
            buffer = System.in.read();
            if (buffer == -1){
                throw new InputFinished();
            }
            index = BYTE_NUM_BITS;
        }
        // ELSE we have bits remaining to read in the buffer
        index--;
        int bit = (buffer >>> index) & 1;
        return bit;
    }

    public static void start () {
        try {
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
            // Used for calculating bit size of phrase number
            int currentPhraseIndex = 1;
            
            try {
                // While we have not finished reading
                while (true) {
                    int parentPhraseNumber = 0;
                    int mismatchedValue = 0;

                    // Calculate the number of bit required for the next phrase number
                    int phraseNumLength = (int)Math.ceil(Math.log(currentPhraseIndex) / Math.log(2));

                    // RETRIEVE parentPhraseNumber
                    for (int i = 0; i < phraseNumLength; i++) {
                        int nextBit = readNextBit();
                        //System.err.print( nextBit == 0 ? "0" : "1" );
                        parentPhraseNumber = parentPhraseNumber << 1;
                        parentPhraseNumber = parentPhraseNumber | nextBit;
                    }

                    // RETRIEVE mismatchedValue
                    for (int j = 0; j < BYTE_NUM_BITS; j++) {
                        int nextBit = readNextBit();
                        //System.err.print( nextBit == 0 ? "0" : "1" );
                        mismatchedValue = mismatchedValue << 1;
                        mismatchedValue = mismatchedValue | nextBit;
                    }

                    // Write next tuple to the writer
                    writeTuple(parentPhraseNumber, mismatchedValue, writer);
                    currentPhraseIndex++;
                }

            // Catch when byte input is finished and flsuh written tuples
            } catch (InputFinished e) {
                writer.flush();
                writer.close();
            }
        } catch (Exception ex) {
            System.out.println("Error processing: " + ex.getMessage() );
            ex.printStackTrace();
        }
    }

    // Writes tuple in the correct format to the output stream
    public static void writeTuple(int phraseNumber, int mismatchedValue, BufferedWriter writer) throws IOException {
        writer.write(phraseNumber + " " + mismatchedValue + "\n");
    }
}