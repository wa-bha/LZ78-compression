import java.io.*;

public class LZpack {
    public static final int BYTE_NUM_BITS = 8;
    public static final int BUFFER_NUM_BITS = 32;
    public static void main(String[] args) {
        start();
    }

    public static void start () {
        try (BufferedReader reader = new BufferedReader(new FileReader("./compressed.txt"))) {
        // Use BufferedReader to read lines instead of bytes at a time
        // try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            PrintStream writer = new PrintStream(System.out);
            
            int buffer = 0b0;
            int bufferIndex = BUFFER_NUM_BITS;
            int phraseNum = 1;

            // Read the first character and store in local variable
            String l = reader.readLine();
            String[] tuple = l.split(" ");
            int parentPhraseIndex = Integer.parseInt(tuple[0]);
            int mismatchedValue = Integer.parseInt(tuple[1]);
            // Shift left
            // mismatchedValue = mismatchedValue << BUFFER_NUM_BITS - bufferIndex - BYTE_NUM_BITS;
            // buffer = buffer | mismatchedValue; // Append mismatched value to buffer
            // bufferIndex += BYTE_NUM_BITS; // Update buffer index

            writer.write(mismatchedValue);
            phraseNum++;
            l = reader.readLine(); // Read next line
            // While we have no finished reading the inputted file
            while (l != null) {
                // Get phrase details
                tuple = l.split(" ");
                parentPhraseIndex = Integer.parseInt(tuple[0]);
                mismatchedValue = Integer.parseInt(tuple[1]);
                int minBits = (int)Math.ceil(Math.log(phraseNum) / Math.log(2));
                
                // Pack phrase
                parentPhraseIndex = parentPhraseIndex << BUFFER_NUM_BITS - bufferIndex; // Shift left

                int mask = (int)Math.pow(2, BUFFER_NUM_BITS-(BUFFER_NUM_BITS-bufferIndex))-1;
                mask = mask ^ (-1); // Flip bits
                buffer = buffer & mask; // Apply mask

                buffer = buffer | parentPhraseIndex; // Append parent phrase index to buffer
                bufferIndex -= minBits; // Update buffer index
                // Shift left
                mismatchedValue = mismatchedValue << BUFFER_NUM_BITS - bufferIndex;

                mask = (int)Math.pow(2, BUFFER_NUM_BITS-(BUFFER_NUM_BITS-bufferIndex))-1;
                mask = mask ^ (-1); // Flip bits
                buffer = buffer & mask; // Apply mask

                buffer = buffer | mismatchedValue; // Append mismatched value to buffer
                bufferIndex -= BYTE_NUM_BITS; // Update buffer index
                
                // Output two bytes if buffer is full enough i.e. 
                if (bufferIndex < BYTE_NUM_BITS) {
                    for (int i = 1; i <= (BUFFER_NUM_BITS/BYTE_NUM_BITS)/2; i ++) {
                        // Get output bytes from buffer
                        int b = (buffer >> BYTE_NUM_BITS*3);
                        // Output them to stdout
                        writer.write(b);
                        // Shift buffer along left to make space to load in more values
                        buffer = buffer << BYTE_NUM_BITS;
                    }
                    bufferIndex = BUFFER_NUM_BITS/2;
                }
                l = reader.readLine(); // Read next line
                phraseNum++;
            }
            // Output rest of tuples remaining in buffer
            for (int i = 1; i <= BUFFER_NUM_BITS/BYTE_NUM_BITS; i ++) {
                // Get output bytes from buffer
                int b = (buffer >> BYTE_NUM_BITS*3);
                // Output them to stdout
                writer.write(b);
                // Shift buffer along left to make space to load in more values
                buffer = buffer << BYTE_NUM_BITS;
            }

            // Once there are no more characters to encode
            writer.flush();
            writer.close();
            reader.close();
            
        } catch (Exception e){
            System.out.println("Invalid piped in file");
            e.printStackTrace();
        }
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
