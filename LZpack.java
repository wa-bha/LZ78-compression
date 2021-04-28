import java.io.*;

public class LZpack {
    public static final int BYTE_NUM_BITS = 8;
    public static final int BUFFER_NUM_BITS = 32;
    public static void main(String[] args) {
        start();
    }

    public static void start () {
        // try (BufferedReader reader = new BufferedReader(new FileReader("./compressed.txt"))) {
        // Use BufferedReader to read lines instead of bytes at a time
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            PrintStream writer = new PrintStream(System.out);
            
            int buffer = 0b0;
            int bufferIndex = 0;
            int phraseNum = 1;

            // Read the first character and store in local variable
            String l = reader.readLine();
            String[] tuple = l.split(" ");
            int parentPhraseIndex = Integer.parseInt(tuple[0]);
            int mismatchedValue = Integer.parseInt(tuple[1]);

            writer.write((byte)mismatchedValue);
            l = reader.readLine(); // Read next line
            // While we have no finished reading the inputted file
            while (l != null) {
                phraseNum++;
                // Get phrase details
                tuple = l.split(" ");
                parentPhraseIndex = Integer.parseInt(tuple[0]);
                mismatchedValue = Integer.parseInt(tuple[1]);

                int minBits = (int)Math.ceil(Math.log(phraseNum) / Math.log(2));
                
                // Pack phrase number
                parentPhraseIndex = parentPhraseIndex << (bufferIndex); // Shift into position
                buffer = maskBuffer(buffer, bufferIndex);
                buffer = buffer | parentPhraseIndex; // Append parent phrase index to buffer
                bufferIndex += minBits; // Update buffer index

                // Pack mismatched value
                if (mismatchedValue != -1) {
                    mismatchedValue = mismatchedValue << (bufferIndex); // Shift into position 
                    buffer = maskBuffer(buffer, bufferIndex);
                    buffer = buffer | mismatchedValue; // Append mismatched value to buffer
                    bufferIndex += BYTE_NUM_BITS; // Update buffer index
                }
                
                // Output two bytes if buffer is full enough i.e. 
                if (bufferIndex > (BUFFER_NUM_BITS - BYTE_NUM_BITS) || bufferIndex > (BUFFER_NUM_BITS - minBits)) {
                    for (int i = 1; i <= 2; i ++) {
                        // Output them to stdout
                        writer.write((byte)buffer); // Write left most byte
                        // Shift buffer along left to make space to load in more values
                        buffer = buffer >>> BYTE_NUM_BITS;
                        bufferIndex -= BYTE_NUM_BITS;
                    }
                }
                l = reader.readLine(); // Read next line
            }
            // Output rest of tuples remaining in buffer
            for (int i = 1; i <= 4; i ++) {
                // Output them to stdout
                writer.write((byte)buffer);
                // Shift buffer along left to make space to load in more values
                buffer = buffer >>> BYTE_NUM_BITS;
            }

            // Once there are no more characters to encode
            writer.close();
            reader.close();
            
        } catch (Exception e){
            System.out.println("Invalid piped in file");
            e.printStackTrace();
        }
    }

    private static int maskBuffer(int buffer, int bufferIndex) {
        int mask = (int)Math.pow(2, bufferIndex)-1;
        return buffer & mask; // Apply mask
    }

    // Pseudocode:

    // - Declare our byte buffer e.g. an int (default int can hold 4 bytes)
    //   to setup out our output
    // - Mask to ensure all 0
    // - Read initial line (tuple)
    // - Get parentPhraseIndex and misMatchedValue as Intigers so that
    //   we can perform binary operations on them
    // - Add initial mismathcedValue to buffer (parentPhraseIndex is implied
    //   to be 0 so don't need to write it)
    // - Read next line (tuple)
    // - LOOP until line is null
        // - Get parentPhraseIndex and misMatchedValue as Intigers so that
        //   we can perform binary operations on them
        // - Shift parentPhraseIndex and mismatchedValue right to line up
        //   with position in buffer
        // - Append parentPhraseIndex and mismatchedValue to left of Buffer
        // - IF our buffer is too full to fit the next tuple
            // - Output left most two bytes from buffer
            // - Shift remaining bytes 
        //                      
    // - END
}
