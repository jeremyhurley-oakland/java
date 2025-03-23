import java.io.*;
import java.util.*;

public class WordCounter {
    public static void main(String[] args) {
        String inputFilePath = "input.txt"; //
        String outputFilePath = "output.txt"; //

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFilePath));
             BufferedWriter writer = new BufferedWriter(new FileWriter(outputFilePath))) {

                //
                Map<String, Integer> WordCounts = new HashMap<>();

                //
                String line;
                while ((line = reader.readLine()) != null) {
                    String word = line.toLowerCase(); //
                    WordCounts.put(word, WordCounts.getOrDefault(word, 0) + 1);
                }

                //
                for(Map.Entry<String, Integer> entry : WordCounts.entrySet()) {
                    writer.write(entry.getKey() + " " + entry.getValue());
                    writer.newLine();
                }

                System.out.println("Word counts written to " + outputFilePath);

            } catch (IOException e) {
                e.printStackTrace();
            }
    }
}
