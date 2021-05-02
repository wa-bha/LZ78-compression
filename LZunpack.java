import java.io.*;

public class LZunpack {
    public static final int BYTE_NUM_BITS = 8;
    public static final int BUFFER_MAX_BITS = 32;

    public static void main(String[] args) {
        start();
    }

    private static class InputFinished extends Exception {
        InputFinished() {
            super();
        }
    }

    private static int buffer = 0;
    private static int index = 0;

    private static int readNextBit() throws IOException, InputFinished {
        //IF buffer is empty, read a new byte and reset index
        if (index == 0) {
            buffer = System.in.read();
            if( buffer == -1 ) {
                throw new InputFinished();
            }
            //System.err.println( buffer );
            index = 8;
        }

        //ELSE we have bits remaining to read in the buffer
        index--;
        int bit = (buffer >>> index) & 1;
        return bit;
    }

    public static void start () {
        try {
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
            //Used for calculating bit size of phrase number
            int currentPhraseIndex = 1;
            
            try {
                //While we have not finished reading
                while (true) {
                    int parentPhraseNumber = 0;
                    int mismatchedValue = 0;

                    int phraseNumLength = (int)Math.ceil(Math.log(currentPhraseIndex) / Math.log(2));

                    //RETRIEVE parentPhraseNumber
                    for (int i = 0; i < phraseNumLength; i++) {
                        int nextBit = readNextBit();
                        //System.err.print( nextBit == 0 ? "0" : "1" );
                        parentPhraseNumber = parentPhraseNumber << 1;
                        parentPhraseNumber = parentPhraseNumber | nextBit;
                    }

                    //RETRIEVE mismatchedValue
                    for (int j = 0; j < 8; j++) {
                        int nextBit = readNextBit();
                        //System.err.print( nextBit == 0 ? "0" : "1" );
                        mismatchedValue = mismatchedValue << 1;
                        mismatchedValue = mismatchedValue | nextBit;
                    }
                    //System.err.println();

                    //Write next tuple to the writer
                    writeTuple(parentPhraseNumber, mismatchedValue, writer);
                    currentPhraseIndex++;
                }
            } catch (InputFinished e) {
                writer.flush();
                writer.close();
            }
        } catch (Exception ex) {
            System.out.println("Error processing: " + ex.getMessage() );
            ex.printStackTrace();
        }
    }

    //Writes tuple in correct format to the output stream
    public static void writeTuple(int phraseNumber, int mismatchedValue, BufferedWriter writer) throws IOException {
        writer.write(phraseNumber + " " + mismatchedValue + "\n");
    }
}