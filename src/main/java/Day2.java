import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day2 {

    private static final String FILEPATH = "src/main/resources/Input_day2.txt";


    public static void Day2_out(){
        try {
            List<String> input = Helper.readWholeFile(FILEPATH);
            int result = countCharAppeared(input);
            System.out.println("Result: " + result);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    static int countCharAppeared(List<String> input){
        int numberOfDuplicates = 0;
        int numberOfTriplets = 0;

        for (String line: input) {
            Map<Character, Integer> countAppearedChar = new HashMap<>();
            char[] stringToCharArray = line.toCharArray();

            for (char letter : stringToCharArray){
                if(countAppearedChar.containsKey(letter)){
                    int oldNumber = countAppearedChar.get(letter);
                    countAppearedChar.replace(letter, (oldNumber+1));
                }else
                    countAppearedChar.put(letter, 1);
            }

            if(countAppearedChar.containsValue(2))
                numberOfDuplicates++;
            if(countAppearedChar.containsValue(3))
                numberOfTriplets++;

        }
        int result = calculateProduct(numberOfDuplicates, numberOfTriplets);

        return result;
    }

    static int calculateProduct(int dup, int tri){
        return (dup*tri);
    }
}
