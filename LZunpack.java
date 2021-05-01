import java.io.*;

public class LZunpack {
    public static final int BYTE_NUM_BITS = 8;
    public static final int BUFFER_MAX_BITS = 32;

    public static void main(String[] args) {
        start();
    }

    public static void start () {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

            //Used for calculating bit size of phrase number
            int currentPhraseIndex = 0;

            
            int nextInputByte = 0;
            int parentPhraseNumber = 0;
            int mismatchedValue = 0;

            int buffer = 0;
            int remainingBitsInBuffer = 32;
            
            //For the FIRST CASE, read 8 bits, write 0 + "8-bit mismatched value"
            nextInputByte = System.in.read();
            System.err.println(nextInputByte);
            mismatchedValue = mismatchedValue | nextInputByte;
            writeTuple(0, mismatchedValue, writer);
            currentPhraseIndex++;

            
            nextInputByte = System.in.read();
            System.err.println(nextInputByte);
            buffer = buffer | nextInputByte;
            System.err.println(buffer);

            // //While there are still bytes to read
            // while (nextInputByte != -1) {
                

            //     nextInputByte = System.in.read();


            //     buffer = buffer << 16;
            //     buffer = buffer | reader.read();

            //     System.err.println(buffer);

            //     // //While we are not at the end of the buffer
            //     // while (currentPhraseIndex < BUFFER_MAX_BITS) {

            //     // }
            // }

            // //While we have not finished reading
            // while (nextBit != -1) {

            //     //Get next tuple and store in local variable
            //     int phraseNumLength = (int)Math.ceil(Math.log(currentPhraseIndex) / Math.log(2));

            //     //RETRIEVE parentPhraseNumber
            //     for (int i = 0; i < phraseNumLength; i++) {
            //         nextBit = System.in.read();
            //         parentPhraseNumber = parentPhraseNumber & nextBit;
            //         parentPhraseNumber = parentPhraseNumber << 1;
            //     }

            //     //RETRIEVE mismatchedValue
            //     for (int j = 0; j < 8; j++) {
            //         nextBit = System.in.read();
            //         mismatchedValue = mismatchedValue & nextBit;
            //         mismatchedValue = mismatchedValue << 1;
            //     }
                
            //     //Write next tuple to the writer
            //     writeTuple(parentPhraseNumber, mismatchedValue, writer);
            //     currentPhraseIndex++;
            //     nextBit = System.in.read();
            // }

            // Once standard input has been exhausted
            writer.flush();
            writer.close();
            
        } catch (Exception e){
            System.out.println("Invalid piped in file");
            e.printStackTrace();
        }
    }

    //Writes tuple in correct format to the output stream
    public static void writeTuple(int phraseNumber, int mismatchedValue, BufferedWriter writer) {
        try {
            writer.write(phraseNumber + " " + mismatchedValue + "\n");
        } catch (Exception e){
            System.out.println("Writer error");
            e.printStackTrace();
        }
    }
}