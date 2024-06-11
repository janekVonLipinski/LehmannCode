package LehmannCode.OtherStuff;

import LehmannCode.IO.Input;
import LehmannCode.IO.Output;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static LehmannCode.OtherStuff.TestMain.listFiles;

public class AnalyseOutput {

    private static final String PATH =
            "C:\\Users\\Admin\\Desktop\\java\\Naeherungsverfahren\\LehmannCode\\outputDirectory\\";

    private static final String OUTPUT_PATH = "C:\\Users\\Admin\\Desktop\\java\\Naeherungsverfahren\\LehmannCode\\csv\\";

    public static void main(String[] args) {

        List<String> times = new ArrayList<>();

        List<String> listOfFiles = listFiles(PATH);
        listOfFiles.sort(Comparator.comparingInt(TestMain::extractInt));
        Pattern p = Pattern.compile("\\s+\\d*\\s+");

        try {
            for (String filename : listOfFiles) {

                if (filename.charAt(0) != 'g') {
                    continue;
                }

                List<String> read = new Input().readFile(PATH + filename);

                List<String> fileContent = read.stream()
                        .filter(string -> p.matcher(string).find())
                        .toList();

                times.add(fileContent.get(0));
            }


            String output = times.stream()
                    .map(i -> i.replaceAll("\\D", ""))
                    .map(i -> i +",\n")
                    .collect(Collectors.joining());



            new Output().writeToFile(OUTPUT_PATH + "genral_analyses.csv", output);

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
