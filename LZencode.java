import java.io.*;

public class LZencode {
    public static void main(String[] args) {
        start();
    }

    public static void start () {
        try {
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

            MultiWayTrie trie = new MultiWayTrie();

            // Read the first byte and store in local variable
            // Use System.in.read instead of wrapping InputSreamReader, to only read one byte at a time.
            int b = System.in.read();

            // While we have no finished reading the inputted file
            while (b != -1) {
                MultiWayTrie current = trie.insert(b);
                int parentIndex = 0;
                
                // While insert does not return null, read the next character and insert it into the trie               
                while (current != null) {
                    int tmp = System.in.read();
                    if (tmp == -1) {
                        break;
                    }
                    b = tmp;
                    parentIndex = current.getParentPhraseIndex();
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
