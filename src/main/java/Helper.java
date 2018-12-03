import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

class Helper {

    static List<String> readWholeFile(String filePath) throws IOException {
        File f = new File(filePath);
        List<String> inputToRepeat = new ArrayList<>();

        try (BufferedReader b = new BufferedReader(new FileReader(f))) {
            String readline;
            while ((readline = b.readLine()) != null) {
                inputToRepeat.add(readline);
            }
        }
        return inputToRepeat;
    }
}
