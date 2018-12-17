import com.sun.org.apache.xml.internal.serialize.LineSeparator;

import java.io.IOException;
import java.util.*;

public class Day3 {

    private static final String FILEPATH = "src/main/resources/Input_day3.txt";

    public static void performDay3(){
        try {
            List<String> input = Helper.readWholeFile(FILEPATH);
            List<String> results = createGrid(input);
            System.out.println("The number of square inches claimed by two or more: " + results.get(0));
            System.out.println("The id of the one that does not overlap: " +results.get(1));
        }catch (IOException e){
            e.printStackTrace();
        }

    }

    static List<String> createGrid(List<String> input){

        List<String> returnValues = new ArrayList<>();
        List<String> claimIds = new ArrayList<>();

        int maxX = Integer.MIN_VALUE;
        int maxY = Integer.MIN_VALUE;

        for (String line : input){
            String[] splittedLine = line.split(" ");
            claimIds.add(splittedLine[0]);
            int startXCoordinate = Integer.parseInt(splittedLine[2].split(",")[0]);
            int startYCoordinate = Integer.parseInt((splittedLine[2].split(",")[1]).split(":")[0]);
            String[] area = splittedLine[3].split("x");

            int xCorner = startXCoordinate + Integer.parseInt(area[0]) + 1;
            int yCorner = startYCoordinate + Integer.parseInt(area[1]) + 1;

            if(xCorner > maxX)
                maxX = xCorner;

            if(yCorner > maxY)
                maxY = yCorner;
        }
        List[][] grid = new List[maxX][maxY];
        updateGrid(grid, input);
        returnValues.add(Integer.toString(calculateSquareInches(grid, maxX, maxY)));
        List<String> overlaps = (countTimesIdsAppear(grid, maxX, maxY));
        returnValues.add(getIdNoOverlap(claimIds, overlaps));
        return returnValues;
    }

    static void updateGrid(List[][] grid, List<String> input){
        for(String line : input){
            String[] splittedLine = line.split(" ");
            String[] coordinates = (splittedLine[2]).split(",");
            coordinates[1] = coordinates[1].split(":")[0];
            String[] size = (splittedLine[3]).split("x");
            int startX = Integer.parseInt(coordinates[0]);
            int startY = Integer.parseInt(coordinates[1]);
            for(int y = startX; y <= startX + Integer.parseInt(size[0])-1; y++) {
                for (int x = startY; x <= startY + Integer.parseInt(size[1])-1; x++) {
                    if(grid[x][y] != null) {
                        List<String> updateValues = grid[x][y];
                        updateValues.add(splittedLine[0]);
                        grid[x][y] = updateValues;
                    }
                    else {
                        List<String> newValue = new ArrayList<>();
                        newValue.add(splittedLine[0]);
                        grid[x][y] = newValue;
                    }
                }
            }
        }
    }


    static int calculateSquareInches(List[][] grid, int xCorner, int yCorner){
        int numberOfSquareInches = 0;
        for(int x = 0; x < xCorner; x++){
            for(int y = 0; y < yCorner; y++){
                List<String> values = grid[x][y];
                if(values != null && values.size() > 1){
                    numberOfSquareInches += 1;
                }
            }
        }
        return numberOfSquareInches;
    }

    static List<String> countTimesIdsAppear(List[][] grid, int xCorner, int yCorner){

        List<String> overlaps = new ArrayList<>();
        for(int x = 0; x < xCorner; x++) {
            for (int y = 0; y < yCorner; y++) {
                List<String> coordValues = grid[x][y];
                if(grid[x][y] != null) {
                    for(String elem : coordValues) {
                        if (coordValues.size() > 1) {
                            if (!overlaps.contains(elem)){
                                overlaps.add(elem);
                            }
                        }
                    }
                }
            }
        }

        return overlaps;
    }
    
    static String getIdNoOverlap(List<String> allIds, List<String> overlaps){
        String id = "";
        for (String elem : allIds){
            if(!overlaps.contains(elem))
                id = elem;
        }
        return id;
    }
}
