import java.io.*;
import java.util.List;
import java.util.ArrayList;

//
// LZ decode
//
public class LZdecode {
    public static void main(String[] args) {
        start();
    }

    public static void start () {
        // Use BufferedReader to read lines instead of bytes at a time
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            PrintStream writer = new PrintStream(System.out); // Use PrintStream to output only least significant byte of ints

            List<int[]> dictionary = new ArrayList<int[]>(); // Use ArrayList to make our dictionary dynamic in size

            // Read the first character and store in local variable
            String l = reader.readLine();

            // While we have not finished reading the inputted file
            while (l != null) {
                String[] tuple = l.split(" "); // Get tuple from line
                // Convert to ints
                int parentPhraseIndex = Integer.parseInt(tuple[0]);
                int misMatchedValue = Integer.parseInt(tuple[1]);

                // Add to dictionary
                dictionary.add(new int[] { parentPhraseIndex, misMatchedValue });

                // Build output stack from dictionary
                List<Integer> outStack = new ArrayList<Integer>();
                while (parentPhraseIndex != 0) { // Keep adding until we get to 0th mismathced value
                    outStack.add(misMatchedValue);
                    // Get next mismatched value and parent phrase index
                    misMatchedValue = dictionary.get(parentPhraseIndex-1)[1];
                    parentPhraseIndex = dictionary.get(parentPhraseIndex-1)[0];
                }
                outStack.add(misMatchedValue); // Add final 0th mismatched value
                

                // Write output stack to stdout
                for (int i = outStack.size()-1; i >= 0; i--) {
                    if (outStack.get(i) != (byte)65535) { // Check for null char
                        writer.write(outStack.get(i));
                    }
                }

                l = reader.readLine(); // Read next line
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
}
