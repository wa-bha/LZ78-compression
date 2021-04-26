import java.io.*;

public class LZencode {
    public static void main(String[] args) {
        start();
    }

    public static void start () {

        // try (Reader reader = new InputStreamReader(System.in)) {
        try (Reader reader = new FileReader("./MobyDick.txt")) {
            MultiWayTrie trie = new MultiWayTrie();

            //Read the first character and store in local variable
            char b = (char)reader.read();

            //While we have no finished reading the inputted file
            while (b != (char)-1) {
                // System.out.println(charNum + ": " + b);
                
                MultiWayTrie current = trie.insert(b);
                int parentIndex = 0;
                
                //While insert does not return null, read the next character and insert it into the trie               
                while (current != null) {
                    b = (char)reader.read();
                    parentIndex = current.getParentPhraseIndex();
                    current = current.insert(b);
                }

                //Print tuple (parentPhraseIndex, Mismathced Value) to stdout
                System.out.println(parentIndex + " " + (int)b);

                b = (char)reader.read();
            }
            
        } catch (Exception e){
            System.out.println("Invalid piped in file");
            e.printStackTrace();
        }
    }
}
