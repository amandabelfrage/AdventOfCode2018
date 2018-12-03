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
            int checksum = createChecksum(input);
            System.out.println("Checksum: " + checksum);
            String commonLetters = findCommonLetters(input);
            System.out.println("Common Letters: " + commonLetters);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    static String findCommonLetters(List<String> input){
        String commonLetters = "";
        outerloop:
        for (int i = 0; i < (input.size()-1); i++){
            String line1 = input.get(i);
            for(int j = i+1; j < input.size(); j++){            
                String line2 = input.get(j);
                commonLetters = compareCharByChar(line1, line2);
                if(commonLetters != "")
                    break outerloop;
            }
        }
        return commonLetters;
    }

    static String compareCharByChar(String line1, String line2){
        char[] charactersLine1 = line1.toCharArray();
        char[] charactersLine2 = line2.toCharArray();
        int unequalCharCounter = 0;
        String commonLetters = "";
        for (int i = 0; i < charactersLine1.length; i++){
            if(charactersLine1[i] == (charactersLine2[i])){
                commonLetters += charactersLine1[i];
            }else
                unequalCharCounter++;
        }
        if(unequalCharCounter != 1)
            commonLetters = "";
        return commonLetters;
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
