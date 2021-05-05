//  Name                    ID
//  Jesse Reyneke-Barnard   1351388
//  Bhavit Wadhwa           1516846

import java.io.*;

//
// LZ Pack
//
public class LZpack {
    public static final int BYTE_NUM_BITS = 8;
    public static final int BUFFER_NUM_BITS = 64;
    public static void main(String[] args) {
        start();
    }

    public static void start () {
        // Use BufferedReader to read lines instead of bytes at a time
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            PrintStream writer = new PrintStream(System.out);
            
            long buffer = 0b0;
            int bufferIndex = 0;
            int phraseNum = 1;

            // Read the first character and store in local variable
            String l = reader.readLine();
            String[] tuple = l.split(" ");
            long parentPhraseIndex = Long.parseLong(tuple[0]);
            long mismatchedValue = Long.parseLong(tuple[1]);

            //  Write initial character (phrase number 0 is implied so do not need to write it)
            writer.write((int)mismatchedValue);
            l = reader.readLine(); // Read next line
            // While we have not finished reading the inputted file
            while (l != null) {
                phraseNum++;
                // Get phrase details
                tuple = l.split(" ");
                parentPhraseIndex = Long.parseLong(tuple[0]);
                mismatchedValue = Long.parseLong(tuple[1]);

                int minBits = (int)Math.ceil(Math.log(phraseNum) / Math.log(2)); // Min bits for phrase num is log base2 of phraseNum
                
                // Pack phrase number
                parentPhraseIndex = parentPhraseIndex << ((BUFFER_NUM_BITS - bufferIndex) - minBits); // Shift into position
                buffer = maskBuffer(buffer, bufferIndex);
                buffer = buffer | parentPhraseIndex; // Append parent phrase index to buffer
                bufferIndex += minBits; // Update buffer index

                // Pack mismatched value
                mismatchedValue = mismatchedValue << ((BUFFER_NUM_BITS  - bufferIndex) - BYTE_NUM_BITS); // Shift into position 
                buffer = maskBuffer(buffer, bufferIndex);
                buffer = buffer | mismatchedValue; // Append mismatched value to buffer
                bufferIndex += BYTE_NUM_BITS; // Update buffer index
                
                // Output a byte as soon as buffer contains a completed packed byte
                while (bufferIndex >= BYTE_NUM_BITS) {
                    // Output them to stdout
                    byte b = (byte)(buffer >>> (BUFFER_NUM_BITS - BYTE_NUM_BITS));
                    writer.write(b); // Write left most byte

                    // Shift buffer along left to make space to load in and pack more values
                    buffer = buffer << BYTE_NUM_BITS;
                    bufferIndex -= BYTE_NUM_BITS;
                }
                l = reader.readLine(); // Read next line
            }
            // Output rest of packed tuples remaining in buffer after no more lines to pack
            while (bufferIndex > 0) {
                // Output them to stdout
                byte b = (byte)(buffer >>> (BUFFER_NUM_BITS - BYTE_NUM_BITS));
                writer.write(b); // Write left most byte
                // Shift buffer along left to make space to load in more values
                buffer = buffer << BYTE_NUM_BITS;
                bufferIndex -= BYTE_NUM_BITS;
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

    //
    // Mask buffer
    //
    private static long maskBuffer(long buffer, int bufferIndex) {
        // Create mask based on buffer index and number of bits within buffer
        long mask = (long)Math.pow(2, (BUFFER_NUM_BITS - bufferIndex))-1;
        mask = mask ^ (-1);
        return buffer & mask; // Return buffer with mask applied
    }
}
