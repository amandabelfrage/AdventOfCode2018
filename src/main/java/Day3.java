import com.sun.org.apache.xml.internal.serialize.LineSeparator;

import java.io.IOException;
import java.util.*;

public class Day3 {

    private static final String FILEPATH = "src/main/resources/Input_day3.txt";

    public static void performDay3() {
        try {
            System.out.println("**** DAY 3 ****");

            List<String> input = Helper.readWholeFile(FILEPATH);
            List[][] grid = createGrid(input);
            List<String> claimIds = updateGrid(grid, input);

            int numberOfSquareInches = calculateSquareInches(grid, grid.length, grid[0].length);

            List<String> overlaps = countTimesIdsAppear(grid, grid.length, grid[0].length);
            String idNoOverlap = getIdNoOverlap(claimIds, overlaps);

            System.out.println("The number of square inches claimed by two or more: " + numberOfSquareInches);
            System.out.println("The id of the one that does not overlap: " + idNoOverlap + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    static List[][] createGrid(List<String> input) {

        int maxX = Integer.MIN_VALUE;
        int maxY = Integer.MIN_VALUE;

        for (String line : input) {
            String[] splittedLine = line.split(" |\\,|x");

            int startXCoordinate = Integer.parseInt(splittedLine[2]);
            int startYCoordinate = Integer.parseInt((splittedLine[3]).split(":")[0]);
            int xCorner = startXCoordinate + Integer.parseInt(splittedLine[4]) + 1;
            int yCorner = startYCoordinate + Integer.parseInt(splittedLine[5]) + 1;

            if (xCorner > maxX)
                maxX = xCorner;

            if (yCorner > maxY)
                maxY = yCorner;
        }

        List[][] grid = new List[maxX][maxY];
        return grid;
    }

    static List<String> updateGrid(List[][] grid, List<String> input) {
        List<String> claimIds = new ArrayList<>();

        for (String line : input) {
            String[] splittedLine = line.split(" |\\,|\\:|x");
            claimIds.add(splittedLine[0]);
            int startX = Integer.parseInt(splittedLine[2]);
            int startY = Integer.parseInt(splittedLine[3]);
            for (int y = startX; y <= startX + Integer.parseInt(splittedLine[5]) - 1; y++) {
                for (int x = startY; x <= startY + Integer.parseInt(splittedLine[6]) - 1; x++) {
                    if (grid[x][y] != null) {
                        List<String> updateValues = grid[x][y];
                        updateValues.add(splittedLine[0]);
                        grid[x][y] = updateValues;
                    } else {
                        List<String> newValue = new ArrayList<>();
                        newValue.add(splittedLine[0]);
                        grid[x][y] = newValue;
                    }
                }
            }
        }
        return claimIds;
    }


    static int calculateSquareInches(List[][] grid, int xCorner, int yCorner) {
        int numberOfSquareInches = 0;
        for (int x = 0; x < xCorner; x++) {
            for (int y = 0; y < yCorner; y++) {
                List<String> values = grid[x][y];
                if (values != null && values.size() > 1) {
                    numberOfSquareInches += 1;
                }
            }
        }
        return numberOfSquareInches;
    }

    static List<String> countTimesIdsAppear(List[][] grid, int xCorner, int yCorner) {

        List<String> overlaps = new ArrayList<>();
        for (int x = 0; x < xCorner; x++) {
            for (int y = 0; y < yCorner; y++) {
                List<String> coordValues = grid[x][y];
                if (coordValues != null) {
                    for (String elem : coordValues) {
                        if (coordValues.size() > 1) {
                            if (!overlaps.contains(elem)) {
                                overlaps.add(elem);
                            }
                        }
                    }
                }
            }
        }

        return overlaps;
    }

    static String getIdNoOverlap(List<String> allIds, List<String> overlaps) {
        String id = "";
        for (String elem : allIds) {
            if (!overlaps.contains(elem))
                id = elem;
        }
        return id;
    }
}
