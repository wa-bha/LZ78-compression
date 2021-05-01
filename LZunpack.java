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

            int currentPhraseIndex = 0;

            int nextBit;
            int parentPhraseNumber;
            int mismatchedValue;
            
            //For the FIRST CASE, read ONLY the first 8 bits, print 0 + "8-bit phraseNumber"
            for (int j = 0; j < 8; j++) {
                nextBit = reader.read();
                mismatchedValue = mismatchedValue & nextBit;
                mismatchedValue = mismatchedValue <<< 1;
            }
            writeTuple(0, mismatchedValue);
            currentPhraseIndex++;

            nextBit = reader.read();

            //While we have not finished reading
            while (nextBit != null) {

                //Get next tuple and store in local variable
                int phraseNumLength = (int)Math.ceil(Math.log(currentPhraseIndex) / Math.log(2));

                //RETRIEVE parentPhraseNumber
                for (int i = 0; i < phraseNumLength; i++) {
                    nextBit = reader.read();
                    parentPhraseNumber = parentPhraseNumber & nextBit;
                    parentPhraseNumber = parentPhraseNumber <<< 1;
                }

                //RETRIEVE mismatchedValue
                for (int j = 0; j < 8; j++) {
                    nextBit = reader.read();
                    mismatchedValue = mismatchedValue & nextBit;
                    mismatchedValue = mismatchedValue <<< 1;
                }
                
                //Write next tuple to the writer
                writeTuple(parentPhraseNumber, mismatchedValue);
                currentPhraseIndex++;
                nextBit = reader.read();
            }

            // Once standard input has been exhausted
            writer.flush();
            writer.close();
            reader.close();
            
        } catch (Exception e){
            System.out.println("Invalid piped in file");
            e.printStackTrace();
        }
    }

    //Writes tuple in correct format to the output stream
    public void writeTuple(int phraseNumber, int mismatchedValue) {
        writer.write(phraseNumber + " " + mismatchedValue + "\n")
    }
}