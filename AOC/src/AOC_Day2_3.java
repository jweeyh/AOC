import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;


public class AOC_Day2_3 {
    public static void main(String[] args) throws IOException {
        File file1 = new File("C:\\Users\\jweey\\OneDrive\\Documents\\Exciting Stuff\\hi.txt");
        File file2 = new File("C:\\Users\\jweey\\OneDrive\\Documents\\Exciting Stuff\\AOC2.txt");
        File file3 = new File("C:\\Users\\jweey\\OneDrive\\Documents\\Exciting Stuff\\AOC3.txt");
        BufferedReader reader = new BufferedReader(new FileReader(file3));
        int O2 = Integer.parseInt(some.lifeSupportRating(file3, "O2"), 2);
        int CO2 = Integer.parseInt(some.lifeSupportRating(file3, "CO2"), 2);
        System.out.println(O2);
        System.out.println(CO2);
        System.out.println(CO2 * O2);

    }
}
class some {
    public static int numOfLines (File file) throws IOException{
        int num = 0;
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String line = reader.readLine();
        while(line != null){
            num++;
            line = reader.readLine();
        }
        reader.close();
        return num;
    }
    public static String[] linesToStrArray (File file) throws IOException{
        String[] strArr = new String[numOfLines(file)];
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String line = reader.readLine();
        for(int i = 0; i < numOfLines(file); i++){
            strArr[i] = line;
            line = reader.readLine();
        }
        reader.close();
        return strArr;
    }
    public static int MethodOne(BufferedReader reader) throws IOException {
        int no_of_increases, current_a, current_b, current_c, current_d;
        String current_d_string;
        no_of_increases = 0;
        current_a = Integer.parseInt(reader.readLine());
        current_b = Integer.parseInt(reader.readLine());
        current_c = Integer.parseInt(reader.readLine());
        current_d_string = reader.readLine();

        while (current_d_string != null ) {
            current_d = Integer.parseInt(current_d_string);
            if (current_a < current_d) {
                no_of_increases ++;
            }
            current_a = current_b;
            current_b = current_c;
            current_c = current_d;
            current_d_string = reader.readLine();
        }
        return no_of_increases;
    }
    public static int depthDistance(BufferedReader reader) throws IOException{
        int depth = 0;
        int distance = 0;
        int aim = 0;
        String line = reader.readLine();
        while(line != null){
            String[] s = line.split(" ");
            String direction = s[0];
            int mag = Integer.parseInt(s[1]);
            if (direction.equals("up") ){
                aim = aim - mag;
            } else if (direction.equals("down")){
                aim = aim + mag;
            } else  if (direction.equals("forward")){
                distance = distance + mag;
                depth = depth + (aim * mag);
            }
            line = reader.readLine();
        }
        return depth * distance;
    }
    public static int powerConsumption(File file) throws IOException{
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String line = reader.readLine();
        int[] number = new int[12];
        int[] numberPrime = new int[12];
        for (int i = 0; i < 12; i++ ){
            int numberOfZeroes = 0;
            int numberOfOnes = 0;
            while (line != null){
                char[] array = line.toCharArray();
                if (array[i] == '0'){
                   // System.out.println("numberOfZeroes plus 1");
                    numberOfZeroes++;
                } else {
                   // System.out.println("numberOfOnes plus 1");
                    numberOfOnes++;
                }
                line = reader.readLine();
            }
           // System.out.println(numberOfOnes);
            //System.out.println(numberOfZeroes);
            reader = new BufferedReader(new FileReader(file));
            line = reader.readLine();
            if(numberOfZeroes > numberOfOnes){
                number[i] = 0;
                numberPrime[i] = 1;
            } else {
                number[i] = 1;
                numberPrime[i] = 0;
            }
        }
        StringBuilder s = new StringBuilder();
        StringBuilder sPrime = new StringBuilder();
        for (int x = 0; x < number.length; x++){
            s.append(number[x]);
            sPrime.append(numberPrime[x]);
        }
        int finalNum = Integer.parseInt(s.toString(), 2);
        int finalNumPrime = Integer.parseInt(sPrime.toString(), 2);
        return finalNum * finalNumPrime;

    }
    public static int[] strArrayToIntArray(String[] array){
        int[] intArray = new int[array.length];
        for(int i = 0; i < array.length; i++){
            intArray[i] = Integer.parseInt(array[i]);
        }
        return intArray;
    }
    public static int mostCommon(int[] array){
        int numOfZeroes = 0;
        int numOfOnes = 0;
        for (int i = 0; i < array.length; i++){
            if (array[i] == 0){
                numOfZeroes++;
            } else {
                numOfOnes++;
            }
        }
        return numOfOnes == numOfZeroes
                ? 2
                : numOfOnes > numOfZeroes
                ? 1
                : 0;
    }
    public static int[] arrayOfDigit(int position, String[] sa){
        int[] a = new int[sa.length];
        for (int i = 0; i < sa.length && sa[i] != null; i++){
            char[] c = sa[i].toCharArray();
            a[i] = Character.getNumericValue(c[position]);
        }
        return a;
    }
    public static String lifeSupportRating(File file, String bitCriteria) throws IOException{
        String[] overString = linesToStrArray(file);
        ArrayList<String> overList = new ArrayList(Arrays.asList(overString));
        for(int i = 0; i < 12; i++){
            int mostCommonDigit = mostCommon(arrayOfDigit(i, overString));
            System.out.println("Most common number = " + mostCommonDigit);
            int leastCommonDigit = helper(mostCommonDigit);
            System.out.println("Least common number =" + leastCommonDigit);

            for (int j = 0; j < overString.length; j++){
                int actualDigit = Character.getNumericValue(overString[j].toCharArray()[i]);
                System.out.println(overString[j].toCharArray()[i]);
                if (overList.size() == 1){
                    break;
                } else if (bitCriteria == "O2"){
                    if (actualDigit == mostCommonDigit || (mostCommonDigit == 2 && actualDigit == 1)){
                        System.out.println("leaving " + overString[j]);
                        System.out.println(overList.toString());
                        continue;
                    } else {
                        System.out.println("removing " + overString[j]);

                        overList.remove(overString[j]);
                        System.out.println(overList.toString());
                    }
                } else {
                    if(actualDigit == leastCommonDigit || (mostCommonDigit == 2 && actualDigit == 0)){
                        System.out.println("leaving " + overString[j]);
                        System.out.println(overList.toString());
                        continue;

                    } else {
                        System.out.println("removing " + overString[j]);
                        System.out.println(overList.toString());
                        overList.remove(overString[j]);
                    }
                }
            }
            overString = overList.toArray(new String[overList.size()]);
            System.out.println(Arrays.toString(overString));
        }
        return overString[0];
    }
    public static int helper (int a) {
        return a == 1
                ? 0
                : a == 2
                ? 2
                : 1;
    }
}


