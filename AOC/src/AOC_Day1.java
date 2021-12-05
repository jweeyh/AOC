import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;
import java.io.IOException;
import java.io.StringReader;


public class AOC_Day1 {
    public static void main(String[] args) throws java.io.IOException {
        int no_of_increases, current_number;
        no_of_increases = 0;
        current_number = 0;
        File fn = new File("C:\\Users\\jweey\\OneDrive\\Documents\\Exciting Stuff\\hi.txt");
        BufferedReader reader = new BufferedReader(new FileReader(fn));
        String line = reader.readLine();
        while (line != null){
            int a = Integer.parseInt(line);
            if (current_number == 0){
            } else if (a > current_number){
                no_of_increases++;
            }
            current_number = a;
            line = reader.readLine();
        }
        System.out.println(no_of_increases);
    }
}


