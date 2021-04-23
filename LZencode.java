import java.io.*;

public class LZencode {
    public static void main(String[] args) {
        start();
    }

    public static void start () {
        //Reader reads 
        try (Reader reader = new InputStreamReader(System.in)) {
            int b = reader.read();

            while (b != -1) {
                System.out.println(b);
                b = reader.read();
            }
            
        } catch (Exception e){
            System.out.println("Invalid piped in file");
        }
    }
}
