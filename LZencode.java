//  Name                    ID
//  Jesse Reyneke-Barnard   1351388
//  Bhavit Wadhwa           1516846

import java.io.*;

//
// LZ Encode
//
public class LZencode {
    public static void main(String[] args) {
        start();
    }

    public static void start () {
        try {
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
            MultiWayTrie trie = new MultiWayTrie();

            // Use System.in.read instead of wrapping InputSreamReader, to only read one byte at a time.
            int b = System.in.read(); // Read the first byte and store in local variable

            // While we have not finished reading the inputted file
            while (b != -1) {
                MultiWayTrie current = trie.insert(b); // Populate trie
                int parentIndex = 0;
                
                // While insert does not return null, read the next character and insert it into the returned trie node
                while (current != null) {
                    int tmp = System.in.read();
                    if (tmp == -1) {
                        break;
                    }
                    b = tmp;
                    parentIndex = current.getPhraseIndex();
                    current = current.insert(b);
                }

                // Print tuple (parentPhraseIndex, Mismathced Value) to stdout
                writer.write(parentIndex + " " + b + "\n");

                b = System.in.read();
            }

            // Once there are no more characters to encode
            writer.flush();
            writer.close();
            
        } catch (Exception e){
            System.out.println("Invalid piped in file");
            e.printStackTrace();
        }
    }
}
