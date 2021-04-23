import java.io.*;

public class LZencode {
    public static void main(String[] args) {
        start();
    }

    public static void start () {
        //Reader reads 
        
        int charNum = 1;

        try (Reader reader = new InputStreamReader(System.in)) {
            char b = (char)reader.read();

            while (b != (char)-1) {
                System.out.println(charNum + ": " + b);
                b = (char)reader.read();
                charNum++;
            }
            
        } catch (Exception e){
            System.out.println("Invalid piped in file");
        }
    }
}
