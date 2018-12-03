import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day2 {

    private static final String FILEPATH = "src/main/resources/Input_day2.txt";


    public static void performDay2(){
        try {
            List<String> input = Helper.readWholeFile(FILEPATH);
            int result = createChecksum(input);
            System.out.println("Result: " + result);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    static int createChecksum(List<String> input){
        int numberOfDuplicates = 0;
        int numberOfTriplets = 0;

        for (String line: input) {
            Map<Character, Integer> countAppearedChar = createCharCountMap(line);

            if(charAppearsExactlyNTimes(countAppearedChar, 2))
                numberOfDuplicates++;
            if(charAppearsExactlyNTimes(countAppearedChar, 3))
                numberOfTriplets++;

        }
        return calculateProduct(numberOfDuplicates, numberOfTriplets);
    }

    static Map<Character, Integer> createCharCountMap(String line){
        Map<Character, Integer> characterCount = new HashMap<>();

        for (char letter : line.toCharArray()){
            if(characterCount.containsKey(letter)) {
                int oldNumber = characterCount.get(letter);
                characterCount.replace(letter, oldNumber + 1);
            } else
                characterCount.put(letter, 1);
        }
        return characterCount;
    }

    static boolean charAppearsExactlyNTimes(Map<Character, Integer> countAppearedChar, int n){
        return  countAppearedChar.containsValue(n);
    }

    static int calculateProduct(int dup, int tri){
        return (dup*tri);
    }
}
